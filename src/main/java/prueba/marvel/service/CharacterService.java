package prueba.marvel.service;

import prueba.marvel.model.CharacterModel;
import prueba.marvel.model.dto.CharacterDto;

import java.util.List;
import java.util.Optional;

public interface CharacterService {
    public CharacterModel createCharacter(CharacterModel character);
    public List<CharacterModel> getAllCharacters();
    public Optional<CharacterModel> getCharacterById(Long id);
    public CharacterModel updateCharacter(Long id, CharacterModel updatedCharacter);
    public void deleteCharacter(Long id);
    public CharacterDto findNameAndSuitById(Long id);
}
