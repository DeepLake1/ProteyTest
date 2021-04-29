package com.example.restservice.web.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@RestControllerAdvice(annotations = RestController.class)
public class UserExceptionHandler {

    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
    protected ResponseEntity<UserException> handleBindValidationEx(HttpServletRequest request, BindException ex) {
        String[] details = ex.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toArray(String[]::new);
        UserException apiError = new UserException("Validation error", details, HttpStatus.UNPROCESSABLE_ENTITY);
        return new ResponseEntity<>(apiError, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<UserException> handleDuplicateCredentials(DataIntegrityViolationException ex) {
        UserException apiError = new UserException("Wrong Credentials", new String[]{findCauseUsingPlainJava(ex).toString()}, HttpStatus.CONFLICT);
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({ConversionFailedException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<UserException> handleConversionEx(RuntimeException ex) {
        UserException apiError = new UserException("Invalid status. Please enter status 'ONLINE', 'OFFLINE' or 'AWAY'", new String[]{ex.getMessage()}, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NoSuchElementException.class, EmptyResultDataAccessException.class})
    protected ResponseEntity<UserException> handleEntityNotFoundEx(HttpServletRequest request, Exception ex) {
        UserException apiError = new UserException("Entity Not Found Exception", new String[]{ex.getMessage()}, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<UserException> wrongRequest(HttpServletRequest request, NoHandlerFoundException ex) {
        UserException apiError = new UserException("Wrong request", new String[]{ex.getMessage()}, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<UserException> defaultErrorHandler(HttpServletRequest req, Exception ex) {
        UserException apiError = new UserException("Error", new String[]{ex.getMessage()}, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static Throwable findCauseUsingPlainJava(Throwable throwable) {
        Objects.requireNonNull(throwable);
        Throwable rootCause = throwable;
        while (rootCause.getCause() != null && rootCause.getCause() != rootCause) {
            rootCause = rootCause.getCause();
        }
        return rootCause;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class UserException {
        private HttpStatus httpStatus;
        private String message;
        private String[] debugMessage;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private List<String> errors;

        public UserException(String message, String[] debugMessage, HttpStatus httpStatus) {
            this.message = message;
            this.debugMessage = debugMessage;
            this.httpStatus = httpStatus;
        }
    }


}
