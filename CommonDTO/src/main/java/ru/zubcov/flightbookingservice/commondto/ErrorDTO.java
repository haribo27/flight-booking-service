package ru.zubcov.flightbookingservice.commondto;

import lombok.Value;

import java.time.Instant;

@Value
public class ErrorDTO {

    public String message;
    public int HttpCode;
    public Instant timestamp;

    public ErrorDTO(String message, int httpCode) {
        this.message = message;
        this.HttpCode = httpCode;
        this.timestamp = Instant.now();
    }
}