package com.enocapiGateway.apiGateway.servicehello.impl;

import com.enocapiGateway.apiGateway.servicehello.HelloGatewayService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Service implementation for interacting with external Hello services.
 */
@Service
public class HelloGatewayServiceImpl implements HelloGatewayService {

    private final WebClient.Builder webClientBuilder;

    @Value("${service.url.hello1}")
    private String hello1Url;

    @Value("${service.url.hello2}")
    private String hello2Url;

    /**
     * Constructs a new HelloGatewayServiceImpl.
     *
     * @param webClientBuilder the WebClient.Builder instance
     */
    public HelloGatewayServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    /**
     * Retrieves a hello message from the first external service.
     *
     * @return a Mono emitting the hello message
     */
    @Override
    public Mono<String> getHello1() {
        return webClientBuilder.build()
                .get()
                .uri(hello1Url)
                .retrieve()
                .bodyToMono(String.class);
    }

    /**
     * Retrieves a hello message from the second external service.
     *
     * @return a Mono emitting the hello message
     */
    @Override
    public Mono<String> getHello2() {
        return webClientBuilder.build()
                .get()
                .uri(hello2Url)
                .retrieve()
                .bodyToMono(String.class);
    }
}
