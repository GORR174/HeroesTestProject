package net.catstack.heroes.model.errors;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;

public class ValidationError extends ResponseError {
    private Map<String, String> fieldErrors = new HashMap<>();

    public ValidationError(MethodArgumentNotValidException ex) {
        super(400, "Validation error");

        ex.getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            fieldErrors.put(fieldName, errorMessage);
        });
    }

    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }
}
