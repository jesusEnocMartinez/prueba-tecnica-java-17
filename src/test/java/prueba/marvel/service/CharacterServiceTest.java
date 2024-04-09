package prueba.marvel.service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import prueba.marvel.model.CharacterModel;
import prueba.marvel.model.dto.CharacterDto;
import prueba.marvel.repository.CharacterRepository;
import prueba.marvel.service.impl.CharacterServiceImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


public class CharacterServiceTest {
    private CharacterServiceImpl characterService;

    @Mock
    private CharacterRepository mockCharacterRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        characterService = new CharacterServiceImpl(mockCharacterRepository);
    }

    @Test
    void testCreateCharacter() {
        CharacterModel character = new CharacterModel();
        character.setName("Iron Man");
        character.setSuit("Iron Man");
        when(mockCharacterRepository.save(character)).thenReturn(character);
        CharacterModel savedCharacter = characterService.createCharacter(character);
        assertNotNull(savedCharacter);
        verify(mockCharacterRepository, times(1)).save(character);
    }

    @Test
    void testGetAllCharacters() {
        List<CharacterModel> listaDePersonajes = Arrays.asList(
                new CharacterModel(1L,"Iron Man",2l,"","","","","","","","","",""),
                new CharacterModel(2L,"Iron",5l,"","","","","","","","","","")
        );
        when(mockCharacterRepository.findAll()).thenReturn(listaDePersonajes);
        List<CharacterModel> characters = characterService.getAllCharacters();
        assertNotNull(characters);
        assertFalse(characters.isEmpty());
    }

    @Test
    void testGetCharacterById() {
        Long characterId = 1L;
        CharacterModel character = new CharacterModel();
        character.setId(characterId);
        character.setName("Iron Man");
        when(mockCharacterRepository.findById(characterId)).thenReturn(Optional.of(character));
        Optional<CharacterModel> foundCharacter = characterService.getCharacterById(characterId);
        assertTrue(foundCharacter.isPresent());
        assertEquals(characterId, foundCharacter.get().getId());

    }

    @Test
    void testUpdateCharacter() {
        Long characterId = 1L;
        CharacterModel existingCharacter = new CharacterModel();
        existingCharacter.setId(characterId);
        existingCharacter.setName("Iron Man");
        CharacterModel updatedCharacter = new CharacterModel();
        updatedCharacter.setId(characterId);
        updatedCharacter.setName("Tony Stark");
        updatedCharacter.setAge(40L);
        when(mockCharacterRepository.findById(characterId)).thenReturn(Optional.of(existingCharacter));
        when(mockCharacterRepository.save(existingCharacter)).thenReturn(updatedCharacter);
        CharacterModel savedCharacter = characterService.updateCharacter(characterId, updatedCharacter);
        assertNotNull(savedCharacter);
        assertEquals("Tony Stark", savedCharacter.getName());

    }

    @Test
    void testDeleteCharacter() {
        Long characterId = 1L;
        characterService.deleteCharacter(characterId);
        verify(mockCharacterRepository, times(1)).deleteById(characterId);
    }

    @Test
    void testFindNameAndSuitById() {
        Long id = 123L;
        CharacterDto expectedCharacterDto = new CharacterDto("Iron Man", "Mark XLII");
        when(mockCharacterRepository.findNameAndSuitById(id)).thenReturn(expectedCharacterDto);
        CharacterDto actualCharacterDto = characterService.findNameAndSuitById(id);
        assertEquals(expectedCharacterDto, actualCharacterDto);
    }
}


