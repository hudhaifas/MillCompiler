package com.wajatto.millcompiler;

/*
 * MillStatic.java
 * No copyright reserved !� 2006
 * Created on February 26, 2006, 7:30 PM
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author Hudhaifa Shatnawi
 * @version
 */
public class MillStatic {

    public static void closeEditor() {
        if (documentsList.getSelectedIndex() != -1) {
            EditorFrame editor = (EditorFrame) panels.get(documentsList.getSelectedIndex());
            editor.dispose();
            documents.removeElementAt(documentsList.getSelectedIndex());
            panels.remove(editor);
            counter--;

        } // END: if

    } // END: closeEditor

    public static EditorFrame getCurrentEditor() {
        return (EditorFrame) panels.get(documentsList.getSelectedIndex());

    } // END: getCurrentEditor

    public static int getLineNumber(String text) {
        String[] lines = text.split("\n");
        return lines.length;

    } // END: getLineNumber

    public static MainFrame getMainFrameInstance() {
        return mainFrame;

    } // END: getMainFrameInstance

    public static String[] getTokens(String text) {
        StringTokenizer token = new StringTokenizer(text);
        String[] tokensArray = new String[token.countTokens()];
        int i = 0;

        while (token.hasMoreTokens()) {
            tokensArray[i] = token.nextToken();
            i++;

        } // END: while

        return tokensArray;

    } // END: getTokens

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

    } // END: readFile

    public static String setfilePath(String lastName) {
        StringTokenizer oldName = new StringTokenizer(lastName, ".", false);
        String filePath = oldName.nextToken();

        return filePath;

    } // END: setfilePath

    public static int counter = 0;
    public static DefaultListModel documents = new DefaultListModel();
    public static JList documentsList;
    public static Vector panels = new Vector<EditorFrame>();
    public static boolean saved = true;
    protected static MainFrame mainFrame;

} // END: class MillStatic
