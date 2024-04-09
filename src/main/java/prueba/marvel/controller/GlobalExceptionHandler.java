package prueba.marvel.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles method argument validation exceptions and returns an HTTP error response with an appropriate error message.
     * This handler is used to capture {@link MethodArgumentNotValidException} exceptions that are thrown when argument validation fails in controllers.
     *
     * @param ex The captured {@link MethodArgumentNotValidException} exception.
     * @return An HTTP error response with an appropriate error message in JSON format.
     */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getDefaultMessage())
                .findFirst()
                .orElse("Validation failed");
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", errorMessage);
        return ResponseEntity.badRequest().body(errorResponse);
    }

}
