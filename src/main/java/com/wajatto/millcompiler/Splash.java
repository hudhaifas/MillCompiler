package com.wajatto.millcompiler;

/*
 * @(#)Splash.java
 * No copyright reserved !� 2006
 * Created on 11 February 2006, 23:11
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

/**
 * A <code>Splash</code> is class extendes a <code>JWindow</code> container that generate splash window period 5 second
 * while the application setup it's sitting. It does not have the title bar, window-management buttons, or other
 * trimmings associated with a <code>JFrame</code>, but it is still a "first-class citizen" of the user's desktop, and
 * can exist anywhere on it.
 *
 * @see JRootPane
 *
 * @beaninfo attribute: isContainer true attribute: containerDelegate getContentPane description: A toplevel window
 * which has no system border or controls.
 *
 * @version 1.0
 * @author Hudhaifa Shatnawi
 * @description	This class
 */
public class Splash
        extends JWindow {

    /**
     * Creates a splash with no specified owner. This window will not be focusable.
     * <p>
     * This constructor sets the component's locale property to the value returned by
     * <code>JComponent.getDefaultLocale</code>.
     *
     * @throws HeadlessException if <code>GraphicsEnvironment.isHeadless()</code> returns true.
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see #isFocusableWindow
     * @see JComponent#getDefaultLocale
     *
     * @author Hudhaifa Shatnawi
     * @since 1.0
     */
    public Splash() {
        JPanel mains = new JPanel(new BorderLayout());
        ImageIcon icon = new ImageIcon(IMAGE_ICON);
        mains.add(new JLabel(icon, SwingConstants.CENTER));
        getContentPane().add(mains);
        // Show the window
        pack();
        centerScreen();
        setVisible(true);
        if (time != 0) {
            try {
                Thread.sleep(time);
                dispose();

            } catch (InterruptedException interruptedException) {
                System.err.println("there is an interrupted in thread.");

            } // END: try..catch

        } // END: if

    } // END: Splash constructor

    /**
     * Simple method to set screen on center location
     *
     * @author Hudhaifa Shatnawi
     * @since 1.0
     */
    private void centerScreen() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((screenSize.getWidth() - getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - getHeight()) / 2);
        setLocation(x, y);

    } // END: centerScreen
    private final String IMAGE_ICON = "images/Mill.gif";
    private final int time = 5000;

} // END: class Splash
