package prueba.marvel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import prueba.marvel.model.CharacterModel;
import prueba.marvel.model.dto.CharacterDto;

@Repository
public interface CharacterRepository
        extends JpaRepository<CharacterModel, Long> {

    @Query("SELECT new prueba.marvel.model.dto.CharacterDto(c.name AS name, c.suit AS suit) FROM CharacterModel c WHERE c.id = :id")
    CharacterDto findNameAndSuitById(@Param("id") Long id);


}
