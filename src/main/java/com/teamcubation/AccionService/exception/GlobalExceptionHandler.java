package com.teamcubation.AccionService.exception;

import com.teamcubation.AccionService.domain.exception.DuplicatedStockException;
import com.teamcubation.AccionService.domain.exception.InvalidStockEntityException;
import com.teamcubation.AccionService.domain.exception.InvalidStockModelException;
import com.teamcubation.AccionService.domain.exception.StockNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicatedStockException.class)
    public ResponseEntity<?> handleDuplicatedStockException(DuplicatedStockException e, HttpServletRequest request) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(this.createErrorMessage(e, request, HttpStatus.CONFLICT));
    }

    @ExceptionHandler(InvalidStockModelException.class)
    public ResponseEntity<?> handleInvalidStockModelException(InvalidStockModelException e, HttpServletRequest request) {

        return ResponseEntity
                .badRequest()
                .body(this.createErrorMessage(e, request, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(InvalidStockEntityException.class)
    public ResponseEntity<?> handleInvalidStockEntityException(InvalidStockEntityException e, HttpServletRequest request) {

        return ResponseEntity
                .badRequest()
                .body(this.createErrorMessage(e, request, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(StockNotFoundException.class)
    public ResponseEntity<?> handleStockNotFoundException(StockNotFoundException e, HttpServletRequest request) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(this.createErrorMessage(e, request, HttpStatus.NOT_FOUND));

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body(getValidationErrorsMap(ex));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception e, HttpServletRequest request) {

        return ResponseEntity
                .internalServerError()
                .body(this.createErrorMessage(e, request, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    private ErrorMessage createErrorMessage(Exception e, HttpServletRequest request, HttpStatus status) {
        return ErrorMessage.builder()
                .message(e.getMessage())
                .status(status.value())
                .path(request.getRequestURI())
                .method(request.getMethod())
                .build();
    }

    private static Map<String, String> getValidationErrorsMap(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            FieldError fieldError = (FieldError) error;
            String fieldName = fieldError.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
