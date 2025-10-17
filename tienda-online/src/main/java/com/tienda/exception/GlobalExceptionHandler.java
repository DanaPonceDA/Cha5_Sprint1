package com.tienda.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler to capture validation errors
 * and return clear HTTP responses.
 *
 * @version 1.0.0
 * <p>
 * 1.0.0 - Initial version with handlers for validation errors (400) 
 * and generic exceptions (500).
 * </p>
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles validation errors resulting from method arguments annotated with @Valid (e.g., @RequestBody).
     * Converts binding errors into a map of field names and error messages.
     *
     * @param ex the exception that occurred
     * @param headers the HTTP headers
     * @param status the HTTP status code
     * @param request the current web request
     * @return a ResponseEntity with a 400 Bad Request status and a map of validation errors.
     * @since 1.0.0
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Catches all unhandled exceptions and returns a generic 500 Internal Server Error.
     *
     * @param ex the unhandled exception
     * @return a ResponseEntity with a 500 Internal Server Error status and the exception message.
     * @since 1.0.0
     */

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAll(Exception ex) {
        Map<String, String> body = new HashMap<>();
        body.put("error", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
