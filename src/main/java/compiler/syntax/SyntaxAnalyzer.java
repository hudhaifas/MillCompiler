/*
 * SyntaxAnalyzer.java
 *
 * Created on May 11, 2006, 12:24 PM
 *
 */
package compiler.syntax;

import java.io.IOException;
import java.util.Stack;

/**
 *
 * @author	Hudhaifa Shatnawi Hesham al-Rousan Fares Athamneh
 */
public class SyntaxAnalyzer {

    /**
     * Creates a new instance of SyntaxAnalyzer
     */
    public SyntaxAnalyzer(String arg0) {
        code = Static.getCode(arg0);
        position = Static.getPosition(arg0);
        program();

    } // END: SyntaxAnalyzer constructor

    public void assign() {
        // id.
        // =.
        if (code[count] == 20) {
            count++;
            if (code[count] == 70) {
                Static.de += "ASSIGN ==> id = EXPR\n";
                count++;
                expr();

            } else {
                Static.de += "ERROR missing = in line: " + position[count][0] + " index: " + position[count][1] + "\n";

            } // END: inner if..else

        } else {
            Static.de += "ERROR missing identefier in line: " + position[count][0] + " index: " + position[count][1] + "\n";

        } // END: outer if..else

    } // END: program

    public void block() {
        // {.
        if (code[count] == 62) {
            Static.de += "BLOCK ==> { STMT_LIST }\n";
            count++;

        } // END: if

        stmt_list();

        // }.
        if (code[count] == 63) {
            count++;

        } else { // ERROR
            Static.de += "ERROR missing { in line: " + position[count][0] + " index: " + position[count][1] + "\n";

        } // END: if..else

    } // END: program

    public void cins() {
        // cin.
        if (code[count] == 13) {
            Static.de += "CINS ==> cin id ID_LIST\n";
            count++;

        } else {
            Static.de += "ERROR missing cin\n";

        } // END: if..else

        // id.
        if (code[count] == 20) {
            count++;
        } else {
            Static.de += "ERROR missing identefier in line: " + position[count][0] + " index: " + position[count][1] + "\n";
        }

        id_list();

    } // END: program

    public void couts() {
        // cout.
        if (code[count] == 12) {
            Static.de += "COUTS ==> cout EXPR REST_OUT\n";
            count++;
            expr();
            rest_out();

        } else {
            Static.de += "ERROR missing cout in line: " + position[count][0] + " index: " + position[count][1] + "\n";

        } // END: if..else

    } // END: program

    public void e() {
    } // END: e

    public void expr() {
        switch (code[count]) {
            // id, num, (
            case 20:
            case 30:
            case 60:
                Static.de += "EXPR ==> TERM EXPRD in line: " + position[count][0] + " index: " + position[count][1] + "\n";
                term();
                exprd();
                break;

            // ERROR
            default:
                Static.de += "ERROR missing identefier in line: " + position[count][0] + " index: " + position[count][1] + "\n";
                break;

        } // END: switch

    } // END: program

    public void exprd() {
        switch (code[count]) {
            // ), ;, ,
            case 61:
            case 65:
            case 64:
                Static.de += "EXPRD ==> e\n";
                e();
                break;

            // +.
            case 40:
                Static.de += "EXPRD ==> + TERM EXPRD\n";
                count++;
                term();
                exprd();
                break;

            // -.
            case 41:
                Static.de += "EXPRD ==> - TERM EXPRD\n";
                count++;
                term();
                exprd();
                break;

            // <, > , ==, <=, >= !=
            case 50:
            case 51:
            case 52:
            case 54:
            case 55:
            case 53:
                Static.de += "EXPRD ==> e\n";
                e();
                break;

            // ERROR
            default:
                Static.de += "ERROR missing operator or , or, ; or ) in line: " + position[count][0] + " index: " + position[count][1] + "\n";
                break;

        } // END: switch

    } // END: program

    public void factor() {
        switch (code[count]) {
            // id.
            // num.
            case 20:
                Static.de += "FACTOR ==> id\n";
                count++;
                break;

            case 30:
                Static.de += "FACTOR ==> num\n";
                count++;
                break;

            // (.
            case 60:
                Static.de += "FACTOR ==> ( EXPR )\n";
                count++;
                expr();
                switch (code[count]) {
                    // ).
                    case 61:
                        count++;
                        break;

                    default:
                        Static.de += "ERROR missing ) in line: " + position[count][0] + " index: " + position[count][1] + "\n";
                        break;

                } // END: inner switch
                break;

            // ERROR
            default:
                Static.de += "ERROR missing identefier or (\n";
                break;

        } // END: switch

    } // END: program

    public String getToken(int t, int w) {
        return Static.table[t][w];

    } // END: getToken

    public void id_list() {
        // ,.
        // id.
        if (code[count] == 64) {
            count++;
            if (code[count] == 20) {
                Static.de += "ID_LIST ? , id ID_LIST\n";
                count++;
                id_list();

            } else if (code[count] == 65) { // ;
                Static.de += "ID_LIST ==> e\n";
                e();

            } else { // ERROR
                Static.de += "ERROR missing identefier in line: " + position[count][0] + " index: " + position[count][1] + "\n";

            } // END: inner if..else

        } else if (code[count] == 65) { // ;
            Static.de += "ID_LIST ==> e\n";
            e();

        } else { // ERROR
            Static.de += "ERROR missing , or ; in line: " + position[count][0] + " index: " + position[count][1] + "\n";

        } // END: outer if..else

    } // END: program

    public void ident() {
        // real.
        // id.
        if (code[count] == 10) {
            count++;

            if (code[count] == 20) {
                Static.de += "IDENT ==> real id ID_LIST\n";
                count++;
                id_list();

            } else {
                Static.de += "ERROR missing identefier in line: " + position[count][0] + " index: " + position[count][1] + "\n";

            } // END: inner if..else

        } // END: outer if

        // int.
        // id.
        if (code[count] == 11) {
            count++;

            if (code[count] == 20) {
                Static.de += "IDENT ==> int id ID_LIST\n";
                count++;
                id_list();

            } else {
                Static.de += "ERROR missing identefier\n";

            } // END: inner if.else

        } else {
            Static.de += "ERROR missing premitive keyword in line: " + position[count][0] + " index: " + position[count][1] + "\n";

        } // END: outer if..else

    } // END: program

    public void ifs() {
        // if.
        if (code[count] == 14) {
            Static.de += "IFS ==> if ( LOGICAL_EXPR ) BLOCK OPT_ELSE\n";
            count++;

        } else {
            Static.de += "ERROR missing if in line: " + position[count][0] + " index: " + position[count][1] + "\n";
        }

        // (.
        if (code[count] == 60) {
            count++;
        } else {
            Static.de += "ERROR missing ( in line: " + position[count][0] + " index: " + position[count][1] + "\n";
        }

        logical_expr();

        // ).
        if (code[count] == 61) {
            count++;
        } else {
            Static.de += "ERROR missing ) in line: " + position[count][0] + " index: " + position[count][1] + "\n";
        }

        block();
        opt_else();

    } // END: program

    public void logical_expr() {
        switch (code[count]) {
            // id, (
            case 20:
            case 60:
                Static.de += "LOGICAL_EXPR ==> EXPR REL_OP EXPR\n";
                expr();
                rel_op();
                expr();
                break;

            // ERROR
            default:
                Static.de += "ERROR missing identefier in line: " + position[count][0] + " index: " + position[count][1] + "\n";
                break;

        } // END: switch

    } // END: program

    public void opt_else() {
        switch (code[count]) {
            // }, real, int, id, cout, cin, if, while
            case 63:
            case 10:
            case 11:
            case 20:
            case 12:
            case 13:
            case 14:
            case 16:
                Static.de += "OPT_ELSE ==> e\n";
                e();
                break;

            // else.
            case 15:
                Static.de += "OPT_ELSE ==> else BLOCK\n";
                count++;
                block();
                break;

            // ERROR
            default:
                Static.de += "ERROR missing keyword or identefier in line: " + position[count][0] + " index: " + position[count][1] + "\n";
                break;

        } // END: switch

    } // END: program

    public void program() {
        switch (code[count]) {
            // main.
            case 17:
                Static.de += "PROGRAM ==> main BLOCK\n";
                count++;
                block();
                break;

            // ERROR
            default:
                Static.de += "ERROR missing main in line: " + position[count][0] + " index: " + position[count][1] + "\n";
                break;

        } // END: switch

    } // END: program

    public void rel_op() {
        switch (code[count]) {
            // <.
            case 50:
                Static.de += "REL_OP ==> <\n";
                count++;
                break;

            // >.
            case 51:
                Static.de += "REL_OP ==> >\n";
                count++;
                break;

            // ==.
            case 52:
                Static.de += "REL_OP ==> ==\n";
                count++;
                break;

            // <=.
            case 54:
                Static.de += "REL_OP ==> <=\n";
                count++;
                break;

            // >=.
            case 55:
                Static.de += "REL_OP ==> >=\n";
                count++;
                break;

            // !=.
            case 53:
                Static.de += "REL_OP ==> !=\n";
                count++;
                break;

            // ERROR
            default:
                Static.de += "ERROR missing operator in line: " + position[count][0] + " index: " + position[count][1] + "\n";
                break;

        } // END: switch

    } // END: program

    public void rest_out() {
        switch (code[count]) {
            // ;
            case 65:
                Static.de += "REST_OUT ==> e\n";
                e();
                break;

            // ,.
            case 64:
                Static.de += "REST_OUT ==> , EXPR REST_OUT\n";
                count++;
                expr();
                break;

            // ERROR
            default:
                Static.de += "ERROR missing ; or , in line: " + position[count][0] + " index: " + position[count][1] + "\n";
                break;

        } // END: switch

    } // END: program

    public void stmt() {
        switch (code[count]) {
            // real, int
            case 10:
            case 11:
                Static.de += "STMT ==> IDENT\n";
                ident();
                break;

            // id
            case 20:
                Static.de += "STMT ==> ASSIGN\n";
                assign();
                break;

            // cout
            case 12:
                Static.de += "STMT ==> COUTS\n";
                couts();
                break;

            // cin
            case 13:
                Static.de += "STMT ==> CINS\n";
                cins();
                break;

            // ERROR
            default:
                Static.de += "ERROR missing keyword or identefier in line: " + position[count][0] + " index: " + position[count][1] + "\n";
                break;

        } // END: switch

    } // END: program

    public void stmt_list() {
        switch (code[count]) {
            // real, int, id, cout, cin
            case 10:
            case 11:
            case 20:
            case 12:
            case 13:
                Static.de += "STMT_LIST ==> STMT; STMT_LIST\n";
                stmt();
                switch (code[count]) {
                    case 65:
                        count++;
                        break;

                    default:
                        Static.de += "ERROR missing ; in line: " + position[count][0] + " index: " + position[count][1] + "\n";
                        break;

                } // END: inner switch
                stmt_list();
                break;

            // if
            case 14:
                Static.de += "STMT_LIST ==> IFS STMT_LIST\n";
                ifs();
                break;

            // while
            case 16:
                Static.de += "STMT_LIST ==> WHILES STMT_LIST\n";
                whiles();
                break;

            case 63:
                Static.de += "STMT_LIST ==> e\n";
                e();
                break;

            // ERROR
            default:
                Static.de += "ERROR missing keyword or identefier in line: " + position[count][0] + " index: " + position[count][1] + "\n";
                break;

        } // END: switch

    } // END: program

    public void term() {
        switch (code[count]) {
            // id, num, (
            case 20:
            case 30:
            case 60:
                Static.de += "TERM ==> FACTOR TERMD\n";
                factor();
                termd();
                break;

            // ERROR
            default:
                Static.de += "ERROR missing identefier in line: " + position[count][0] + " index: " + position[count][1] + "\n";
                break;

        } // END: switch

    } // END: program

    public void termd() {
        switch (code[count]) {
            // ), ;, ,, +, -, <, > , ==, <=, >= !=
            case 61:
            case 65:
            case 64:
            case 40:
            case 41:
            case 50:
            case 51:
            case 52:
            case 54:
            case 55:
            case 53:
                Static.de += "TERMD ==> e\n";
                e();
                break;

            // *.
            case 42:
                Static.de += "TERMD ==> * FACTOR TERMD\n";
                count++;
                factor();
                termd();
                break;

            // /.
            case 43:
                Static.de += "TERMD ==> / FACTOR TERMD\n";
                count++;
                factor();
                termd();
                break;

            // ERROR
            default:
                Static.de += "ERROR missing operator or , or ; or , in line: " + position[count][0] + " index: " + position[count][1] + "\n";
                break;

        } // END: switch

    } // END: program

    public void whiles() {
        Static.de += "WHILES ==> while ( LOGICAL_EXPR ) BLOCK\n";
        // while.
        if (code[count] == 16) {
            count++;
        } else {
            Static.de += "ERROR missing while in line: " + position[count][0] + " index: " + position[count][1] + "\n";
        }

        // (.
        if (code[count] == 60) {
            count++;
        } else {
            Static.de += "ERROR missing ( in line: " + position[count][0] + " index: " + position[count][1] + "\n";
        }

        logical_expr();

        // ).
        if (code[count] == 61) {
            count++;
        } else {
            Static.de += "ERROR missing ) in line: " + position[count][0] + " index: " + position[count][1] + "\n";
        }

        block();

    } // END: program

    public static void main(String... args) throws IOException, InterruptedException {
        new SyntaxAnalyzer(args[0]);

    } // END: main
    protected int[] code;
    protected int count;
    protected String[] leximes;
    protected int[][] position;
    protected Stack stack;

} // END: class SyntaxAnalyzer
