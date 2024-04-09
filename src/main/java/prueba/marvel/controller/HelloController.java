package prueba.marvel.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import prueba.marvel.service.HelloService;
import prueba.marvel.utils.SwaggerDescriptions;

import java.util.Map;

@RestController
@RequestMapping("/hello")
@Tag(name = "Endpoint Hello")
public class HelloController
{
    private final HelloService helloService;

    @Autowired
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    /**
     * Retrieves a hello message.
     *
     * @return A map containing the hello message.
     */
    @GetMapping
    @Operation(
            summary = "Endpoint Showing Welcome Path",
            description = "Endpoint showing welcome path to test backend"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = SwaggerDescriptions.HANDLING_200),
            @ApiResponse(responseCode = "500", description = SwaggerDescriptions.HANDLING_500)
    })
    public Map<String, String> getHelloMessage() {
        String message = helloService.getHelloMessage();
        return Map.of("message", message);
    }
}
