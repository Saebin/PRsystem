package edu.hkcity.cs;

import java.io.*;
import java.util.Scanner;

/**
 * The Class Input.
 */
public class Input {
    private Scanner scanner = null;

    /** The original file name. */
    private String originalFileName;

    /** The target file name. */
    private String targetFileName;

    /** The original file. */
    private String originalFile;

    /** The target file. */
    private String targetFile;

    /**
     * Instantiates a new input.
     */
    public Input() {
        originalFileName = getFilename("Please enter original name:");
        targetFileName = getFilename("Please enter target name:");
    }
    /**
     * Instantiates a new input.
     *
     * @param oriName
     *            the ori name
     * @param tarName
     *            the tar name
     */
    public Input(String oriName, String tarName) {
        originalFileName = oriName;
        targetFileName = tarName;
    }
    /**
     * Gets the filename.
     *
     * @param msg
     *            the msg
     * @return the filename
     */
    private String getFilename(String msg) {
        if (scanner==null)
            scanner = new Scanner(System.in);
        String fileName = null;
        while (fileName == null) {
            System.out.print(msg);
            fileName = scanner.next();
            try {
                new FileInputStream(fileName);
            } catch (FileNotFoundException e) {
                System.out.println("File Not Found:" + fileName);
                fileName = null;
            }
        }
        return fileName;
    }
   
    /**
     * Gets the input.
     *
     * @return the input
     * @throws IOException 
     */
    public void getInput() throws IOException {
        File oriFile = new File(originalFileName);
        File tarFile = new File(targetFileName);
        InputStream oriStream = new FileInputStream(oriFile);
        InputStream tarStream = new FileInputStream(tarFile);
        byte[] oriBuf = new byte[(int) oriFile.length()];
        byte[] tarBuf = new byte[(int) tarFile.length()];

        oriStream.read(oriBuf);
        tarStream.read(tarBuf);
		
        oriStream.close();
        tarStream.close();

        originalFile = new String(oriBuf);
        targetFile = new String(tarBuf);

        new LineByLineComparar(originalFile, targetFile).compare(
                new Formatter(), new Output());
        new FuncByFuncComparar(originalFile, targetFile).compare(
                new Formatter(), new Output());
        new RegexComparar(originalFile, targetFile).compare(
                new Formatter(), new Output());
        new CosSimComparar(originalFile, targetFile).compare(
                new Formatter(), new Output());
    }

    /**
     * Gets the original file.
     *
     * @return the original file
     */
    public String getOriginalFile() {
        return originalFile;
    }

    /**
     * Gets the target file.
     *
     * @return the target file
     */
    public String getTargetFile() {
        return targetFile;
    }
}
