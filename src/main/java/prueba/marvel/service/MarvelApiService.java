package prueba.marvel.service;

import org.springframework.http.ResponseEntity;

public interface MarvelApiService {
    public ResponseEntity<String> getCharacters();
}
