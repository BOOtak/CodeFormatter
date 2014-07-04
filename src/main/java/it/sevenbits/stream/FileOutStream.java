package it.sevenbits.stream;

import it.sevenbits.Main;
import it.sevenbits.exceptions.StreamException;
import it.sevenbits.stream.OutStream;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Provides implementation of OutStream interface.
 * User: kirill
 * Date: 23.10.13
 * Time: 14:48
 */
public class FileOutStream implements OutStream {

    Logger logger = Logger.getLogger(Main.class);

    private java.io.FileOutputStream out;
    private String stringData;

    public FileOutStream(File file) throws StreamException {
        try {
            out = new java.io.FileOutputStream(file);
        } catch (FileNotFoundException e) {
            throw new StreamException(e.getMessage() ,e.getCause());
        }
    }

    public void writeSymbol(char symbol) throws StreamException {
        byte b = (byte) symbol;
        try {
            out.write(b);
        } catch (IOException e) {
            throw new StreamException(e.getMessage(), e.getCause());
        }
    }

    public void close() throws StreamException {
        try {
            out.close();
        } catch (IOException e) {
            throw new StreamException(e.getMessage(), e.getCause());
        }
    }
}
