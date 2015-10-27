package edu.towson.cis.cosc455.mfernandez.project1.implementation;

import ebu.towson.cis.cosc455.mfernandez.project1.interfaces.SyntaxAnalyzer;

import java.util.*;

import static edu.towson.cis.cosc455.mfernandez.project1.implementation.Tokens.docBegin;

/**
 * Created by MasterMiggy on 10/15/15.
 */
public class mySyntaxAnalyzer implements SyntaxAnalyzer {

    public static boolean errorFound;

    private Queue<String> tokensToPrint;

    public mySyntaxAnalyzer(){
        errorFound = false;
        tokensToPrint = new LinkedList<String>();
        try {
            markdown();
        } catch (CompilerException e) {
            e.printStackTrace();
        }
    }

    private String stripString(String toStrip){
        return toStrip.trim().toUpperCase();
    }

    private void moveOn(){
        this.tokensToPrint.add(CompilerManager.currentToken);
        CompilerManager.lexicalAnalyzer.getNextToken();
    }

    public String convertStack(){
        if (!tokensToPrint.isEmpty()){
            String current = tokensToPrint.poll();
            switch (stripString(current)){
                case Tokens.docBegin:
                    return MySemanticAnalyzer.addHtmlStartTag();
                case Tokens.docEnd:
                    return MySemanticAnalyzer.addHtmlEndTag();
                default:
                    break;
            }
            return current;
        }
        return null;
    }

    /**
     * This method implements the BNF grammar rule for the document annotation.
     * @throws CompilerException
     */
    public void markdown() throws CompilerException{
        if(stripString(CompilerManager.currentToken).equals(docBegin)){
            moveOn();
            if(!errorFound){innerText();}
            if (!errorFound && stripString(CompilerManager.currentToken).equals(Tokens.docEnd)){
                moveOn();
            }
            else{
                errorFound = true;
                throw new CompilerException("Got " + CompilerManager.currentToken + " but expected " + Tokens.docEnd);
            }
        }
        else{
            errorFound = true;
            throw new CompilerException("Got " + CompilerManager.currentToken + " but expected " + docBegin);
        }
     }

    /**
     * This method implements the BNF grammar rule for the head annotation.
     * @throws CompilerException
     */
    public void head() throws CompilerException{

    }

    /**
     * This method implements the BNF grammar rule for the title annotation.
     * @throws CompilerException

     */
    public void title() throws CompilerException{
        if(stripString(CompilerManager.currentToken).equals(Tokens.titleBegin)){
            moveOn();
            if(!errorFound){innerText();}
            if (!errorFound && stripString(CompilerManager.currentToken).equals(Tokens.docEnd)){
                moveOn();
            }
            else{
                errorFound = true;
                throw new CompilerException("Got " + CompilerManager.currentToken + " but expected " + Tokens.docEnd);
            }
        }
        else{
            errorFound = true;
            throw new CompilerException("Got " + CompilerManager.currentToken + " but expected " + docBegin);
        }
    }

    /**
     * This method implements the BNF grammar rule for the body annotation.
     * @throws CompilerException
     */
    public void body() throws CompilerException{

    }

    /**
     * This method implements the BNF grammar rule for the paragraph annotation.
     * @throws CompilerException
     */
    public void paragraph() throws CompilerException{

    }

    /**
     * This method implements the BNF grammar rule for the inner-text annotation.
     * @throws CompilerException
     */
    public void innerText() throws CompilerException{
        moveOn();
    }

    /**
     * This method implements the BNF grammar rule for the variable-define annotation.
     * @throws CompilerException
     */
    public void variableDefine() throws CompilerException{

    }

    /**
     * This method implements the BNF grammar rule for the variable-use annotation.
     * @throws CompilerException
     */
    public void variableUse() throws CompilerException{

    }

    /**
     * This method implements the BNF grammar rule for the bold annotation.
     * @throws CompilerException
     */
    public void bold() throws CompilerException{

    }

    /**
     * This method implements the BNF grammar rule for the italics annotation.
     * @throws CompilerException
     */
    public void italics() throws CompilerException{

    }

    /**
     * This method implements the BNF grammar rule for the listitem annotation.
     * @throws CompilerException
     */
    public void listitem() throws CompilerException{

    }

    /**
     * This method implements the BNF grammar rule for the inner-item annotation.
     * @throws CompilerException
     */
    public void innerItem() throws CompilerException{

    }

    /**
     * This method implements the BNF grammar rule for the link annotation.
     * @throws CompilerException
     */
    public void link() throws CompilerException{

    }

    /**
     * This method implements the BNF grammar rule for the audio annotation.
     * @throws CompilerException
     */
    public void audio() throws CompilerException{

    }

    /**
     * This method implements the BNF grammar rule for the video annotation.
     * @throws CompilerException
     */
    public void video() throws CompilerException{

    }

    /**
     * This method implements the BNF grammar rule for the newline annotation.
     * @throws CompilerException
     */
    public void newline() throws CompilerException{

    }

}
