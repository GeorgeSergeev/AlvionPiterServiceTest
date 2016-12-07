package ru.alvioneurope.sample.exception;

/**
 * Created by lkhruschev on 07.12.2016.
 * lkhruschev@alvioneurope.ru
 * Skype: levasfx
 */
public class ApiException extends RuntimeException {

    public ApiException() {
        super();
    }

    public ApiException(String message) {
        super(message);
    }
}
