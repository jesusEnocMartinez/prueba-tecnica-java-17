package prueba.marvel.utils;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class CustomError extends RuntimeException {

    private final HttpStatus statusCode;
    private final Map<String, String> customException;

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
