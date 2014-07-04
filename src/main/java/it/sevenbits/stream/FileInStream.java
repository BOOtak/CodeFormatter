package it.sevenbits.stream;

import it.sevenbits.Main;
import it.sevenbits.exceptions.StreamException;
import it.sevenbits.stream.InStream;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Providecoms an implementation of InStream interface.
 * User: kirill
 * Date: 23.10.13
 * Time: 14:40
 *
 */

public class FileInStream implements InStream {

    static Logger logger = Logger.getLogger(Main.class);

    private java.io.FileInputStream in;
    private long read = 0;
    private long sizeOfStream = 0;

    public FileInStream(File file) throws StreamException {
        try {
            in = new java.io.FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new StreamException(e.getMessage(), e.getCause());
        }
        sizeOfStream = file.length();
    }

    public char readSymbol(){
        byte[] b;
        b = new byte[1];
        char result = 0;
        try {
            in.read(b);
        } catch (IOException e) {
            logger.warn("Can not get next symbol from input stream: ",e);
        }
        result = (char)b[0];
        read++;
        return result;
    }

    public boolean isEnd() {
        return (read == sizeOfStream);
    }

    public void close() {
        try {
            in.close();
        } catch (IOException e) {
            logger.warn(e.getStackTrace());
        }
    }
}
