package com.cmarinescu.backend.common.exception.handler;

import com.cmarinescu.backend.common.exception.BaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class BaseExceptionHandler {

    protected ResponseEntity<Object> buildResponse(BaseException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();

        Instant timestamp = Instant.now();
        body.put("timestamp", DateTimeFormatter.ISO_INSTANT.format(timestamp));
        body.put("status", ex.getStatus().value());
        body.put("error", ex.getStatus().getReasonPhrase());
        body.put("message", ex.getMessage());
        body.put("path", request.getDescription(false).replace("uri=", ""));

        return ResponseEntity.status(ex.getStatus()).body(body);
    }
}
