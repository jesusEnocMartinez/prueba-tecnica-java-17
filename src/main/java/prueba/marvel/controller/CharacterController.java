package prueba.marvel.controller;

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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/characters")
@Tag(name = "Endpoint Hello")
public class CharacterController {

    private final CharacterService characterService;

    @Autowired
    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @PostMapping("/post")
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



    @GetMapping("/All")
    public ResponseEntity<?> getAllCharacters() {
        List<CharacterModel> characters = characterService.getAllCharacters();
        if (characters.isEmpty()) {
            Map<String, String> response = Map.of("message", "No characters found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            return ResponseEntity.ok(characters);
        }
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<CharacterModel> getCharacterById(@PathVariable Long id) {
        Optional<CharacterModel> characterOptional = characterService.getCharacterById(id);
        return characterOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/getCharacterById")
    public ResponseEntity<?> getCharacterMarvelById(@RequestParam Long id) {
        CharacterDto characterDto = characterService.findNameAndSuitById(id);
        if (characterDto != null) {
            return ResponseEntity.ok(characterDto);
        } else {
            Map<String, String> response = Map.of("error", "Character not found with id " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    @PutMapping("/put/{id}")
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


    @DeleteMapping("/delete/{id}")
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
