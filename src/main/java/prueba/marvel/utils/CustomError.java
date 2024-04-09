package prueba.marvel.utils;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;
/**
 * Represents a custom exception for handling errors with specific status codes and messages.
 */
public class CustomError extends RuntimeException {

    private final HttpStatus statusCode;
    private final Map<String, String> customException;

    /**
     * Constructs a new CustomError instance with the specified message and status code.
     * If the message is null or empty, a default error message is used.
     *
     * @param message     The error message.
     * @param statusCode  The HTTP status code.
     */

    public CustomError(String message , HttpStatus statusCode){
        super();
        this.statusCode = statusCode;
        this.customException = new HashMap<>();
        customException.put("message", message == null || message.isEmpty() ? "Ocurrió un error inesperado, consulte a su administrador" : message);
    }

    public Map<String, String> getMessageEx(){
        return customException;
    }
    public HttpStatus getStatusCode() {
        return statusCode;
    }
}
