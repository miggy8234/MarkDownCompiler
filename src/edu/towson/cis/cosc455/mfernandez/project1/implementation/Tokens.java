package edu.towson.cis.cosc455.mfernandez.project1.implementation;

import java.util.ArrayList;

/**
 * Created by MasterMiggy on 10/15/15.
 */
public class Tokens {

    public static final String docBegin = "#BEGIN";
    public static final String docEnd = "#END";

    public static final String headAnnotation = "^";

    public static final String titleBegin = "<";
    public static final String titleEnd = ">";

    public static final String paragraphBegin = "{";
    public static final String paragraphEnd = "}";

    public static final String boldAnnotation = "**";

    public static final String italicAnnotation = "*";

    public static final String listItemBegin = "+";
    public static final String listItemEnd = ";";

    public static final String lineBreakAnnotation = "~";

    public static final String urlAddressBegin = "(";
    public static final String urlAddressEnd = ")";

    public static final String linkPhraseBegin = "[";
    public static final String linkPhraseEnd = "]";

    public static final String audioAnnotation = "@";

    public static final String videoAnnotation = "%";

    public static final String variableDefinitionBegin = "$DEF";
    public static final String variableEquivalence = "=";
    public static final String variableUseBegin = "$USE";
    public static final String variableEnd = "$END";

    public static final ArrayList<String> validTags = new ArrayList<String>(){{
        add(docBegin);
        add(docEnd);
        add(headAnnotation);
        add(titleBegin);
        add(titleEnd);
        add(paragraphBegin);
        add(paragraphEnd);
        add(boldAnnotation);
        add(italicAnnotation);
        add(listItemBegin);
        add(listItemEnd);
        add(lineBreakAnnotation);
        add(urlAddressBegin);
        add(urlAddressEnd);
        add(linkPhraseBegin);
        add(linkPhraseEnd);
        add(audioAnnotation);
        add(videoAnnotation);
        add(variableDefinitionBegin);
        add(variableEquivalence);
        add(variableUseBegin);
        add(variableEnd);
    }};

    public static final ArrayList<String> text = new ArrayList<String>(){{
        add("A");
        add("B");
        add("C");
        add("D");
        add("E");
        add("F");
        add("G");
        add("H");
        add("I");
        add("J");
        add("K");
        add("L");
        add("M");
        add("N");
        add("O");
        add("P");
        add("Q");
        add("R");
        add("S");
        add("T");
        add("U");
        add("V");
        add("W");
        add("X");
        add("Y");
        add("Z");
        add("0");
        add("1");
        add("2");
        add("3");
        add("4");
        add("5");
        add("6");
        add("7");
        add("8");
        add("9");
        add(".");
        add(",");
        add(":");
        add("_");
        add("!");
        add("/");
        add(" ");
        add("\t");
        add("\r");
        add("\n");
    }};

}
