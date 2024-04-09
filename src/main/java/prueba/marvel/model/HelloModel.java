package prueba.marvel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HelloModel {

    @Id
    private Long id;
    private String message;

    public HelloModel(String message) {
        this.message = message;
    }
}
