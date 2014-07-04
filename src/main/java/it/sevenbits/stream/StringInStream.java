package it.sevenbits.stream;

import it.sevenbits.stream.InStream;

/**
 * Created with IntelliJ IDEA.
 * User: kirill
 * Date: 31.10.13
 * Time: 8:41
 * To change this template use File | Settings | File Templates.
 */
public class StringInStream implements InStream {
    private String stringData;
    private int sizeOfStream;
    private int read = 0;

    public StringInStream(String data) {
        stringData = data;
        sizeOfStream = data.length();
    }

    public char readSymbol() {
        char result = stringData.charAt(read);
        read++;
        return result;
    }

    public boolean isEnd() {
        return(read == sizeOfStream);
    }

    public void close() {
        return;
    }
}
