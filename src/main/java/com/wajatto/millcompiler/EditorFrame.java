package com.wajatto.millcompiler;

/*
 * EditorFrame.java
 * No copyright reserved !� 2006
 * Created on February 24, 2006, 1:08 PM
 */

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.filechooser.FileFilter;

/**
 * @version 1.0
 * @author Hudhaifa Shatnawi
 */
public class EditorFrame
        extends JInternalFrame {

    /**
     * Creates new form EditorFrame
     */
    public EditorFrame( boolean editable, int counter ) {
        super( "Document" + counter, false, false, true, true  );
        fileName = "";
        oldFileName = "Document" + counter;

        setupComponents( editable );
        addComponents();
        setupInternalFrame();

    } // END: EditorFrame constructor

    public void setIndex( int index ) {
        this.index = index;
        System.out.println( index );

    } // END: setIndex

    public int getIndex() {
        return index;

    } // END: getIndex

    /**
     * @see javax.swing.JInternalFrame#setContentPane
     * @see java.awt.RootPaneContainer#getContentPane
     * @see javax.swing.JTextArea#setFont
     * @see javax.swing.JTextComponent#setDisabledTextColor
     * @see javax.swing.JTextComponent#setEditable
     * @since 1.0
     */
    private void setupComponents( boolean editable ) {
        container = getContentPane();
        textArea = new JTextArea( 5, 20 );
        textArea.setEditable( editable );
        textArea.setFont( new java.awt.Font( "Lucida Console", 0, 14 ) );
        scroller = new JScrollPane( textArea );

    } // END: setupComponents

    /**
     * @see javax.swing.JInternalFrame#getContentPane
     * @since 1.0
     */
    private void addComponents() {
        container.add( scroller, BorderLayout.CENTER );

    } // END: addComponents

    /**
     * @since 1.0
     */
    private void registration() {
        addMouseListener( new EditorFrameListener() );
        addInternalFrameListener( new EditorFrameListener() );
        textArea.addMouseListener( new EditorFrameListener() );
        container.addMouseListener( new EditorFrameListener() );
        scroller.addMouseListener( new EditorFrameListener() );

    } // END: registration

    /**
     * @see javax.swing.JInternalFrame#setVisible
     * @since 1.0
     */
    private void setupInternalFrame() {
        setFrameIcon( new ImageIcon( "images/MillIcon.gif" ) );
        setSize( 400, 400 );
        setVisible( true );

    } // END: setupInternalFrame

    public void setText( String text ) {
        textArea.setText( text );

    } // END: setText

    public String getText() {
        return textArea.getText();

    } // END: getText

    public void setFileName( String fileName ) {
        this.fileName = fileName;

    } // END: setFileName

    public String getFileName() {
        return fileName;

    } // END: getFileName

    public byte[] getBytes() {
        return textArea.getText().getBytes() ;

    } // END: readTextArea

    public File getFile() {
        return document;

    } // END: getFile

    public void save( boolean forceSaveAs ) {

        if ( fileName.equals( "" ) || forceSaveAs ) {
            this.fileName = fileName;
            JFileChooser fileDialog = new JFileChooser();
            fileDialog.setDialogTitle( "Save file as" );
            fileDialog.setDialogType( JFileChooser.SAVE_DIALOG );
            fileDialog.setMultiSelectionEnabled( false );
            fileDialog.addChoosableFileFilter(
                    new FileFilter() {

                public boolean accept( File file ) {
                    String filename = file.getName();
                    return filename.endsWith( attribute );

                } // END: accept

                public String getDescription() {
                    return description;

                } // END: getDescription

                private String attribute = ".mill";
                private String description = "Mill (*.mill)";

            } );
            fileDialog.showSaveDialog( this );

            File file = fileDialog.getSelectedFile();
            if( file != null ) {
                fileName = file.getPath() + ( file.getPath().endsWith( ".mill" ) ? "" : ".mill" );
                try {
                    document = new File( fileName );
                    FileOutputStream output = new FileOutputStream( document );
                    output.write( getBytes() );
                    setTitle( document.getName() );
                    MillStatic.documents.removeElement( oldFileName );
                    MillStatic.documents.addElement( document.getName() );
                    oldFileName = document.getName();
                    output.close();

                } catch ( IOException ioException ) {
                    JOptionPane.showMessageDialog( this, ioException.getMessage() );

                } // END: try..catch

            } // END: if

        } else {
            try {
                document = new File( fileName );
                FileOutputStream output = new FileOutputStream( document );
                output.write( getBytes() );
                output.close();

            } catch ( IOException ex ) {
                JOptionPane.showMessageDialog( this, ex.getMessage() );

            } // END: try..catch

        } // END: if..else

    } // END: save

    public boolean open() {
        JFileChooser fileDialog = new JFileChooser();
        fileDialog.setDialogTitle( "Open image file" );
        fileDialog.setDialogType( JFileChooser.OPEN_DIALOG );
        fileDialog.setMultiSelectionEnabled( false );
        fileDialog.addChoosableFileFilter( new OpenFileFilter( ".txt", "Text (*.txt)" ) );
        fileDialog.addChoosableFileFilter( new OpenFileFilter( ".java", "Java (*.java)" ) );
        fileDialog.addChoosableFileFilter( new OpenFileFilter( ".code", "Code (*.code)" ) );
        fileDialog.addChoosableFileFilter( new OpenFileFilter( ".mill", "Mill (*.mill)" ) );
        fileDialog.showOpenDialog( null );
        document = fileDialog.getSelectedFile();
        fileName = document.getPath() + ( document.getPath().endsWith( ".mill" ) ? "" : ".mill" );
        if( document != null ) {
            if ( MillStatic.documents.contains( document.getName() ) )
                MillStatic.closeEditor();

            try {
                setText( readFile( document ) );
                MillStatic.documents.addElement( document.getName() );
                setIndex( MillStatic.documents.size() - 1 );
                setTitle( document.getName() );
                return true;

            } catch ( IOException ex ) {
                JOptionPane.showMessageDialog( this, ex.getMessage() );
                return false;

            } // END: try..catch

        } // END: if
        return false;

    } // END: open

    public String readFile( File file )
    throws IOException {
        String content = new String();
        FileInputStream input = new FileInputStream( file );
        int n;

        while ( ( n = input.available() ) > 0 ) {
            byte[] buffer = new byte[ n ];
            int result = input.read( buffer );

            if ( result == -1 )
                break;

            String string = new String( buffer );
            content = content + string;

        } // END: while

        input.close();
        return content;

    } // END: readFile

    public static EditorFrame getActiveEditor() {
		return activeEditor;

	} // END: getActiveEditor

    private class OpenFileFilter
            extends FileFilter {

        public OpenFileFilter( String attribute, String description ) {
            this.attribute = attribute;
            this.description = description;

        } // END: OpenFileFilter constructor

        public boolean accept( File file ) {
            String filename = file.getName();
            return filename.endsWith( attribute );

        } // END: accept

        public String getDescription() {
            return description;

        } // END: getDescription

        private String attribute = ".mill";
        private String description = "Mill (*.mill)";

    } // END: inner class OpenFileFilter

    private class EditorFrameListener
            extends MouseAdapter
            implements InternalFrameListener {

        public void mousePressed( MouseEvent me ) {
            MillStatic.documentsList.setSelectedIndex( EditorFrame.this.index );
            System.out.println( EditorFrame.this.index );

        } // END: mousePressed

        public void mouseReleased( MouseEvent me ) {
            MillStatic.documentsList.setSelectedIndex( EditorFrame.this.index );
            System.out.println( EditorFrame.this.index );

        } // END: mouseReleased

        public void mouseClicked( MouseEvent me ) {
            MillStatic.documentsList.setSelectedIndex( EditorFrame.this.index );
            System.out.println( EditorFrame.this.index );

        } // END: mouseClicked

        public void internalFrameActivated( InternalFrameEvent ife ) {
			activeEditor = EditorFrame.this;

		} // END: internalFrameActivated

		public void internalFrameClosing( InternalFrameEvent ife ) {
			activeEditor = null;

		} // END: internalFrameClosing

		public void internalFrameClosed( InternalFrameEvent ife ) {
		} // END: internalFrameClosed

		public void internalFrameOpened( InternalFrameEvent ife ) {
		} // END: internalFrameOpened

		public void internalFrameDeactivated( InternalFrameEvent ife ) {
		} // END: internalFrameDeactivated

		public void internalFrameDeiconified( InternalFrameEvent ife ) {
		} // END: internalFrameDeiconified

		public void internalFrameIconified( InternalFrameEvent ife ) {
		} // END: internalFrameIconified

    } // END: inner class EditorFrameListener

    protected Container container;
    protected JScrollPane scroller;
    protected JTextArea textArea;

    /** Filname to store the source code. */
    protected String fileName;
    protected String oldFileName;
    protected File document;
    protected int index;
    protected static EditorFrame activeEditor;

} // END: class EditorFrame