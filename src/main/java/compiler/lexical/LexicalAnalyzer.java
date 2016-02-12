/*
 * LexicalAnalyzer.java
 *
 * Created on May 11, 2006, 12:16 PM
 *
 */
package compiler.lexical;

import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author	Hudhaifa Shatnawi Hesham al-Rousan Fares Athamneh
 */
public class LexicalAnalyzer {

    /**
     * Creates a new instance of LexicalAnalyzer
     */
    public LexicalAnalyzer()
            throws IOException, InterruptedException {
        leximes = new String();
        errMsg = new String();
        cin = new Scanner(System.in);
        System.out.println("Enter file name without extention: (e.g; test)");
        String fileName = cin.next();

        File toRead = new File(fileName + ".cmm");
        toCode(toRead);

        File toWrite1 = new File(fileName + ".lex");
        Static.write(toWrite1, leximes);

        File toWrite2 = new File(fileName + ".sym");
        String symbols = new String();
        for (int j = 0; j < Tables.id.size(); j++) {
            symbols += Tables.id.get(j) + "\n";
        }

        Static.write(toWrite2, symbols);
        Thread.sleep(2500);
        Toolkit.getDefaultToolkit().beep();
        if (err > 0) {
            Toolkit.getDefaultToolkit().beep();
            System.err.println(errMsg);
            Thread.sleep(1500);
            Toolkit.getDefaultToolkit().beep();
            System.err.println(err + (err > 1 ? " errors" : "error"));
            Thread.sleep(1500);
            Toolkit.getDefaultToolkit().beep();
            System.err.println("\nTool completed with exit code 1");
            return;

        } // END: if
        System.err.println("Tool completed successfully");

    } // END: LexicalAnalyzer constructor

    public void addError(String word, int lineNo, int wordStart) {
        errMsg += "Sysntax error in Line: " + lineNo + " Character: " + wordStart + "\n\t" + word.trim() + " is not a correct word in C--\n\n";
        err++;

    } // END: addError

    public void addToleximes(String arg0, String arg1, String arg2, String arg3) {
        leximes += "<" + arg0.trim() + ", " + arg1.trim() + ", " + arg2.trim() + ", " + arg3.trim() + ">\n";

    } // END: addToleximes

    public void processLine(String line, int lineNo) {
        int wordStart = 1;
        int charNo = 1;
        String[] words = Static.getTokens(line);
        for (String word : words) {
            charNo += word.length();
            if (!word.equals(" ")) {
                processWord(word.toLowerCase(), lineNo, wordStart);
                wordStart = charNo;

            } // END: if

        } // END: for

    } // END: processLine

    public void processWord(String word, int lineNo, int wordStart) {
        if (Static.isIdentefier(word)) {
            String[] key = Tables.isKeyword(word);
            if (key.length != 0) {
                addToleximes("" + lineNo, "" + wordStart, key[0], key[1]);
                return;

            } // END: inner if

        } else if (Static.isNumber(word)) {
            String[] num = Tables.isNum(word);
            if (num.length != 0) {
                addToleximes("" + lineNo, "" + wordStart, num[0], num[1]);
                return;

            } // END: inner if

        } // END: outer if..else

        String[] op = Tables.isOperatop(word);
        if (op.length != 0) {
            addToleximes("" + lineNo, "" + wordStart, op[0], op[1]);
            return;

        } // END: outer if..else

        addError(word, lineNo, wordStart);

    } // END: processWord

    public void toCode(File file) throws IOException {
        String code = Static.read(file);
        String[] lines = Static.getLines(code);
        int lineNo = 1;
        for (String line : lines) {
            processLine(line.trim(), lineNo++);

        } // END: for

    } // END: compile

    public static String getLeximes() {
        return leximes;

    } // END: getLeximes

    public static void main(String... args) throws IOException, InterruptedException {
        new LexicalAnalyzer();

    } // END: main
    protected Scanner cin;
    protected int err;
    protected static String errMsg;
    protected static String leximes;

}
