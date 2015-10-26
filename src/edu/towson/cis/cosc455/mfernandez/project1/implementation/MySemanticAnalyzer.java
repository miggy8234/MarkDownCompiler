package edu.towson.cis.cosc455.mfernandez.project1.implementation;

import ebu.towson.cis.cosc455.mfernandez.project1.interfaces.SemanticAnalyzer;

/**
 * Created by MasterMiggy on 10/25/15.
 */
public class MySemanticAnalyzer implements SemanticAnalyzer {

    public String addHtmlTag(String Content){
        return HtmlTags.htmlStart + Content + HtmlTags.htmlEnd;
    }

    public String addHeadTag(String headContent){
        return HtmlTags.headStart + headContent + HtmlTags.headEnd;
    }

    public String addTitleTag(String titleContent){
        return HtmlTags.titleStart + titleContent + HtmlTags.titleEnd;
    }

    public String addParagraphTag(String paragraphContent){
        return HtmlTags.paragraphStart + paragraphContent + HtmlTags.paragraphEnd;
    }

    public String addBoldTag(String boldContent){
        return HtmlTags.boldStart + boldContent + HtmlTags.boldEnd;
    }

    public String addListTag(String listContent){
        return HtmlTags.listStart + listContent + HtmlTags.listEnd;
    }

    public String addLineBreak(){
        return HtmlTags.lineBreak;
    }

    public String addLinkTextTag(String linkUrl, String linkText){
        return HtmlTags.linkStart + linkUrl + HtmlTags.linkMiddle + linkText + HtmlTags.linkEnd;
    }

    public String addAudioTag(String linkUrl){
        return HtmlTags.audioStart + linkUrl + HtmlTags.audioMiddle + HtmlTags.audioEnd;
    }

    public String addVideoTag(String linkUrl){
        return HtmlTags.videoStart + linkUrl + HtmlTags.videoMiddle;
    }

    @Override
    public void writeToFile(String item) {

    }
}
