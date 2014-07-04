package it.sevenbits;

import it.sevenbits.exceptions.FormatterException;
import it.sevenbits.exceptions.StreamException;
import it.sevenbits.stream.InStream;
import it.sevenbits.stream.OutStream;
import org.apache.log4j.Logger;

/**
 * Class used to format java code (very primitive implementation).
 * User: kirill
 * Date: 09.10.13
 * Time: 14:24
 */
public class CodeFormatter {

    static Logger logger = Logger.getLogger(Main.class);
    /**
     * Method formats the code.
     * @param inStream Provides input stream containing unformatted code.
     * @param outStream Provides stream to output formatted code.
     * @param indentation Provides indentation of strings.
     * @throws it.sevenbits.exceptions.FormatterException When something goes wrong
     * @return Whether formatting operation was successful or not.
     */
    public boolean format(InStream inStream, OutStream outStream, String indentation) throws FormatterException {

        char currentChar = 0;
        char lastChar = 0;
        int braceCount = 0;

        boolean startOfLine = true;

        try {
            while (!inStream.isEnd()) {
                currentChar = inStream.readSymbol();
                if (lastChar == '{' || lastChar == '}' || lastChar == ';') {
                    if (currentChar != '\n') {
                        outStream.writeSymbol('\n');
                        startOfLine = true;
                    }
                }
                if (startOfLine) {
                    if (currentChar != '}') {
                        writeString(multiplyString(indentation, braceCount), outStream);
                    } else {
                        writeString(multiplyString(indentation, braceCount - 1), outStream);
                    }
                    startOfLine = false;
                }
                if (currentChar == '{') {
                    braceCount++;
                }
                if (currentChar == '}') {
                    braceCount--;
                }
                if (currentChar == '+' || currentChar == '-' || currentChar == '/' || currentChar == '*') {
                    if (currentChar == '*') {
                        if (lastChar != '.') {
                            outStream.writeSymbol(' ');
                        }
                    } else if (lastChar != ' ') {
                        outStream.writeSymbol(' ');
                    }
                }
                if (lastChar == '+' || lastChar == '-' || lastChar == '/' || lastChar == '*') {
                    if (lastChar == '*') {
                        if (currentChar != ';') {
                            outStream.writeSymbol(' ');
                        }
                    } else if (currentChar != ' ') {
                        outStream.writeSymbol(' ');
                    }
                }
                if (currentChar == '|') {
                    if (lastChar != '|' && lastChar != ' ') {
                        outStream.writeSymbol(' ');
                    }
                } else if (currentChar == '&') {
                    if (lastChar != '&' && lastChar != ' ') {
                        outStream.writeSymbol(' ');
                    }
                }
                if (lastChar == '|') {
                    if (currentChar != '|' && currentChar != ' ') {
                        outStream.writeSymbol(' ');
                    }
                } else if (lastChar == '&') {
                    if (currentChar != '&' && currentChar != ' ') {
                        outStream.writeSymbol(' ');
                    }
                }
                if ((currentChar == ' ' && lastChar != ' ' && lastChar != '(' && lastChar != '\n' && !startOfLine) || currentChar != ' ') {
                    outStream.writeSymbol(currentChar);
                }
                if (currentChar == '\n') {
                    startOfLine = true;
                }
                lastChar = currentChar;
            }

            if (lastChar != '\n') {
                outStream.writeSymbol('\n');
            }

            if (braceCount != 0) {
                throw new FormatterException(String.format("%d more left braces!", braceCount));
            }

            outStream.close();
            inStream.close();

        } catch (StreamException e) {
            throw new FormatterException(e.getMessage(), e.getCause());
        }

        return true;
    }

    private void writeString(String data, OutStream out) throws StreamException {
        byte[] bytes;
        bytes = data.getBytes();
        for (byte b : bytes) {
            out.writeSymbol((char) b);
        }
    }

    private String multiplyString(String source, int count) {
        String result = "";
        for (int i = 0; i < count; i++) {
            result = result.concat(source);
        }
        return result;
    }
}
