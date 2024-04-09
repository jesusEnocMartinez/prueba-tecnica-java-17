package prueba.marvel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prueba.marvel.repository.HelloRepository;
import prueba.marvel.service.HelloService;

@Service
public class HelloServiceImpl implements HelloService {
    private final HelloRepository helloRepository;

    @Autowired
    public HelloServiceImpl(HelloRepository helloRepository) {
        this.helloRepository = helloRepository;
    }

    /**
     * Retrieves the hello message from the repository.
     *
     * @return The hello message retrieved from the repository.
     */
    @Override
    public String getHelloMessage() {
        var helloModel = helloRepository.createHelloMessage();
        return helloModel.getMessage();
    }
}
