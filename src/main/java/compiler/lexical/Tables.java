/*
 * Tables.java
 *
 * Created on May 11, 2006, 12:17 PM
 *
 */
package compiler.lexical;

import java.util.Vector;

/**
 *
 * @author	Hudhaifa Shatnawi Hesham al-Rousan Fares Athamneh
 */
public class Tables {

    public static String[] addId(String word) {
        id.add(word);
        return new String[]{"2", "0"};
        //return new String[] { "Id", word };

    } // END: addId

    public static String[] addNum(String word) {
        id.add(word);
        return new String[]{"3", "0"};
        //return new String[] { "Number", word };

    } // END: addId

    public static String[] isEqual(String word) {
        for (int j = 0; j < table[4].length; j++) {
            if (table[4][j].equals(word)) {
                return new String[]{"7", "" + j};
            }
        }
        //return new String[] { "Equal", word };

        return new String[]{};

    } // END: isKeyword

    public static String[] isId(String word) {
        for (int j = 0; j < id.size(); j++) {
            if (id.get(j).equals(word)) {
                return new String[]{"2", "0"};
            }
        }
        //return new String[] { "Id", word };

        return addId(word);

    } // END: isId

    public static String[] isKeyword(String word) {
        for (int j = 0; j < table[0].length; j++) {
            if (table[0][j].equals(word)) {
                return new String[]{"1", "" + j};
            }
        }
        //return new String[] { "Keyword", word };

        return isId(word);

    } // END: isKeyword

    public static String[] isNum(String word) {
        for (int j = 0; j < id.size(); j++) {
            if (id.get(j).equals(word)) {
                return new String[]{"3", "0"};
            }
        }
        //return new String[] { "Number", word };

        return addNum(word);

    } // END: isId

    public static String[] isOperatop(String word) {
        for (int j = 0; j < table[1].length; j++) {
            if (table[1][j].equals(word)) {
                return new String[]{"4", "" + j};
            }
        }
        //return new String[] { "Operator", word };

        return isRelOp(word);

    } // END: isKeyword

    public static String[] isRelOp(String word) {
        for (int j = 0; j < table[2].length; j++) {
            if (table[2][j].equals(word)) {
                return new String[]{"5", "" + j};
            }
        }
        //return new String[] { "RelOp", word };

        return isSpecialCharacters(word);

    } // END: isRelOp

    public static String[] isSpecialCharacters(String word) {
        for (int j = 0; j < table[3].length; j++) {
            if (table[3][j].equals(word)) {
                return new String[]{"6", "" + j};
            }
        }
        //return new String[] { "Special", word };

        return isEqual(word);

    } // END: isKeyword

    public static Vector id = new Vector<String>();															// 2    identifiers
    // 3    numbers
    public static final String[][] table = {
        // 0            1           2           3           4           5			6			7
        /*1*/{"real", "int", "cout", "cin", "if", "else", "while", "main"}, // 1    keywords
        /*4*/ {"+", "-", "*", "/"}, // 4    operators
        /*5*/ {"<", ">", "==", "!=", "<=", ">="}, // 5	relational Op
        /*6*/ {"(", ")", "{", "}", ",", ";", "[", "]"}, // 6    special characters
        /*7*/ {"="} // 7    equal
    };

}
