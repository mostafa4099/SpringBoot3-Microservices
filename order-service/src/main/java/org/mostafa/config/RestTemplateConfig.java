package org.mostafa.config;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

/**
 * @Author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @CreationDate 8/13/2024 4:56 PM
 */
@Configuration
@RequiredArgsConstructor
public class RestTemplateConfig {
    private final ObservationRegistry observationRegistry;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .additionalInterceptors(observationInterceptor(observationRegistry))
                .build();
    }

    private ClientHttpRequestInterceptor observationInterceptor(ObservationRegistry observationRegistry) {
        return (request, body, execution) -> {
            Observation observation = Observation.createNotStarted("http.client.requests", observationRegistry);
            observation.start();
            try {
                return execution.execute(request, body);
            } finally {
                observation.stop();
            }
        };
    }
}
