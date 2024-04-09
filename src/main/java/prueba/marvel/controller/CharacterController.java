package prueba.marvel.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import prueba.marvel.model.CharacterModel;
import prueba.marvel.model.dto.CharacterDto;
import prueba.marvel.service.CharacterService;
import prueba.marvel.utils.CustomError;
import prueba.marvel.utils.SwaggerDescriptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/characters")
@Tag(name = "Endpoint characters")
public class CharacterController {

    private final CharacterService characterService;

    @Autowired
    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    /**
     * Endpoint for creating a new character.
     *
     * @param character The character data to be created.
     * @return ResponseEntity representing the outcome of the operation.
     */
    @PostMapping("/post")
    @Operation(
            summary = "Endpoint inserts superheroes",
            description = "Endpoint Inserting Superheroes into DB Memory"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = SwaggerDescriptions.HANDLING_200),
            @ApiResponse(responseCode = "500", description = SwaggerDescriptions.HANDLING_500)
    })
    public ResponseEntity<Object> createCharacter(@RequestBody @Valid CharacterModel character) {
        try {
            CharacterModel createdCharacter = characterService.createCharacter(character);
            String message = (createdCharacter != null) ? "Character created successfully" : "Failed to create character";

            return ResponseEntity.status((createdCharacter != null) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST)
                    .body(new HashMap<>() {{
                        put("character", createdCharacter);
                        put("message", message);
                    }});
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CustomError("Internal Server Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    /**
     * Retrieves all superheroes from the database.
     *
     * @return ResponseEntity containing a list of superheroes if found, or an error message if none are found.
     */
    @GetMapping("/All")  @Operation(
            summary = "Endpoint that brings all the superheroes ...",
            description = "Endpoint that returns all the superheroes of the BD"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = SwaggerDescriptions.HANDLING_200),
            @ApiResponse(responseCode = "500", description = SwaggerDescriptions.HANDLING_500)
    })
    public ResponseEntity<?> getAllCharacters() {
        List<CharacterModel> characters = characterService.getAllCharacters();
        if (characters.isEmpty()) {
            Map<String, String> response = Map.of("message", "No characters found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            return ResponseEntity.ok(characters);
        }
    }

    /**
     * Retrieves a superhero by its ID from the database.
     *
     * @param id The ID of the superhero to retrieve.
     * @return ResponseEntity containing the superhero if found, or an error response if not found.
     */
    @GetMapping("/get/{id}")
    @Operation(
            summary = "Endpoint Throwing Superheroes By id",
            description = "Endpoint Throwing Superheroes by DB ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = SwaggerDescriptions.HANDLING_200),
            @ApiResponse(responseCode = "500", description = SwaggerDescriptions.HANDLING_500)
    })
    public ResponseEntity<CharacterModel> getCharacterById(@PathVariable Long id) {
        Optional<CharacterModel> characterOptional = characterService.getCharacterById(id);
        return characterOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Retrieves specific data of a superhero by its ID.
     *
     * @param id The ID of the superhero to retrieve specific data for.
     * @return ResponseEntity containing the specific data of the superhero if found, or an error response if not found.
     */

    @GetMapping("/getCharacterById")  @Operation(
            summary = "Endpoint that yields specific data",
            description = "Endpoint that arrogates superhero-specific data"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = SwaggerDescriptions.HANDLING_200),
            @ApiResponse(responseCode = "500", description = SwaggerDescriptions.HANDLING_500)
    })
    public ResponseEntity<?> getCharacterMarvelById(@RequestParam Long id) {
        CharacterDto characterDto = characterService.findNameAndSuitById(id);
        if (characterDto != null) {
            return ResponseEntity.ok(characterDto);
        } else {
            Map<String, String> response = Map.of("error", "Character not found with id " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    /**
     * Updates a superhero by its ID.
     *
     * @param id               The ID of the superhero to update.
     * @param updatedCharacter The updated information of the superhero.
     * @return ResponseEntity containing the updated superhero information if successful, or an error response if not found or an internal server error occurs.
     */
    @PutMapping("/put/{id}")
    @Operation(
                summary = "Endpoint Updating Superheroes",
            description = "Endpoint updating superheroes by id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = SwaggerDescriptions.HANDLING_200),
            @ApiResponse(responseCode = "500", description = SwaggerDescriptions.HANDLING_500)
    })
    public ResponseEntity<Object> updateCharacter(@PathVariable Long id, @RequestBody CharacterModel updatedCharacter) {
        try {
            CharacterModel updated = characterService.updateCharacter(id, updatedCharacter);
            if (updated != null) {
                Map<String, Object> responseBody = new HashMap<>();
                responseBody.put("id", updated.getId());
                responseBody.put("message", "Character updated successfully");
                return ResponseEntity.ok().body(responseBody);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Character not found with id " + id));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Internal Server Error: " + e.getMessage()));
        }
    }

    /**
     * Deletes a superhero by its ID.
     *
     * @param id The ID of the superhero to delete.
     * @return ResponseEntity containing a success message if the deletion is successful, or an error response if the superhero is not found or an internal server error occurs.
     */
    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Endpoint That Eliminates Superheroes",
            description = "Endpoint Eliminating Superheroes by id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = SwaggerDescriptions.HANDLING_200),
            @ApiResponse(responseCode = "500", description = SwaggerDescriptions.HANDLING_500)
    })
    public ResponseEntity<Map<String, String>> deleteCharacter(@PathVariable Long id) {
        try {
            characterService.deleteCharacter(id);
            Map<String, String> response = Map.of("message", "Character with id " + id + " deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = Map.of("error", "Failed to delete character with id " + id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

}
