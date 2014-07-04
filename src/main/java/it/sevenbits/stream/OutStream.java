package it.sevenbits.stream;

import it.sevenbits.exceptions.StreamException;

import java.io.IOException;

/**
 * Interface to output single char or entire strings to file or to string.
 * User: kirill
 * Date: 16.10.13
 * Time: 20:07
 */
public interface OutStream {

    /**
     * Writes single character to output stream.
     * @param symbol Character.
     * @throws it.sevenbits.exceptions.StreamException
     */
    public void writeSymbol(char symbol) throws StreamException;

    /**
     * Closes the stream.
     */
    public void close() throws StreamException;
}
