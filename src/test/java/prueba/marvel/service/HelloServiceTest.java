package prueba.marvel.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import prueba.marvel.model.HelloModel;
import prueba.marvel.repository.HelloRepository;
import prueba.marvel.service.impl.HelloServiceImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


public class HelloServiceTest {

    @Mock
    private HelloRepository helloRepository;

    private HelloService helloService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        helloService = new HelloServiceImpl(helloRepository);
    }

    /**
     * Unit test to verify that the method getHelloMessage()
     * returns the correct message.
     */
    @Test
    void testGetHelloMessage() {
        HelloModel helloModel = new HelloModel();
        helloModel.setMessage("Hello, World!");
        when(helloRepository.createHelloMessage()).thenReturn(helloModel);
        String helloMessage = helloService.getHelloMessage();
        assertEquals("Hello, World!", helloMessage);
    }


}
