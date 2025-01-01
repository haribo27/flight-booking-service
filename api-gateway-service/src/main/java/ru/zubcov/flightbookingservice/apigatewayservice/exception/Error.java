package ru.zubcov.flightbookingservice.apigatewayservice.exception;

import lombok.Value;

import java.time.Instant;

@Value
public class Error {

    public String message;
    public int HttpCode;
    public Instant timestamp;

    public Error(String message, int httpCode) {
        this.message = message;
        this.HttpCode = httpCode;
        this.timestamp = Instant.now();
    }
}
