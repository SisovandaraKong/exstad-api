package co.istad.exstadapi.exception;

import co.istad.exstadapi.base.BasedError;
import co.istad.exstadapi.base.BasedErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class HandleValidationException {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BasedErrorResponse handleValidationException(MethodArgumentNotValidException exception){
        BasedError<List<?>> basedError = new BasedError<>();
        List<Map<String,Object>> errors = new ArrayList<>();
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            Map<String, Object> error = new HashMap<>();
            error.put("field", fieldError.getField());
            error.put("reason", fieldError.getDefaultMessage());
            errors.add(error);
        }
        basedError.setCode(HttpStatus.BAD_REQUEST.getReasonPhrase());
        basedError.setDescription(errors);
        return new BasedErrorResponse(basedError);
    }
}