package it.sevenbits;

import static org.junit.Assert.*;

import it.sevenbits.stream.InStream;
import it.sevenbits.stream.StringInStream;
import org.junit.Test;


/**
 * Created with IntelliJ IDEA.
 * User: kirill
 * Date: 12.11.13
 * Time: 22:42
 */
public class InStreamTest {
    @Test
    public void testGetNextSymbol() throws Exception {
        String testString = "tort";
        char nextSymbol = 't';
        InStream inStream = new StringInStream(testString);
        char result = inStream.readSymbol();
        assertEquals("symbols must be equal", nextSymbol, result);
    }
}
