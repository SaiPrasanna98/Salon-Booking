package com.zosh.party.service.exception;

import com.zosh.party.service.payload.response.ExceptionResponse;
import lombok.EqualsAndHashCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@Controller
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> ExceptiionHandler(Exception ex, WebRequest req){
        ExceptionResponse response=new ExceptionResponse(
                ex.getMessage(),
                req.getDescription(false), LocalDateTime.now()
        );
        return ResponseEntity.ok(response);
    }
}
