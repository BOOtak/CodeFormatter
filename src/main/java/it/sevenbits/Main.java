package it.sevenbits;

import it.sevenbits.exceptions.FormatterException;
import it.sevenbits.exceptions.StreamException;
import it.sevenbits.stream.FileInStream;
import it.sevenbits.stream.FileOutStream;
import it.sevenbits.stream.InStream;
import it.sevenbits.stream.OutStream;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Properties;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        String indentation = "";
        Properties prop = new Properties();
        try {
            prop.load(new java.io.FileInputStream("project.properties"));
            indentation = prop.getProperty("indentation");
        } catch (IOException e) {
            logger.warn("Error opening project.properties:", e);
            logger.warn("default indentation will be used");
            indentation = "    ";
        }

        String inputFilePath = "input.txt";
        String outputFilePath = "output.txt";
        File inputFile = new File(inputFilePath);
        File outputFile = new File(outputFilePath);

        CodeFormatter codeFormatter = new CodeFormatter();

        InStream inStream;
        try {
            inStream = new FileInStream(inputFile);
        } catch (StreamException e) {
            logger.error("failed to create input stream stream: ",e);
            throw new RuntimeException();
        }

        OutStream outStreamProvider;
        try {
            outStreamProvider = new FileOutStream(outputFile);
        } catch (StreamException e) {
            logger.error("Failed to create output stream stream: ", e);
            throw new RuntimeException();
        }

        try {
            if (codeFormatter.format(inStream, outStreamProvider, indentation)) {
                logger.info("Code formatted successfully");
            } else {
                logger.info("Code formatted with errors");
            }
        } catch (FormatterException e) {
            logger.error("Invalid braces count: ", e);
        }
    }
}
