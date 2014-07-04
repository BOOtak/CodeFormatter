package it.sevenbits.exceptions;

/**
 * Provides custom exception.
 * User: kirill
 * Date: 30.10.13
 * Time: 19:20
 */
public class StreamException extends Exception {
    public StreamException(String message, Throwable cause) {
        super(message, cause);
    }
}
