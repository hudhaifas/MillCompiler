package com.wajatto.millcompiler;

/*
 * MainFrame.java
 * No copyright reserved !� 2006
 * Created on February 24, 2006, 1:28 AM
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import javax.swing.BorderFactory;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import creator.MenuCreator;

/**
 * An extended of <code>javax.swing.JFrame</code> that adds support for
 * the JFC/Swing component architecture.
 * You can find task-oriented documentation about using <code>JFrame</code>
 * in <em>The Java Tutorial</em>, in the section
 * <a href="http://java.sun.com/docs/books/tutorial/uiswing/components/frame.html">How to Make Frames</a>.
 * The <code>Runnable</code> interface implemented by ServerFrame class to be executed by a thread. The
 * class must define a method of no arguments called <code>run</code>.
 *
 * @see javax.swing.JRootPane
 * @see javax.swing.JFrame#setDefaultCloseOperation
 * @see java.awt.event.WindowListener#windowClosing
 * @see javax.swing.RootPaneContainer
 * @see java.lang.Thread
 * @see java.util.concurrent.Callable
 *
 * @beaninfo
 *      attribute: isContainer true
 *      attribute: containerDelegate getContentPane
 *    description: A toplevel window which can be minimized to an icon.
 *
 * @version 1.0
 * @author Hudhaifa Shatnawi
 */
public class MainFrame
        extends JFrame {

    /**
     * Creates a new, initially invisible <code>MainFrame</code> with the
     * specified title.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by <code>JComponent.getDefaultLocale</code>.
     *
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see Component#setSize
     * @see Component#setVisible
     * @see JComponent#getDefaultLocale
     * @since 1.0
     */
    public MainFrame() {
        super( "Mill Compiler" );

        setupMenus();
        addMenus();
        registrationMenus();
        setupComponents();
        addComponents();
        setupWindow();

    } // END: MainFrame constructor

    private void setupMenus() {
        menuBar = new JMenuBar();

        fileMenu = MenuCreator.creatMenu( "File", 'F' );
        toolMenu = MenuCreator.creatMenu( "Tool", 'T' );
        helpMenu = MenuCreator.creatMenu( "Help", 'H' );

        newMenuItem = MenuCreator.creatItem( "New", KeyEvent.VK_N, InputEvent.CTRL_MASK, 'N' );
        openMenuItem = MenuCreator.creatItem( "Open", KeyEvent.VK_O, InputEvent.CTRL_MASK, 'O' );
        closeMenuItem = MenuCreator.creatItem( "Close", 'C' );
        saveMenuItem = MenuCreator.creatItem( "Save", KeyEvent.VK_S, InputEvent.CTRL_MASK, 'S' );
        saveAsMenuItem = MenuCreator.creatItem( "Save As..", KeyEvent.VK_S, InputEvent.SHIFT_MASK | InputEvent.CTRL_MASK, 'A' );
        exitMenuItem = MenuCreator.creatItem( "Exit", KeyEvent.VK_F4, InputEvent.ALT_MASK, 'E' );
        runTokenizerMenuItem = MenuCreator.creatItem( "Run Tokenizer", KeyEvent.VK_R, InputEvent.ALT_MASK, 'R' );
        runTokenizerMenuItem.setEnabled( false );
        helpMenuItem = MenuCreator.creatItem( "Help", KeyEvent.VK_F1, 0, 'H' );
        aboutMenuItem = MenuCreator.creatItem( "About", KeyEvent.VK_F2, 0, 'A' );

    } // END: setupMenus

    private void addMenus() {
        fileMenu.add( newMenuItem );
        fileMenu.add( openMenuItem );
        fileMenu.add( closeMenuItem );
        fileMenu.addSeparator();
        fileMenu.add( saveMenuItem );
        fileMenu.add( saveAsMenuItem );
        fileMenu.addSeparator();
        fileMenu.add( exitMenuItem );

        toolMenu.add( runTokenizerMenuItem );

        helpMenu.add( helpMenuItem );
        helpMenu.addSeparator();
        helpMenu.add( aboutMenuItem );

        menuBar.add( fileMenu );
        menuBar.add( toolMenu );
        menuBar.add( helpMenu );

        setJMenuBar( menuBar );

    } // END: addMenus

    private void registrationMenus() {
        newMenuItem.addActionListener( new CompilerFrameListener() );
        openMenuItem.addActionListener( new CompilerFrameListener() );
        saveMenuItem.addActionListener( new CompilerFrameListener() );
        saveAsMenuItem.addActionListener( new CompilerFrameListener() );
        closeMenuItem.addActionListener( new CompilerFrameListener() );
        exitMenuItem.addActionListener( new CompilerFrameListener() );
        runTokenizerMenuItem.addActionListener( new CompilerFrameListener() );
        helpMenuItem.addActionListener( new CompilerFrameListener() );
        aboutMenuItem.addActionListener( new CompilerFrameListener() );

    } // END: registrationMenus

    /**
     * @see javax.swing.JFrame#setContentPane
     * @see java.awt.RootPaneContainer#getContentPane
     * @see javax.swing.JLabel#setIcon
     * @see javax.swing.JComponent#setPreferredSize
     * @see javax.swing.JComponent#setBorder
     * @see javax.swing.JTextArea#setFont
     * @see javax.swing.JTextComponent#setEditable
     * @since 1.0
     */
    private void setupComponents() {
        container = getContentPane();
        statusBarLabel = new JLabel();
        statusBarLabel.setBorder( BorderFactory.createEtchedBorder() );
        statusBarLabel.setPreferredSize( new Dimension( 38, 22 ) );

        desktop = new JDesktopPane();
        desktop.setBackground( new Color( 236,233,216 ) );

        documentsSelector = new SelectorScroller();
        splitPane = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT, documentsSelector, desktop );

    } // END: setupComponents

    /**
     * @see javax.swing.JFrame#getContentPane
     * @since 1.0
     */
    private void addComponents() {
        container.add( statusBarLabel, BorderLayout.SOUTH );
        container.add( splitPane, BorderLayout.CENTER );

    } // END: addComponents

    /**
     * @since 1.0
     */
    private void registration() {
    } // END: registration

    /**
     * @see java.awt.Toolkit#getScreenSize
     * @see java.awt.Component#setBounds
     * @see javax.swing.JFrame#setDefaultCloseOperation
     * @see javax.swing.JFrame#setResizable
     * @see javax.swing.JComponent#setVisible
     * @since 1.0
     */
    private void setupWindow() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds( ( screenSize.width - 600 ) / 2, ( screenSize.height - 500 ) / 2, 600, 500 );
        setIconImage( Toolkit.getDefaultToolkit().getImage( "images/MillIcon.gif" ) );
        setVisible( true );
        setDefaultCloseOperation( EXIT_ON_CLOSE );

    } // END: setupWindow

    public void setStatusBar( String label ) {
        statusBarLabel.setText( label );

    } //END: setStatusBar

    public void setRunEnabled( boolean enabled ) {
        runTokenizerMenuItem.setEnabled( enabled );

    } // END: setRunEditable

    public void setSelectedEditor( EditorFrame editor ) {
        desktop.setSelectedFrame( editor );

    } // END: setSelectedEditor

    /**
     * The listener class for receiving action events.
     * The class that is interested in processing an action event
     * and the object created with that class is registered with a component, using the component's
     * <code>addActionListener</code> method. When the action event
     * occurs, that object's <code>actionPerformed</code> method is
     * invoked.
     *
     * @see java.awt.event.ActionEvent
     * @see <a href="http://java.sun.com/docs/books/tutorial/post1.0/ui/eventmodel.html">Tutorial: Java 1.1 Event Model</a>
     * @see <a href="http://www.awl.com/cp/javaseries/jcl1_2.html">Reference: The Java Class Libraries ( update file )</a>
     * @since 1.0
     */
    private class CompilerFrameListener
            implements ActionListener {
        /**
         * Invoked when an action occurs.
         * @since 1.0
         */
        public void actionPerformed( ActionEvent ae ) {
            source = ae.getSource();
            if ( source instanceof JMenuItem ) {
                if ( source == newMenuItem ) {
					try {
						MillStatic.counter++;
						counter++;
						EditorFrame editor = new EditorFrame( true, counter );
						MillStatic.panels.add( counter - 1, editor );
						MillStatic.documents.addElement( "Document" + counter );
						editor.setIndex( MillStatic.documents.size() - 1 );
						desktop.add( editor );
						editor.setSelected( true );
						setSelectedEditor( editor );

					} catch ( PropertyVetoException propertyVetoException ) {
					} // END: try..catch

                } else if ( source == openMenuItem ) {
                    MillStatic.counter++;
                    EditorFrame editor = new EditorFrame( true, MillStatic.counter );
                    if ( editor.open() ) {
						try {
							MillStatic.panels.add( MillStatic.counter - 1, editor );
							desktop.add( editor );
							editor.setSelected( true );
							setSelectedEditor( editor );
						} catch ( PropertyVetoException propertyVetoException ) {
						} // END: try..catch

                    } else {
                        MillStatic.counter--;

                    } // END: if

                } else if ( source == saveMenuItem ) {
                    MillStatic.getCurrentEditor().save( false );

                } else if ( source == saveAsMenuItem ) {
                    MillStatic.getCurrentEditor().setFileName( "" );
                    MillStatic.getCurrentEditor().save( true );

                } else if ( source == closeMenuItem ) {
                    MillStatic.closeEditor();

                } else if ( source == exitMenuItem ) {
                    System.exit( 0 );

                } else if ( source == runTokenizerMenuItem ) {
                    MillStatic.getCurrentEditor().save( false );
                    new Tokenizer( MillStatic.getCurrentEditor().getFile() );

                } else if ( source == helpMenuItem ) {
                } else if ( source == aboutMenuItem ) {
                    JOptionPane.showMessageDialog( MainFrame.this, "This is a trial version of Mill Compiler", "About Mill Compiler", JOptionPane.INFORMATION_MESSAGE );

                } else {
                } // END: inner if..else

            } else {
            } // END: outer if..else

            if ( MillStatic.panels.size() > 0 )
                setRunEnabled( true );

            else
                setRunEnabled( false );

        } // END: actionPerformed

        private Object source;

    } // END: inner class CompilerFrameListener

    protected Container container;
    protected JMenuBar menuBar;
    protected JMenu fileMenu;
    protected JMenu toolMenu;
    protected JMenu helpMenu;
    protected JMenuItem newMenuItem;
    protected JMenuItem openMenuItem;
    protected JMenuItem saveMenuItem;
    protected JMenuItem saveAsMenuItem;
    protected JMenuItem closeMenuItem;
    protected JMenuItem exitMenuItem;
    protected JMenuItem runTokenizerMenuItem;
    protected JMenuItem helpMenuItem;
    protected JMenuItem aboutMenuItem;
    protected JLabel statusBarLabel;
    protected JDesktopPane desktop;
    protected SelectorScroller documentsSelector;
    protected JSplitPane splitPane;
    protected int counter;

} // END: class CompilerFrame