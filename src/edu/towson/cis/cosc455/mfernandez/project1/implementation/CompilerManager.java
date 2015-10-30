package edu.towson.cis.cosc455.mfernandez.project1.implementation;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by MasterMiggy on 10/24/15.
 */
public class CompilerManager {

    public static String currentToken;
    public static int lineNumber;
    public static char nextCharacter;
    public static MyLexicalAnalyzer lexicalAnalyzer;
    public static MySemanticAnalyzer semanticAnalyzer;
    public static mySyntaxAnalyzer syntaxAnalyzer;
    private String outputLocation;


    public CompilerManager(String fileLocation) throws CompilerException {
        lexicalAnalyzer = new MyLexicalAnalyzer(fileLocation);
        syntaxAnalyzer = new mySyntaxAnalyzer();

        if(currentToken == null && !syntaxAnalyzer.errorFound){
            semanticAnalyzer = new MySemanticAnalyzer(syntaxAnalyzer.passParseTree());
            semanticAnalyzer.convertStack();

            System.out.println("Compilation Completed" + "\n");
            try {
                semanticAnalyzer.writeToFile(fileLocation);
                String fileName = fileLocation.substring(fileLocation.lastIndexOf('/'), fileLocation.lastIndexOf('.')) + ".html";
                String filePath = fileLocation.substring(0, fileLocation.lastIndexOf('/'));
                outputLocation = filePath + fileName;
                System.out.println("Output saved to: " + outputLocation + "\n");
                System.out.println("Opening created file in browser..." + "\n");
                openHTMLFileInBrowswer(outputLocation);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }
        else if(!syntaxAnalyzer.errorFound && currentToken != null ){
            throw new CompilerException("Extra input after " + Tokens.docEnd + " tag.");
        }
        else{
            System.out.println("Error Found" + "\n");
        }
    }

    /**
     * Opens an HTML file in the default browswer. Requies the following imports:
     * 		import java.awt.Desktop;
     * 		import java.io.File;
     * 		import java.io.IOException;
     * @param htmlFileStr the String of an HTML file.
     */
    void openHTMLFileInBrowswer(String htmlFileStr){
        File file= new File(htmlFileStr.trim());
        if(!file.exists()){
            System.err.println("File "+ htmlFileStr +" does not exist.");
            return;
        }
        try{
            Desktop.getDesktop().browse(file.toURI());
        }
        catch(IOException ioe){
            System.err.println("Failed to open file");
            ioe.printStackTrace();
        }
        return ;
    }

}
