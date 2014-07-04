package it.sevenbits.stream;

import it.sevenbits.stream.OutStream;

/**
 * Created with IntelliJ IDEA.
 * User: kirill
 * Date: 31.10.13
 * Time: 8:49
 * To change this template use File | Settings | File Templates.
 */
public class StringOutStream implements OutStream {
    private String stringData;

    public StringOutStream() {
        stringData = "";
    }

    public void writeSymbol(char symbol) {
        stringData += symbol;
    }

    public void close() {
    }

    public String getStringData() {
        return stringData;
    }
}
