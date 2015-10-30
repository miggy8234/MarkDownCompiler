package edu.towson.cis.cosc455.mfernandez.project1.implementation;

import com.sun.xml.internal.ws.api.ha.StickyFeature;
import ebu.towson.cis.cosc455.mfernandez.project1.interfaces.SemanticAnalyzer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by MasterMiggy on 10/25/15.
 */
public class MySemanticAnalyzer implements SemanticAnalyzer {

    private ArrayList<String> output;
    private Queue<String> tokensToPrint;
    private LinkedList<HashMap<String, String>> variables;

    public MySemanticAnalyzer(Queue<String> tokens){
        tokensToPrint = tokens;
        output = new ArrayList<String>();
        variables = new LinkedList<HashMap<String, String>>();
    }

    public void convertStack(){
        HashMap<String, String> scopeVariables = null;
        while (!tokensToPrint.isEmpty()){
            String current = tokensToPrint.poll();
            boolean newScope = false;
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
                        newScope = true;
                    }
                    break;
                case Tokens.boldAnnotation:
                    if(output.contains(HtmlTags.boldStart)){
                        output.add(HtmlTags.boldEnd);
                    }
                    else{
                        output.add(HtmlTags.boldStart);
                        newScope = true;
                    }
                    break;
                case Tokens.italicAnnotation:
                    if(output.contains(HtmlTags.italicStart)){
                        output.add(HtmlTags.italicEnd);
                    }
                    else{
                        output.add(HtmlTags.italicStart);
                        newScope = true;
                    }
                    break;
                case Tokens.titleBegin:
                    output.add(HtmlTags.titleStart);
                    newScope = true;
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
                    output.add(HtmlTags.linkEnd);
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
                    newScope = true;
                    break;
                case Tokens.paragraphEnd:
                    output.add(HtmlTags.paragraphEnd + "\n");
                    break;
                case Tokens.variableDefinitionBegin:
                    String variableName = tokensToPrint.poll();
                    current = tokensToPrint.poll();
                    String variableValue = tokensToPrint.poll();
                    if(scopeVariables == null){
                        scopeVariables = new HashMap<String, String>(){{put("ScopeLocaiton", variableValue);put(variableName, variableValue);}};
                        //variables.addLast(new HashMap<String, String>(){{put(variableName, variableValue);}});
                    }
                    else{
                        scopeVariables.put(variableName, variableValue);
                    }
                    current = tokensToPrint.poll();
                    break;
                case Tokens.variableUseBegin:
                    String variableNameToFind = tokensToPrint.poll();
                    if(scopeVariables != null && scopeVariables.containsKey(variableNameToFind)){
                        output.add(scopeVariables.get(variableNameToFind));
                    }
                    else if(containsValueForVariable(variableNameToFind)){
                        output.add(getValueForVariable(variableNameToFind));
                    }
                    current = tokensToPrint.poll();
                    break;
                default:
                    output.add(current);
                    break;
            }
            if(newScope){
                if(scopeVariables != null){
                    if(variables.size() == 0){
                        variables.addFirst(scopeVariables);
                    }
                    scopeVariables = null;
                }
            }
        }
    }

    private String getValueForVariable(String variableName){
        for(HashMap<String, String> map : variables){
            if(map.containsKey(variableName)){
                return map.get(variableName);
            }
        }
        return null;
    }

    private boolean containsValueForVariable(String variableName){
        for(HashMap<String, String> map : variables){
            if(map.containsKey(variableName)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void writeToFile(String fileLocation) throws FileNotFoundException, UnsupportedEncodingException {
        String fileName = "output.html";
        String filePath = fileLocation.substring(0, fileLocation.lastIndexOf('/'));
       // System.out.println("Output saved to: " + filePath + fileName);
        PrintWriter writer = new PrintWriter(filePath+fileName, "UTF-8");
        for(String s: output){
            writer.print(s);
            //System.out.print(s);
        }
        //System.out.println("\n");
        writer.close();
    }
}
