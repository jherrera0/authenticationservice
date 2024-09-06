package bootcamp.authenticationservice.infrastructure.configuration.exceptiohandler;

import bootcamp.authenticationservice.domain.exception.UserEmailNotFoundException;
import bootcamp.authenticationservice.domain.exception.UserIllegalPhoneFormatException;
import bootcamp.authenticationservice.domain.exception.UserUnderAgeException;
import bootcamp.authenticationservice.domain.exception.UserDocumentAlreadyExistsException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserDocumentAlreadyExistsException.class)
    public ResponseEntity<Object> handleUserDocumentAlreadyExistsException(UserDocumentAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(UserEmailNotFoundException.class)
    public ResponseEntity<Object> handleUserEmailNotFoundException(UserEmailNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(UserUnderAgeException.class)
    public ResponseEntity<Object> handleUserUnderAgeException(UserUnderAgeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(UserIllegalPhoneFormatException.class)
    public ResponseEntity<Object> handleUserIllegalPhoneFormatException(UserIllegalPhoneFormatException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String,Object> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->

                errors.put(error.getField(),error.getDefaultMessage())
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
