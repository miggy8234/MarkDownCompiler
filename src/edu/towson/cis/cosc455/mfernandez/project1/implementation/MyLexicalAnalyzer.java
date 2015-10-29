package edu.towson.cis.cosc455.mfernandez.project1.implementation;

import ebu.towson.cis.cosc455.mfernandez.project1.interfaces.LexicalAnalyzer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by MasterMiggy on 10/15/15.
 */
public class MyLexicalAnalyzer implements LexicalAnalyzer {

    private int charIndex;
    private String input;
    private String lineHolding;
    private  char nextChar;
    private StringTokenizer tokenizer;
    private boolean nextCharStartsToken = false;

    public MyLexicalAnalyzer(String fileLocation){
        try {
            Scanner fileToRead = new Scanner(new FileReader(fileLocation));
            input = "";
            while (fileToRead.hasNext()){
                //System.out.println(input);
                input += fileToRead.nextLine() + "\n";
            }
            fileToRead.close();
            //System.out.println(input);
            tokenizer = new StringTokenizer(input, "\n");
            CompilerManager.lineNumber = 0;
            getNextToken();
        } catch (FileNotFoundException e) {
            System.out.println("Compiler was unable to open file.");
            e.printStackTrace();
        }
    }

    /**
     * This is the public method to be called when the Syntax Analyzer needs a new
     * token to be parsed.
     */
    public void getNextToken() {
        if(tokenizer.hasMoreTokens() || lineHolding.length() > 0){
            //CompilerManager.currentToken
            //System.out.println(lineHolding);
            CompilerManager.currentToken = "";
            nextCharStartsToken = false;
            charIndex = 0;
            if(lineHolding == null || lineHolding.length() == 0 ){
                lineHolding = tokenizer.nextToken();
                CompilerManager.lineNumber++;
            }
            //System.out.println(lineHolding);
            do{
                getCharacter();
                addCharacter();

            }while (charIndex < lineHolding.length() && (!lookupToken() || validString() || lookupToken((CompilerManager.currentToken + Character.toString(lineHolding.toCharArray()[charIndex])).trim())) && !nextCharStartsToken);

            lineHolding = lineHolding.substring(charIndex, lineHolding.length());
            //System.out.println(lineHolding);
            if(!lookupToken() && !validString() && !isSpace(CompilerManager.currentToken)){
                try {
                    throw new CompilerException("On line number " + CompilerManager.lineNumber + " : " + CompilerManager.currentToken + " is not a valid token");
                } catch (CompilerException e) {
                    e.printStackTrace();
                }
                CompilerManager.currentToken = null;
            }
            //getCharacter();
            System.out.println("Current Token is: " + CompilerManager.currentToken);
        }
        else{
            CompilerManager.currentToken = ">";
            System.out.println("test " + lookupToken());
            CompilerManager.currentToken = null;
        }
    }

    /**
     * This is method gets the next character from the input and places it in
     * the nextCharacter class variable.
     *
     * @return the character
     */
    public void getCharacter(){
        if(charIndex < lineHolding.length() &&
                (!lookupToken(Character.toString(lineHolding.toCharArray()[charIndex]).trim())
                        || CompilerManager.currentToken.trim().length() == 0
                        || lookupToken((CompilerManager.currentToken + Character.toString(lineHolding.toCharArray()[charIndex])).trim()))){
            nextChar = lineHolding.toCharArray()[charIndex];
        }
        else{
            if(lookupToken(Character.toString(lineHolding.toCharArray()[charIndex]).trim())){}
            nextCharStartsToken = true;
            nextChar = ' ';
            charIndex--;
        }

    }

    /**
     * This method adds the current character the nextToken.
     */
    public void addCharacter(){

        CompilerManager.currentToken += nextChar;
        charIndex++;

    }

    /**
     * This is method gets the next character from the input and places it in
     * the nextCharacter class variable.
     *
     * @param c the current character
     * @return true, if is space; otherwise false
     */
    public boolean isSpace(String c){
        if(c == "" || c == " " || c == "\n" || c == "\t"){
            return true;
        }
        return false;
    }

    public boolean validString(){
        for(Character c : CompilerManager.currentToken.toCharArray()){
            if(!Tokens.text.contains(c.toString().toUpperCase()) || isSpace(c.toString().toUpperCase())){
                return false;
            }
        }
        return true;
    }

    /**
     * This method checks to see if the current, possible token is legal in the
     * defined grammar.
     *
     * @return true, if it is a legal token, otherwise false
     */
    public boolean lookupToken(){
        switch (CompilerManager.currentToken.trim()) {
            case Tokens.docBegin:
                return true;
            case Tokens.docEnd:
                return true;
            case Tokens.headAnnotation:
                return true;
            case Tokens.titleBegin:
                return true;
            case Tokens.titleEnd:
                return true;
            case Tokens.paragraphBegin:
                return true;
            case Tokens.paragraphEnd:
                return true;
            case Tokens.boldAnnotation:
                return true;
            case Tokens.italicAnnotation:
                return true;
            case Tokens.listItemBegin:
                return true;
            case Tokens.listItemEnd:
                return true;
            case Tokens.lineBreakAnnotation:
                return true;
            case Tokens.urlAddressBegin:
                return true;
            case Tokens.urlAddressEnd:
                return true;
            case Tokens.linkPhraseBegin:
                return true;
            case Tokens.linkPhraseEnd:
                return true;
            case Tokens.audioAnnotation:
                return true;
            case Tokens.videoAnnotation:
                return true;
            case Tokens.variableDefinitionBegin:
                return true;
            case Tokens.variableUseBegin:
                return true;
            case Tokens.variableEnd:
                return true;
        }

        return false;
    }

    public boolean lookupToken(String toTest){
        switch (toTest.trim()) {
            case Tokens.docBegin:
                return true;
            case Tokens.docEnd:
                return true;
            case Tokens.headAnnotation:
                return true;
            case Tokens.titleBegin:
                return true;
            case Tokens.titleEnd:
                return true;
            case Tokens.paragraphBegin:
                return true;
            case Tokens.paragraphEnd:
                return true;
            case Tokens.boldAnnotation:
                return true;
            case Tokens.italicAnnotation:
                return true;
            case Tokens.listItemBegin:
                return true;
            case Tokens.listItemEnd:
                return true;
            case Tokens.lineBreakAnnotation:
                return true;
            case Tokens.urlAddressBegin:
                return true;
            case Tokens.urlAddressEnd:
                return true;
            case Tokens.linkPhraseBegin:
                return true;
            case Tokens.linkPhraseEnd:
                return true;
            case Tokens.audioAnnotation:
                return true;
            case Tokens.videoAnnotation:
                return true;
            case Tokens.variableDefinitionBegin:
                return true;
            case Tokens.variableUseBegin:
                return true;
            case Tokens.variableEnd:
                return true;
        }

        return false;
    }

}
