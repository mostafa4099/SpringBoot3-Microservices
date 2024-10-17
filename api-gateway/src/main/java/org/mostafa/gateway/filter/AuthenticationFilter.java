package org.mostafa.gateway.filter;


import org.mostafa.gateway.excption.AuthServiceUnavailableException;
import org.mostafa.gateway.excption.SessionExpiredException;
import org.mostafa.gateway.model.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.*;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @CreationDate 9/11/2024 1:54 PM
 */
@Component
public class AuthenticationFilter implements GatewayFilter {
    @Value("${auth.service.url}")
    private String authServiceUrl;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private CircuitBreakerFactory<?, ?> circuitBreakerFactory;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String authorizationHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return Mono.error(new RuntimeException("Authorization header is missing or invalid"));
        }

        String token = authorizationHeader.substring(7);
        String url = authServiceUrl + "/api/auth/validate?token=" + token;

        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("authServiceCircuitBreaker");

        return circuitBreaker.run(
                        () -> webClientBuilder.build()
                                .get()
                                .uri(url)
                                .retrieve()
                                .bodyToMono(AuthResponse.class),
                        throwable -> Mono.error(new IllegalStateException("Authentication service unavailable"))
                )
                .flatMap(response -> {
                    AuthResponse authResponse = (AuthResponse) response;
                    if (Boolean.TRUE.equals(authResponse.isValid())) {
                        //Pass logged user info in authenticate service.
                        ServerHttpRequest request = null;
                        if (null != authResponse.getLoggedUser()) {
                            request = exchange.getRequest()
                                    .mutate()
                                    .header("loggedUsername", authResponse.getLoggedUser().getUsername())
                                    .build();
                        }
                        return chain.filter(exchange.mutate().request(request).build());
                    } else if (Boolean.TRUE.equals(authResponse.isExpired())) {
                        return Mono.error(new SessionExpiredException(authResponse.getMessage()));
                    } else if (Boolean.TRUE.equals(authResponse.isIllegalArg())) {
                        return Mono.error(new IllegalArgumentException(authResponse.getMessage()));
                    } else {
                        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                        return exchange.getResponse().setComplete();
                    }
                })
                .onErrorResume(throwable -> {
                    if (throwable instanceof SessionExpiredException) {
                        return Mono.error(new SessionExpiredException(throwable.getMessage()));
                    } else if (throwable instanceof IllegalArgumentException) {
                        return Mono.error(new IllegalArgumentException(throwable.getMessage()));
                    } else {
                        return Mono.error(new AuthServiceUnavailableException("Auth service unavailable. Please retry."));
                    }
                });
    }
}
