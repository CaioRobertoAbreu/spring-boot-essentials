package academy.devdojo.springboot2.controller.handler;

import academy.devdojo.springboot2.exception.Error;
import academy.devdojo.springboot2.exception.NotFoundException;
import academy.devdojo.springboot2.exception.ApiExceptionDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiExceptionDetails> handleNotFoundException(NotFoundException e){
        var details = ApiExceptionDetails.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(details);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        var details = new ApiExceptionDetails(HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(), ex.getMessage());

        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            var newError = new Error(fieldError.getField(), fieldError.getDefaultMessage());
            details.addError(newError);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(headers).body(details);
    }


    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        var details = new ApiExceptionDetails(status.value(), LocalDateTime.now(), ex.getMessage());

        return ResponseEntity.status(status).headers(headers).body(details);
    }
}
