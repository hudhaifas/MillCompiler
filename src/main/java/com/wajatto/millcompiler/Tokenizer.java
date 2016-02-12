package com.wajatto.millcompiler;

/*
 * Tokenizer.java
 * No copyright reserved !� 2006
 * Created on February 26, 2006, 7:30 PM
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Tokenizer {

    public Tokenizer( File file ) {
        try {
            fileName = file.getName();
            fileParent = file.getParent();
            filePath = fileParent + "\\" + MillStatic.setfilePath( file.getName() ) + ".code";
            String content = MillStatic.read( file );
            linesCount = MillStatic.getLineNumber( content );
            String[] tokens = MillStatic.getTokens( content );
            writFile( tokens );

        } catch ( IOException ioException ) {
        } // END: try..catch

    } // END: Tokenizer constructor

    public void writFile( String[] tokens )
    throws IOException {
        File destination = new File( filePath );
        destination.createNewFile();
        FileOutputStream output = new FileOutputStream( destination );
        tokensCount = tokens.length;

        for ( int i = 0; i < tokens.length; i++ ) {
            charactersCount += tokens[ i ].length();
            byte[] buffer = ( tokens[ i ] + "\n" ).getBytes() ;
            output.write( buffer );

        } // END: for
        byte[] count = ( "There are " + linesCount + " Line(s)\nand " + tokensCount + " token(s)\nand " + charactersCount + " character(s) with out spaeces\nin " + fileName + " file" ).getBytes() ;
        output.write( count );
        output.close();

    } // END: writeFile

    protected String fileParent;
    protected String filePath;
    protected String fileName;
    protected int linesCount;
    protected int tokensCount;
    protected int charactersCount;

} // END: class Tokenizer