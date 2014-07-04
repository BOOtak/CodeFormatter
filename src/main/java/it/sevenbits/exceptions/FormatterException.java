package it.sevenbits.exceptions;

/**
 * Created by kirill on 01.07.14.
 */
public class FormatterException extends Exception {
    public FormatterException(String message, Throwable cause) {
        super(message, cause);
    }
    public FormatterException(String message) {super(message);}
}