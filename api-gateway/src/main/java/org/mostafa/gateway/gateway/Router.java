package org.mostafa.gateway.gateway;

import org.mostafa.gateway.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @CreationDate 8/13/2024 3:48 PM
 */
@Configuration
public class Router {
    @Value("${product.service.url}")
    private String productServiceUrl;

    @Value("${order.service.url}")
    private String orderServiceUrl;

    @Value("${inventory.service.url}")
    private String inventoryServiceUrl;

    @Value("${auth.service.url}")
    private String authServiceUrl;

    @Value("${discovery.server.url}")
    private String discoveryServerUrl;

    @Autowired
    private AuthenticationFilter authenticationFilter;

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("product-service", r -> r.path("/api/product/**")
                        .filters(f -> f.filter(authenticationFilter)
                                .circuitBreaker(c -> c.setName("productServiceCircuitBreaker")
                                        .setFallbackUri("forward:/fallback/product")))
                        .uri(productServiceUrl))
                .route("product-service-aggregate-swagger", r -> r.path("/aggregate/product-service/v3/api-docs")
                        .filters(f -> f.setPath("/api/product/api-docs")
                                .circuitBreaker(c -> c.setName("productServiceSwaggerCircuitBreaker")
                                        .setFallbackUri("forward:/fallback/common")))
                        .uri(productServiceUrl))
                .route("order-service", r -> r.path("/api/order/**")
                        .filters(f -> f.filter(authenticationFilter)
                                .circuitBreaker(c -> c.setName("orderServiceCircuitBreaker")
                                        .setFallbackUri("forward:/fallback/common")))
                        .uri(orderServiceUrl))
                .route("order-service-aggregate-swagger", r -> r.path("/aggregate/order-service/v3/api-docs")
                        .filters(f -> f.setPath("/api/order/api-docs")
                                .circuitBreaker(c -> c.setName("orderServiceSwaggerCircuitBreaker")
                                        .setFallbackUri("forward:/fallback/common")))
                        .uri(orderServiceUrl))
                .route("inventory-service", r -> r.path("/api/inventory/**")
                        .filters(f -> f.filter(authenticationFilter)
                                .circuitBreaker(c -> c.setName("inventoryServiceCircuitBreaker")
                                        .setFallbackUri("forward:/fallback/common")))
                        .uri(inventoryServiceUrl))
                .route("inventory-service-aggregate-swagger", r -> r.path("/aggregate/inventory-service/v3/api-docs")
                        .filters(f -> f.setPath("/api/inventory/api-docs")
                                .circuitBreaker(c -> c.setName("inventoryServiceSwaggerCircuitBreaker")
                                        .setFallbackUri("forward:/fallback/common")))
                        .uri(inventoryServiceUrl))
                .route("auth-service", r -> r.path("/api/auth/**")
                        .filters(f -> f.circuitBreaker(c -> c.setName("authServiceCircuitBreaker")
                                .setFallbackUri("forward:/fallback/auth")))
                        .uri(authServiceUrl))
                .route("auth-service-aggregate-swagger", r -> r.path("/aggregate/auth-service/v3/api-docs")
                        .filters(f -> f.setPath("/api/auth/api-docs")
                                .circuitBreaker(c -> c.setName("authServiceSwaggerCircuitBreaker")
                                        .setFallbackUri("forward:/fallback/auth")))
                        .uri(authServiceUrl))
                .route("discovery-server", r -> r.path("/eureka/web")
                        .filters(f -> f.setPath("/")
                                .circuitBreaker(c -> c.setName("discoveryServiceCircuitBreaker")
                                        .setFallbackUri("forward:/fallback/common")))
                        .uri(discoveryServerUrl))
                .route("discovery-server-static-content", r -> r.path("/eureka/**")
                        .uri(discoveryServerUrl))
                .build();
    }
}
