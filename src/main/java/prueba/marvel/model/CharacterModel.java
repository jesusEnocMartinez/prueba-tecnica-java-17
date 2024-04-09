package prueba.marvel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CharacterModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name is required")
    @Pattern(regexp = "^[^<>]+$", message = "Name cannot contain <> characters")
    private String name;
    @NotNull(message = "Age is required")
    @Max(value = 100, message = "Age must be less than or equal to 100")
    private Long age;
    @Pattern(regexp = "^[^<>]+$", message = "Name cannot contain <> characters")
    private String gender;
    @Pattern(regexp = "^[^<>]+$", message = "Name cannot contain <> characters")
    private String lastName;
    @Pattern(regexp = "^[^<>]+$", message = "Name cannot contain <> characters")
    private String ability;
    @Pattern(regexp = "^[^<>]+$", message = "Name cannot contain <> characters")
    private String suit;
    @Pattern(regexp = "^[^<>]+$", message = "Name cannot contain <> characters")
    private String attribute1;
    @Pattern(regexp = "^[^<>]+$", message = "Name cannot contain <> characters")
    private String attribute2;
    @Pattern(regexp = "^[^<>]+$", message = "Name cannot contain <> characters")
    private String attribute3;
    @Pattern(regexp = "^[^<>]+$", message = "Name cannot contain <> characters")
    private String attribute4;
    @Pattern(regexp = "^[^<>]+$", message = "Name cannot contain <> characters")
    private String attribute5;
    @Pattern(regexp = "^[^<>]+$", message = "Name cannot contain <> characters")
    private String attribute6;
}
