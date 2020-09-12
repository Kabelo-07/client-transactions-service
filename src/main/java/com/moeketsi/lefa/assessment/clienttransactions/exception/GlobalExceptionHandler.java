package com.moeketsi.lefa.assessment.clienttransactions.exception;

import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static String toFriendlyMessage(final List<ObjectError> errors) {

        final List<String> errorMessages = new ArrayList<>();

        for (ObjectError error : errors) {
            String msg = error.getDefaultMessage();
            if (msg != null) {
                if (!errorMessages.contains(msg)) {
                    errorMessages.add(msg);
                }
            }
        }
        final StringBuilder friendlyMessage = new StringBuilder();

        if (errorMessages.size() == 1) {
            friendlyMessage.append(errorMessages.get(0));
        } else {
            for (String error : errorMessages) {
                friendlyMessage.append(error).append("|");
            }
        }
        return friendlyMessage.toString();
    }

    @SneakyThrows
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Problem problem = Problem.builder()
                .instance(new URI(((ServletWebRequest) request).getRequest().getRequestURI()))
                .title("Request Validation Error")
                .status(status.value())
                .detail(toFriendlyMessage(ex.getBindingResult().getAllErrors()))
                .build();

        return ResponseEntity.ok(problem);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<Object> handleMissingTypesException(
            Exception ex, WebRequest request) throws URISyntaxException {

        Problem problem = Problem.builder()
                .instance(new URI(((ServletWebRequest) request).getRequest().getRequestURI()))
                .title("Request Validation Error")
                .status(HttpStatus.BAD_REQUEST.value())
                .detail(ex.getMessage())
                .build();

        return ResponseEntity.ok(problem);
    }
}



