package prueba.marvel.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import prueba.marvel.service.MarvelApiService;
import prueba.marvel.utils.SwaggerDescriptions;

@RestController
@Tag(name = "Endpoint Api-Marvel")
public class MarvelController {
private final MarvelApiService marvelApiService;
     @Autowired
    public MarvelController(MarvelApiService marvelApiService) {
        this.marvelApiService = marvelApiService;
    }

    /**
     * Retrieves Marvel character information from the Marvel API.
     *
     * @return ResponseEntity containing the response body as a String.
     */
    @GetMapping("/marvel/characters")
    @Operation(
            summary = "Endpoint que regresa contenido de  marvel",
            description = "regresa contenido de marvel"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = SwaggerDescriptions.HANDLING_200),
            @ApiResponse(responseCode = "500", description = SwaggerDescriptions.HANDLING_500)
    })
    public ResponseEntity<String> getMarvelCharacters() {
        return marvelApiService.getCharacters();
    }
}
