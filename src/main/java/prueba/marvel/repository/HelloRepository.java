package prueba.marvel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prueba.marvel.model.HelloModel;
@Repository
public interface HelloRepository
        extends JpaRepository<HelloModel, Long> {

    /**
     * Creates a new HelloModel instance with a predefined message.
     *
     * @return A new HelloModel instance with the message "Hi, this is my tech test".
     */
    default HelloModel createHelloMessage() {
        return new HelloModel("Hi, this is my tech test");
    }

}
