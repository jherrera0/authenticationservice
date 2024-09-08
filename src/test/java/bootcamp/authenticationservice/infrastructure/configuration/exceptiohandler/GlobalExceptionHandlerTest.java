package bootcamp.authenticationservice.infrastructure.configuration.exceptiohandler;

import bootcamp.authenticationservice.domain.exception.*;
import bootcamp.authenticationservice.until.ExceptionConst;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GlobalExceptionHandlerTest {
    @Mock
    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }
    @Test
    void handleRoleEmptyException_ReturnsBadRequest() {
        RoleEmptyException exception = new RoleEmptyException();

        ResponseEntity<Object> response = globalExceptionHandler.handleRoleEmptyException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ExceptionConst.ROLE_EMPTY, response.getBody());
    }

    @Test
    void handleUserAgeEmptyException_ReturnsBadRequest() {
        UserBirthDateEmptyException exception = new UserBirthDateEmptyException();

        ResponseEntity<Object> response = globalExceptionHandler.handleUserAgeEmptyException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ExceptionConst.USER_BIRTH_DATE_EMPTY, response.getBody());
    }

    @Test
    void handleUserDocumentAlreadyExistsException_ReturnsConflict() {
        UserDocumentAlreadyExistsException exception = new UserDocumentAlreadyExistsException();

        ResponseEntity<Object> response = globalExceptionHandler.handleUserDocumentAlreadyExistsException(exception);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals(ExceptionConst.USER_DOCUMENT_ALREADY_EXISTS, response.getBody());
    }

    @Test
    void handleUserUnderAgeException_ReturnsBadRequest() {
        UserUnderAgeException exception = new UserUnderAgeException();

        ResponseEntity<Object> response = globalExceptionHandler.handleUserUnderAgeException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ExceptionConst.USER_BIRTH_DATE_INVALID, response.getBody());
    }

    @Test
    void handleUserIllegalPhoneFormatException_ReturnsBadRequest() {
        UserIllegalPhoneFormatException exception = new UserIllegalPhoneFormatException();

        ResponseEntity<Object> response = globalExceptionHandler.handleUserIllegalPhoneFormatException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ExceptionConst.USER_PHONE_INVALID, response.getBody());
    }

    @Test
    void handleUserEmailAlreadyExistException_ReturnsConflict() {
        UserEmailAlreadyExistException exception = new UserEmailAlreadyExistException();

        ResponseEntity<Object> response = globalExceptionHandler.handleUserEmailAlreadyExistException(exception);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals(ExceptionConst.USER_EMAIL_ALREADY_EXISTS, response.getBody());
    }

    @Test
    void handleBadCredentialsException_ReturnsForbidden() {
        BadCredentialsException exception = new BadCredentialsException(ExceptionConst.BAD_CREDENTIALS);

        ResponseEntity<Object> response = globalExceptionHandler.handleBadCredentialsException(exception);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        assertEquals(ExceptionConst.BAD_CREDENTIALS, response.getBody());
    }
    @Test
    void handleMethodArgumentNotValid_ReturnsBadRequestWithMultipleErrors() {
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);
        HttpHeaders headers = new HttpHeaders();
        HttpStatusCode status = HttpStatus.BAD_REQUEST;
        WebRequest request = mock(WebRequest.class);
        Map<String, Object> errors = new HashMap<>();
        errors.put("field1", "error message 1");
        errors.put("field2", "error message 2");

        when(exception.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getFieldErrors()).thenReturn(List.of(
                new FieldError("objectName", "field1", "error message 1"),
                new FieldError("objectName", "field2", "error message 2")
        ));

        ResponseEntity<Object> response = globalExceptionHandler.handleMethodArgumentNotValid(exception, headers, status, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(errors, response.getBody());
    }

  
}