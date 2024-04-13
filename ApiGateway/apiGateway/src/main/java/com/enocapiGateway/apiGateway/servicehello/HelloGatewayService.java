package com.enocapiGateway.apiGateway.servicehello;

import reactor.core.publisher.Mono;

public interface HelloGatewayService {
    Mono<String> getHello1();
    Mono<String> getHello2();
}
