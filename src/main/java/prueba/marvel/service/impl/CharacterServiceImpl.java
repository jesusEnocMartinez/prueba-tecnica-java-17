package prueba.marvel.service.impl;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import prueba.marvel.model.CharacterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prueba.marvel.model.dto.CharacterDto;
import prueba.marvel.repository.CharacterRepository;
import prueba.marvel.service.CharacterService;
import prueba.marvel.utils.CustomError;

import java.util.List;
import java.util.Optional;

@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    @Autowired
    public CharacterServiceImpl(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }
    @Override
    public CharacterModel createCharacter(CharacterModel character) {
        return characterRepository.save(character);
    }
    @Override
    public List<CharacterModel> getAllCharacters() {
        return characterRepository.findAll();
    }
    @Override
    public Optional<CharacterModel> getCharacterById(Long id) {
        return characterRepository.findById(id);
    }
    @Override
    public CharacterModel updateCharacter(Long id, CharacterModel updatedCharacter) {
        return characterRepository.findById(id)
                .map(character -> {
                    character.setName(updatedCharacter.getName());
                    character.setAge(updatedCharacter.getAge());
                    character.setGender(updatedCharacter.getGender());
                    character.setLastName(updatedCharacter.getLastName());
                    character.setAbility(updatedCharacter.getAbility());
                    character.setSuit(updatedCharacter.getSuit());
                    character.setAttribute1(updatedCharacter.getAttribute1());
                    character.setAttribute2(updatedCharacter.getAttribute2());
                    character.setAttribute3(updatedCharacter.getAttribute3());
                    character.setAttribute4(updatedCharacter.getAttribute4());
                    character.setAttribute5(updatedCharacter.getAttribute5());
                    character.setAttribute6(updatedCharacter.getAttribute6());
                    return characterRepository.save(character);
                })
                .orElseThrow(() -> new CustomError("Character not found with id ", HttpStatus.BAD_REQUEST));
    }
    @Override
    public void deleteCharacter(Long id) {
        characterRepository.deleteById(id);
    }
    @Override
    public CharacterDto findNameAndSuitById(Long id) {
        return characterRepository.findNameAndSuitById(id);
    }
}
