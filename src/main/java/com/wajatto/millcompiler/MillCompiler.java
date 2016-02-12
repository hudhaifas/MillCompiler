package com.wajatto.millcompiler;

/*
 * MillCompiler.java
 * No copyright reserved !� 2006
 * Created on February 24, 2006, 1:13 AM
 */

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Hudhaifa Shatnawi
 * @version
 */
public class MillCompiler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /**
         * To create a Windows style
         */
        try {
//            UIManager.setLookAndFeel(lookAndFeel);
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        } catch (ClassNotFoundException classNotFoundException) {
            System.err.println("Class not found exception.");

        } catch (InstantiationException instantiationException) {
            System.err.println("Instantiation exception.");

        } catch (IllegalAccessException illegalAccessException) {
            System.err.println("Illegal access exception.");

        } catch (UnsupportedLookAndFeelException unsupportedLookAndFeelException) {
            System.err.println("Unsupported Look and Feel exception.");

        } // END: try..catch

        new Splash();
        new MillCompiler();

    } // END: main

    /**
     * Creates a new instance of MillCompiler
     *
     * @since 1.0
     */
    public MillCompiler() {
        MillStatic.mainFrame = new MainFrame();

    } // END: MillCompiler constructor

    private static final String lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
} // END: class Compiler
