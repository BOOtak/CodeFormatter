package it.sevenbits.stream;

import it.sevenbits.exceptions.StreamException;

/**
 * Provides an interface for reading per byte from string or from file.
 * User: kirill
 * Date: 23.10.13
 * Time: 14:40
 */
public interface InStream {
    /**
     * Reads next symbol from stream.
     * @return next symbol from stream,
     */
    public char readSymbol() throws StreamException;

    /**
     * Checks whether or not we reached end of stream.
     * @return whether or not we reached end of stream.
     */
    public boolean isEnd() throws StreamException;

    /**
     * Closes stream
     */
    public void close() throws StreamException;
}
