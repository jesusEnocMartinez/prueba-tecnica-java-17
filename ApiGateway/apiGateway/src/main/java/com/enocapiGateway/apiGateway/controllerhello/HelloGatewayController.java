package com.enocapiGateway.apiGateway.controllerhello;

import com.enocapiGateway.apiGateway.servicehello.HelloGatewayService;
import com.enocapiGateway.apiGateway.utils.SwaggerDescriptions;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Controller for handling requests related to Hello services.
 */
@RestController
@Tag(name = "Endpoint apiGateway Hello")
public class HelloGatewayController {

    private final HelloGatewayService externalService;

    /**
     * Constructs a new GatewayController.
     *
     * @param externalService the ExternalService instance
     */
    public HelloGatewayController(HelloGatewayService externalService) {
        this.externalService = externalService;
    }

    /**
     * Handles GET requests for hello1 endpoint.
     *
     * @return a Mono emitting the response from hello1 service
     */
    @GetMapping("/hello1")
    @Operation(
            summary = "Returns 1",
            description = "Returns Hello 1")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = SwaggerDescriptions.HANDLING_200),
            @ApiResponse(responseCode = "204", description = SwaggerDescriptions.HANDLING_204),
            @ApiResponse(responseCode = "500", description = SwaggerDescriptions.HANDLING_500)
    })
    public Mono<String> getHello1() {
        return externalService.getHello1();
    }

    /**
     * Handles GET requests for hello2 endpoint.
     *
     * @return a Mono emitting the response from hello2 service
     */
    @GetMapping("/hello2")
    @Operation(
            summary = "Returns 2",
            description = "Returns Hello 2")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = SwaggerDescriptions.HANDLING_200),
            @ApiResponse(responseCode = "204", description = SwaggerDescriptions.HANDLING_204),
            @ApiResponse(responseCode = "500", description = SwaggerDescriptions.HANDLING_500)
    })
    public Mono<String> getHello2() {
        return externalService.getHello2();
    }
}
