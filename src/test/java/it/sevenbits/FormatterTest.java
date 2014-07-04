package it.sevenbits;

import it.sevenbits.exceptions.FormatterException;
import it.sevenbits.exceptions.StreamException;
import it.sevenbits.stream.*;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: kirill
 * Date: 30.10.13
 * Time: 23:43
 */
public class FormatterTest {
    @Test(expected = StreamException.class)
    public void testInvalidInputPath() throws StreamException {
        File invalidFile = new File("");
        CodeFormatter codeFormatter = new CodeFormatter();
        FileInStream inputStreamProvider = null;
        inputStreamProvider = new FileInStream(invalidFile);
        StringOutStream outputStreamProvider = new StringOutStream();
        String indentation = "   ";
        try {
            codeFormatter.format(inputStreamProvider, outputStreamProvider, indentation);
        } catch (FormatterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFormatOne() throws Exception {
        String testString = "public class Main {}";
        String result =
                "public class Main {\n" +
                "}\n" +
                "";
        InStream inStream = new StringInStream(testString);
        StringOutStream outStream = new StringOutStream();
        CodeFormatter formatter = new CodeFormatter();
        formatter.format(inStream, outStream, " ");
        assertEquals("Result must be properly formatted!", result, outStream.getStringData());
    }

    @Test
    public void testFormatTwo() throws Exception {
        String testString = "class Main {printf();}";
        String result =
                "class Main {\n" +
                "    printf();\n" +
                "}\n";
        InStream inStream = new StringInStream(testString);
        StringOutStream outStream = new StringOutStream();
        CodeFormatter formatter = new CodeFormatter();
        formatter.format(inStream, outStream, "    ");
        assertEquals("Result must be properly formatted!", result, outStream.getStringData());
    }

    @Test
    public void testReduceExtraSpaces() throws Exception {
        String testString =
                "public void main(          ) {\n" +
                "\n" +
                "}";
        String result =
                "public void main() {\n" +
                "    \n" +
                "}\n";
        InStream inStream = new StringInStream(testString);
        StringOutStream outStream = new StringOutStream();
        CodeFormatter formatter = new CodeFormatter();
        formatter.format(inStream, outStream, "    ");
        assertEquals("Extra spaces must be reduced!", result, outStream.getStringData());
    }

    @Test
    public void testFormatBraces() throws Exception {
        String testString = "{{{{{{{{}}}}}}}}";
        String result =
                "{\n" +
                "    {\n" +
                "        {\n" +
                "            {\n" +
                "                {\n" +
                "                    {\n" +
                "                        {\n" +
                "                            {\n" +
                "                            }\n" +
                "                        }\n" +
                "                    }\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}\n";
        InStream inStream = new StringInStream(testString);
        StringOutStream outStream = new StringOutStream();
        CodeFormatter formatter = new CodeFormatter();
        formatter.format(inStream, outStream, "    ");
        assertEquals("Result must be properly formatted!", result, outStream.getStringData());
    }

    @Test
    public void testPutSpacesNearoperators() throws Exception {
        String testString =
                "public void main(          ) {printf();scanf(a+2);if(a||b){return;}}";
        String result =
                "public void main() {\n" +
                "    printf();\n" +
                "    scanf(a + 2);\n" +
                "    if(a || b){\n" +
                "        return;\n" +
                "    }\n" +
                "}\n";
        InStream inStream = new StringInStream(testString);
        StringOutStream outStream = new StringOutStream();
        CodeFormatter formatter = new CodeFormatter();
        formatter.format(inStream, outStream, "    ");
        assertEquals("Extra spaces must be reduced!", result, outStream.getStringData());
    }

    @Test(expected = FormatterException.class)
    public void testInvalidBraces() throws FormatterException {
        String testString = "public class Main {{{{{df}}}}sdfsdf";
        InStream inStream = new StringInStream(testString);
        StringOutStream outStream = new StringOutStream();
        CodeFormatter formatter = new CodeFormatter();
        formatter.format(inStream, outStream, " ");
    }
}
