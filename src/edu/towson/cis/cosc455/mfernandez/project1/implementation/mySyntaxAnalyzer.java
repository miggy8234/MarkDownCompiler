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

    public String stripString(String toStrip){
        return toStrip.trim().toUpperCase();
    }

    private void moveOn(){
        this.tokensToPrint.add(CompilerManager.currentToken);
        CompilerManager.lexicalAnalyzer.getNextToken();
    }

    public Queue<String> passParseTree(){return tokensToPrint;}

    /**
     * This method implements the BNF grammar rule for the document annotation.
     * @throws CompilerException
     */
    public void markdown() throws CompilerException{
        if(stripString(CompilerManager.currentToken).equals(docBegin)){
            moveOn();
            if(!errorFound){head();}
            if(!errorFound){bold();}
            if(!errorFound){newline();}
            if(!errorFound){italics();}
            if(!errorFound){link();}
            if(!errorFound){audio();}
            if(!errorFound){video();}
            if(!errorFound){listitem();}
            if(!errorFound){paragraph();}
            while(!errorFound && !Tokens.validTags.contains(stripString(CompilerManager.currentToken))){
                innerText();
            }
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
        if(stripString(CompilerManager.currentToken).equals(Tokens.headAnnotation)){
            moveOn();
            if(!errorFound){title();}
            if (!errorFound && stripString(CompilerManager.currentToken).equals(Tokens.headAnnotation)){
                moveOn();
            }
            else{
                errorFound = true;
                throw new CompilerException("Got " + CompilerManager.currentToken + " but expected " + Tokens.headAnnotation);
            }
        }
    }

    /**
     * This method implements the BNF grammar rule for the title annotation.
     * @throws CompilerException

     */
    public void title() throws CompilerException{
        if(stripString(CompilerManager.currentToken).equals(Tokens.titleBegin)){
            moveOn();
            if(!errorFound){innerText();}
            if (!errorFound && stripString(CompilerManager.currentToken).equals(Tokens.titleEnd)){
                moveOn();
            }
            else{
                errorFound = true;
                throw new CompilerException("Got " + CompilerManager.currentToken + " but expected " + Tokens.titleEnd);
            }
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
        if(stripString(CompilerManager.currentToken).equals(Tokens.paragraphBegin)){
            moveOn();
            if(!errorFound){innerText();}
            if (!errorFound && stripString(CompilerManager.currentToken).equals(Tokens.paragraphEnd)){
                moveOn();
            }
            else {
                errorFound = true;
                throw new CompilerException("Got " + CompilerManager.currentToken + " but expected " + Tokens.paragraphEnd);
            }
        }
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
        if(stripString(CompilerManager.currentToken).equals(Tokens.boldAnnotation)){
            moveOn();
            if(!errorFound){innerText();}
            if (!errorFound && stripString(CompilerManager.currentToken).equals(Tokens.boldAnnotation)){
                moveOn();
            }
            else{
                errorFound = true;
                throw new CompilerException("Got " + CompilerManager.currentToken + " but expected " + Tokens.boldAnnotation);
            }
        }
    }

    /**
     * This method implements the BNF grammar rule for the italics annotation.
     * @throws CompilerException
     */
    public void italics() throws CompilerException{
        if(stripString(CompilerManager.currentToken).equals(Tokens.italicAnnotation)){
            moveOn();
            if(!errorFound){innerText();}
            if (!errorFound && stripString(CompilerManager.currentToken).equals(Tokens.italicAnnotation)){
                moveOn();
            }
            else{
                errorFound = true;
                throw new CompilerException("Got " + CompilerManager.currentToken + " but expected " + Tokens.italicAnnotation);
            }
        }
    }

    /**
     * This method implements the BNF grammar rule for the listitem annotation.
     * @throws CompilerException
     */
    public void listitem() throws CompilerException{
        if(stripString(CompilerManager.currentToken).equals(Tokens.listItemBegin)){
            moveOn();
            if(!errorFound){innerText();}
            if (!errorFound && stripString(CompilerManager.currentToken).equals(Tokens.listItemEnd)){
                moveOn();
            }
            else{
                errorFound = true;
                throw new CompilerException("Got " + CompilerManager.currentToken + " but expected " + Tokens.listItemEnd);
            }
        }
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
        if(stripString(CompilerManager.currentToken).equals(Tokens.linkPhraseBegin)){
            moveOn();
            if(!errorFound){innerText();}
            if (!errorFound && stripString(CompilerManager.currentToken).equals(Tokens.linkPhraseEnd)){
                moveOn();
                if(!errorFound){url();}
            }
            else{
                errorFound = true;
                throw new CompilerException("Got " + CompilerManager.currentToken + " but expected " + Tokens.linkPhraseEnd);
            }
        }
    }

    public void url() throws CompilerException{
        if(stripString(CompilerManager.currentToken).equals(Tokens.urlAddressBegin)){
            moveOn();
            if(!errorFound){innerText();}
            if (!errorFound && stripString(CompilerManager.currentToken).equals(Tokens.urlAddressEnd)){
                moveOn();
            }
            else{
                errorFound = true;
                throw new CompilerException("Got " + CompilerManager.currentToken + " but expected " + Tokens.urlAddressEnd);
            }
        }
    }

    /**
     * This method implements the BNF grammar rule for the audio annotation.
     * @throws CompilerException
     */
    public void audio() throws CompilerException{
        if(stripString(CompilerManager.currentToken).equals(Tokens.audioAnnotation)){
            moveOn();
            if(!errorFound) {url();}
        }
    }

    /**
     * This method implements the BNF grammar rule for the video annotation.
     * @throws CompilerException
     */
    public void video() throws CompilerException{
        if(stripString(CompilerManager.currentToken).equals(Tokens.videoAnnotation)){
            moveOn();
            if(!errorFound) {url();}
        }
    }

    /**
     * This method implements the BNF grammar rule for the newline annotation.
     * @throws CompilerException
     */
    public void newline() throws CompilerException{
        if(stripString(CompilerManager.currentToken).equals(Tokens.lineBreakAnnotation)){
            moveOn();
        }
    }

}
