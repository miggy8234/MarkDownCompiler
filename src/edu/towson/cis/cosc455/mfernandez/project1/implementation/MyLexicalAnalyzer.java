package edu.towson.cis.cosc455.mfernandez.project1.implementation;

import ebu.towson.cis.cosc455.mfernandez.project1.interfaces.LexicalAnalyzer;

/**
 * Created by MasterMiggy on 10/15/15.
 */
public class MyLexicalAnalyzer implements LexicalAnalyzer {

    /**
     * This is the public method to be called when the Syntax Analyzer needs a new
     * token to be parsed.
     */
    public void getNextToken(){}

    /**
     * This is method gets the next character from the input and places it in
     * the nextCharacter class variable.
     *
     * @return the character
     */
    public void getCharacter(){}

    /**
     * This method adds the current character the nextToken.
     */
    public void addCharacter(){}

    /**
     * This is method gets the next character from the input and places it in
     * the nextCharacter class variable.
     *
     * @param c the current character
     * @return true, if is space; otherwise false
     */
    public boolean isSpace(String c){
        return false;
    }

    /**
     * This method checks to see if the current, possible token is legal in the
     * defined grammar.
     *
     * @return true, if it is a legal token, otherwise false
     */
    public boolean lookupToken(){
        return false;
    }

}
