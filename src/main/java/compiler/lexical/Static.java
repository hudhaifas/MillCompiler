/*
 * Static.java
 *
 * Created on May 11, 2006, 12:16 PM
 *
 */
package compiler.lexical;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 *
 * @author	Hudhaifa Shatnawi Hesham al-Rousan Fares Athamneh
 */
public class Static {

    public static String[] getLines(String text) {
        String[] lines = text.split("\n");
        return lines;

    } // END: getLineNumber

    public static int getLinesNumber(String text) {
        String[] lines = text.split("\n");
        return lines.length;

    } // END: getLinesNumber

    public static String[] getTokens(String text) {
        StringTokenizer token = new StringTokenizer(text, " ,;{}()[]=+*/-", true);
        String[] tokensArray = new String[token.countTokens()];
        int i = 0;

        while (token.hasMoreTokens()) {
            tokensArray[i] = token.nextToken();
            i++;

        } // END: while

        return tokensArray;

    } // END: getTokens

    public static boolean isDigit(char c) {
        int d = (int) (c);
        if (d < 48 || d > 57) {
            return false;
        }

        return true;

    } // END: isDigit

    public static boolean isIdentefier(String token) {
        char[] c = token.toCharArray();
        if (isDigit(c[0]) || isNonChar(c[0])) {
            return false;
        }

        for (int i = 1; i < c.length; i++) {
            if (isNonChar(c[i])) {
                return false;
            }
        }

        return true;

    } // END: isIdentefier

    public static boolean isNonChar(char c) {
        for (char cc : notChar) {
            if (c == cc) {
                return true;
            }
        }

        return false;

    } // END: isNonChar

    public static boolean isNumber(String t) {
        int dot = 0;
        char[] c = t.toCharArray();

        for (int i = 0; i < c.length; i++) {
            if (c[i] == '.') {
                dot++;
            }
        }

        if (dot > 2) {
            return false;
        }

        for (int i = 0; i < c.length; i++) {
            if (!isDigit(c[i]) && c[i] != '.') {
                return false;
            }
        }

        return true;

    } // END: isNumber

    public static String read(File file) throws IOException {
        String content = new String();
        FileInputStream input = new FileInputStream(file);
        int n;

        while ((n = input.available()) > 0) {
            byte[] buffer = new byte[n];
            int result = input.read(buffer);

            if (result == -1) {
                break;
            }

            String string = new String(buffer);
            content += string;

        } // END: while
        input.close();
        return content;

    } // END: read

    public static void write(File file, String text)
            throws IOException {
        file.createNewFile();
        FileOutputStream output = new FileOutputStream(file);
        byte[] buffer = text.getBytes();
        output.write(buffer);
        output.close();

    } // END: write

    public static final char[] notChar = {'!', '@', '#', '%', '^', '?', '\'', '"', '\\', '|', '~', '.', ';', ',', '{', '}', '[', ']', '+', '-', '*', '/', '(', ')', '=', '<', '>', '!'};

}
