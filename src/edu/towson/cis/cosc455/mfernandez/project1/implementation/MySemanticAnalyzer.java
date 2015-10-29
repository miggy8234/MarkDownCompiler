package edu.towson.cis.cosc455.mfernandez.project1.implementation;

import com.sun.xml.internal.ws.api.ha.StickyFeature;
import ebu.towson.cis.cosc455.mfernandez.project1.interfaces.SemanticAnalyzer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Queue;

/**
 * Created by MasterMiggy on 10/25/15.
 */
public class MySemanticAnalyzer implements SemanticAnalyzer {

    private ArrayList<String> output;
    private Queue<String> tokensToPrint;

    public MySemanticAnalyzer(Queue<String> tokens){
        tokensToPrint = tokens;
        output = new ArrayList<String>();
    }

    public void convertStack(){
        while (!tokensToPrint.isEmpty()){
            String current = tokensToPrint.poll();
            switch (CompilerManager.syntaxAnalyzer.stripString(current)){
                case Tokens.docBegin:
                    output.add(HtmlTags.htmlStart + "\n");
                    break;
                case Tokens.docEnd:
                    output.add("\n" + HtmlTags.htmlEnd);
                    break;
                case Tokens.headAnnotation:
                    if(output.contains(HtmlTags.headStart)){
                        output.add(HtmlTags.headEnd + "\n");
                    }
                    else{
                        output.add(HtmlTags.headStart);
                    }
                    break;
                case Tokens.boldAnnotation:
                    if(output.contains(HtmlTags.boldStart)){
                        output.add(HtmlTags.boldEnd);
                    }
                    else{
                        output.add(HtmlTags.boldStart);
                    }
                    break;
                case Tokens.italicAnnotation:
                    if(output.contains(HtmlTags.italicStart)){
                        output.add(HtmlTags.italicEnd);
                    }
                    else{
                        output.add(HtmlTags.italicStart);
                    }
                    break;
                case Tokens.titleBegin:
                    output.add(HtmlTags.titleStart);
                    break;
                case Tokens.titleEnd:
                    output.add(HtmlTags.titleEnd);
                    break;
                case Tokens.lineBreakAnnotation:
                    output.add("\n" + HtmlTags.lineBreak + "\n");
                    break;
                case Tokens.linkPhraseBegin:
                    output.add(HtmlTags.linkStart);
                    String linkText = tokensToPrint.poll();
                    current = tokensToPrint.poll();
                    current = tokensToPrint.poll();
                    String linkUrl = tokensToPrint.poll();
                    current = tokensToPrint.poll();
                    output.add(linkUrl);
                    output.add(HtmlTags.linkMiddle);
                    output.add(linkText);
                    output.add(HtmlTags.linkEnd + "\n");
                    break;
                case Tokens.audioAnnotation:
                    output.add(HtmlTags.audioStart);
                    current = tokensToPrint.poll();
                    String audioUrl = tokensToPrint.poll();
                    current = tokensToPrint.poll();
                    output.add(audioUrl);
                    output.add(HtmlTags.audioMiddle);
                    output.add(HtmlTags.audioEnd + "\n");
                    break;
                case Tokens.videoAnnotation:
                    output.add(HtmlTags.videoStart);
                    current = tokensToPrint.poll();
                    String videoUrl = tokensToPrint.poll();
                    current = tokensToPrint.poll();
                    output.add(videoUrl);
                    output.add(HtmlTags.videoMiddle + "\n");
                    break;
                case Tokens.listItemBegin:
                    output.add(HtmlTags.listStart);
                    break;
                case Tokens.listItemEnd:
                    output.add(HtmlTags.listEnd + "\n");
                    break;
                case Tokens.paragraphBegin:
                    output.add(HtmlTags.paragraphStart);
                    break;
                case Tokens.paragraphEnd:
                    output.add(HtmlTags.paragraphEnd + "\n");
                    break;
                default:
                    output.add(current);
                    break;
            }
        }
    }

    //public void addToFile(String content){
     //   output.add(content);
   // }

    @Override
    public void writeToFile(String fileLocation) throws FileNotFoundException, UnsupportedEncodingException {
        String fileName = "output.html";
        String filePath = fileLocation.substring(0, fileLocation.lastIndexOf('/'));
        System.out.println("Output saved to: " + filePath + fileName);
        PrintWriter writer = new PrintWriter(filePath+fileName, "UTF-8");
        for(String s: output){
            writer.print(s);
            System.out.print(s);
        }
        System.out.println("\n");
        writer.close();
    }

    /*
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
    */
}
