package edu.towson.cis.cosc455.mfernandez.project1.implementation;

import ebu.towson.cis.cosc455.mfernandez.project1.interfaces.SyntaxAnalyzer;

import java.util.*;

import static edu.towson.cis.cosc455.mfernandez.project1.implementation.Tokens.docBegin;

/**
 * Created by MasterMiggy on 10/15/15.
 */
public class  mySyntaxAnalyzer implements SyntaxAnalyzer {

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
            String previousCurrent = null;
            while(!errorFound && stripString(CompilerManager.currentToken).equals(Tokens.variableDefinitionBegin)){
                variableDefine();
            }
            if(!errorFound){head();}
            if(!errorFound){body();}
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
        String previousCurrent = null;
        boolean rety = true;
        while(!errorFound
                && CompilerManager.currentToken != null
                && !stripString(CompilerManager.currentToken).equals(Tokens.docEnd)
                && (previousCurrent == null || !previousCurrent.equals(CompilerManager.currentToken) || rety)){
            if(previousCurrent != null && previousCurrent.equals(CompilerManager.currentToken) && rety){
                rety = false;
            }
            previousCurrent = CompilerManager.currentToken;
            if(!errorFound){paragraph();}
            innerItem();
        }
        if(previousCurrent.equals(CompilerManager.currentToken)){
            throw new CompilerException("The token of " + CompilerManager.currentToken + " is not a valid token here");
        }
    }

    /**
     * This method implements the BNF grammar rule for the paragraph annotation.
     * @throws CompilerException
     */
    public void paragraph() throws CompilerException{
        if(stripString(CompilerManager.currentToken).equals(Tokens.paragraphBegin)){
            moveOn();
            while(!errorFound && stripString(CompilerManager.currentToken).equals(Tokens.variableDefinitionBegin)){
                variableDefine();
            }
            while(!errorFound
                    && CompilerManager.currentToken != null
                    && !stripString(CompilerManager.currentToken).equals(Tokens.paragraphEnd)){
                innerItem();
            }
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
        if(!Tokens.validTags.contains(CompilerManager.currentToken)){
            moveOn();
        }
    }

    /**
     * This method implements the BNF grammar rule for the variable-define annotation.
     * @throws CompilerException
     */
    public void variableDefine() throws CompilerException{
        if(stripString(CompilerManager.currentToken).equals(Tokens.variableDefinitionBegin)){
            moveOn();
            if(!errorFound){innerText();}
            if (!errorFound && stripString(CompilerManager.currentToken).equals(Tokens.variableEquivalence)){
                moveOn();
            }
            else{
                errorFound = true;
                throw new CompilerException("Got " + CompilerManager.currentToken + " but expected " + Tokens.variableEquivalence);
            }
            if(!errorFound){innerText();}
            if (!errorFound && stripString(CompilerManager.currentToken).equals(Tokens.variableEnd)){
                moveOn();
            }
            else{
                errorFound = true;
                throw new CompilerException("Got " + CompilerManager.currentToken + " but expected " + Tokens.variableEnd);
            }
        }
    }

    /**
     * This method implements the BNF grammar rule for the variable-use annotation.
     * @throws CompilerException
     */
    public void variableUse() throws CompilerException{
        if(stripString(CompilerManager.currentToken).equals(Tokens.variableUseBegin)){
            moveOn();
            if(!errorFound){innerText();}
            if (!errorFound && stripString(CompilerManager.currentToken).equals(Tokens.variableEnd)){
                moveOn();
            }
            else{
                errorFound = true;
                throw new CompilerException("Got " + CompilerManager.currentToken + " but expected " + Tokens.variableEnd);
            }
        }
    }

    /**
     * This method implements the BNF grammar rule for the bold annotation.
     * @throws CompilerException
     */
    public void bold() throws CompilerException{
        if(stripString(CompilerManager.currentToken).equals(Tokens.boldAnnotation)){
            moveOn();
            while(!errorFound && !stripString(CompilerManager.currentToken).equals(Tokens.boldAnnotation)){
                variableDefine();
                innerText();
            }
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
            while(!errorFound && !stripString(CompilerManager.currentToken).equals(Tokens.italicAnnotation)){
                variableDefine();
                innerText();
            }
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
            while(!errorFound && !stripString(CompilerManager.currentToken).equals(Tokens.listItemEnd)){
                italics();
                video();
                audio();
                link();
                bold();
                variableUse();
                innerText();
            }
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
        if(!errorFound){bold();}
        if(!errorFound){italics();}
        if(!errorFound){link();}
        if(!errorFound){link();}
        if(!errorFound){audio();}
        if(!errorFound){video();}
        if(!errorFound){listitem();}
        if(!errorFound){newline();}
        if(!errorFound){variableUse();}
        if(!errorFound){innerText();}
    }

    /**
     * This method implements the BNF grammar rule for the link annotation.
     * @throws CompilerException
     */
    public void link() throws CompilerException{
        if(stripString(CompilerManager.currentToken).equals(Tokens.linkPhraseBegin)){
            moveOn();
            while(!errorFound && !stripString(CompilerManager.currentToken).equals(Tokens.linkPhraseEnd)){
                variableUse();
                innerText();
            }
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
            while(!errorFound && !stripString(CompilerManager.currentToken).equals(Tokens.urlAddressEnd)){
                variableUse();
                innerText();
            }
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
