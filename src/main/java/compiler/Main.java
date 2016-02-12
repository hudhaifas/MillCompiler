/*
 * Main.java
 *
 * Created on May 11, 2006, 12:13 PM
 *
 */
package compiler;

import compiler.lexical.LexicalAnalyzer;
import compiler.syntax.Static;
import compiler.syntax.SyntaxAnalyzer;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author	Hudhaifa Shatnawi Hesham al-Rousan Fares Athamneh
 */
public class Main {

    /**
     * Creates a new instance of Main
     */
    public Main() {
    } // END: Main constructor

    /**
     * @param args the command line arguments
     */
    public static void main(String... args) {
        /**
         * To create a Windows style
         */
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            LexicalAnalyzer.main();
            SyntaxAnalyzer.main(LexicalAnalyzer.getLeximes());
            createFrame();

        } catch (ClassNotFoundException classNotFoundException) {
            Toolkit.getDefaultToolkit().beep();
            System.err.println("Class not found exception.");

        } catch (InstantiationException instantiationException) {
            Toolkit.getDefaultToolkit().beep();
            System.err.println("Instantiation exception.");

        } catch (IllegalAccessException illegalAccessException) {
            Toolkit.getDefaultToolkit().beep();
            System.err.println("Illegal access exception.");

        } catch (UnsupportedLookAndFeelException unsupportedLookAndFeelException) {
            Toolkit.getDefaultToolkit().beep();
            System.err.println("Unsupported Look and Feel exception.");

        } catch (Exception exception) {
            Toolkit.getDefaultToolkit().beep();
            System.err.println("Exception in file processing.");

        } // END: try..catch

    } // END: main

    private static void createFrame() {
        JTextArea textArea = new JTextArea(Static.de, 30, 80);
        textArea.setEditable(false);
        JScrollPane scroller = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(null, scroller, "Derevation", JOptionPane.INFORMATION_MESSAGE);

    } // END: createFrame

} // END: class Main
