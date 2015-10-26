package edu.towson.cis.cosc455.mfernandez.project1.implementation;

import com.sun.xml.internal.ws.api.ha.StickyFeature;
import ebu.towson.cis.cosc455.mfernandez.project1.interfaces.SemanticAnalyzer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by MasterMiggy on 10/25/15.
 */
public class MySemanticAnalyzer implements SemanticAnalyzer {

    private ArrayList<String> output;

    public MySemanticAnalyzer(){
        output = new ArrayList<String>();
    }

    public void addToFile(String content){
        output.add(content);
    }


    public static String addHtmlStartTag(){
        return HtmlTags.htmlStart;
    }

    public static String addHtmlEndTag(){
        return HtmlTags.htmlEnd;
    }

    public static String addHeadTag(String headContent){
        return HtmlTags.headStart + headContent + HtmlTags.headEnd;
    }

    public static String addTitleTag(String titleContent){
        return HtmlTags.titleStart + titleContent + HtmlTags.titleEnd;
    }

    public static String addParagraphTag(String paragraphContent){
        return HtmlTags.paragraphStart + paragraphContent + HtmlTags.paragraphEnd;
    }

    public static String addBoldTag(String boldContent){
        return HtmlTags.boldStart + boldContent + HtmlTags.boldEnd;
    }

    public static String addListTag(String listContent){
        return HtmlTags.listStart + listContent + HtmlTags.listEnd;
    }

    public static String addLineBreak(){
        return HtmlTags.lineBreak;
    }

    public static String addLinkTextTag(String linkUrl, String linkText){
        return HtmlTags.linkStart + linkUrl + HtmlTags.linkMiddle + linkText + HtmlTags.linkEnd;
    }

    public static String addAudioTag(String linkUrl){
        return HtmlTags.audioStart + linkUrl + HtmlTags.audioMiddle + HtmlTags.audioEnd;
    }

    public static String addVideoTag(String linkUrl){
        return HtmlTags.videoStart + linkUrl + HtmlTags.videoMiddle;
    }

    @Override
    public void writeToFile(String fileLocation) throws FileNotFoundException, UnsupportedEncodingException {
        String fileName = "output.html";
        String filePath = fileLocation.substring(0, fileLocation.lastIndexOf('/'));
        System.out.println("Output saved to: " + filePath + fileName);
        PrintWriter writer = new PrintWriter(filePath+fileName, "UTF-8");
        for(String s: output){
            writer.println(s);
            System.out.println(s);
        }
        writer.close();
    }
}
