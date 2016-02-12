/*
 * Static.java
 *
 * Created on May 11, 2006, 12:24 PM
 *
 */
package compiler.syntax;

import java.util.StringTokenizer;
import java.util.Vector;

/**
 *
 * @author	Hudhaifa Shatnawi Hesham al-Rousan Fares Athamneh
 */
public class Static {

    public static int[] getCode(String arg0) {
        String[] leximes = arg0.split("\n");
        int[][] toCode = new int[leximes.length][2];

        for (int i = 0; i < leximes.length; i++) {
            toCode[i][0] = getType(leximes[i]);
            toCode[i][1] = getWord(leximes[i]);

        } // end: for

        int[] code = new int[toCode.length];
        for (int i = 0; i < code.length; i++) {
            code[i] = toCode[i][0] * 10 + toCode[i][1];
        }

        return code;

    } // END: getCode

    public static int getIndex(String lexime) {
        StringTokenizer token = new StringTokenizer(lexime, "<, >", false);
        token.nextToken();
        return Integer.parseInt(token.nextToken());

    } // END: getWord

    public static int getLine(String lexime) {
        StringTokenizer token = new StringTokenizer(lexime, "<, >", false);
        return Integer.parseInt(token.nextToken());

    } // END: getType

    public static int[][] getPosition(String arg0) {
        String[] leximes = arg0.split("\n");
        int[][] toPosition = new int[leximes.length][2];

        for (int i = 0; i < leximes.length; i++) {
            toPosition[i][0] = getLine(leximes[i]);
            toPosition[i][1] = getIndex(leximes[i]);

        } // end: for

        return toPosition;

    } // END: getCode

    public static int getType(String lexime) {
        StringTokenizer token = new StringTokenizer(lexime, "<, >", false);
        token.nextToken();
        token.nextToken();
        return Integer.parseInt(token.nextToken());

    } // END: getType

    public static int getWord(String lexime) {
        StringTokenizer token = new StringTokenizer(lexime, "<, >", false);
        token.nextToken();
        token.nextToken();
        token.nextToken();
        return Integer.parseInt(token.nextToken());

    } // END: getWord

    public static void printDerevation(Vector de) {
        for (int i = 0; i < de.size(); i++) {
            System.out.println(de.get(i));

        } // END: for

    } // END: printDerevation
    public static String de = "";
    public static final String[][] table = {
        // 0            1           2           3           4           5			6			7
        {"real", "int", "cout", "cin", "if", "else", "while", "main"}, // 1    keywords
        {"+", "-", "*", "/"}, // 4    operators
        {"<", ">", "==", "!=", "<=", ">="}, // 5	relational Op
        {"(", ")", "{", "}", ",", ";", "[", "]"}, // 6    special characters
        {"="} // 7    equal
    };

} // END: class Static
