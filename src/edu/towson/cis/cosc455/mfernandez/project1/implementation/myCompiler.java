package edu.towson.cis.cosc455.mfernandez.project1.implementation;

/**
 * Created by MasterMiggy on 10/15/15.
 */
public class myCompiler {

    public static void main(String[] args){
        System.out.println("Start Compiler: ");
        System.out.println("File loaded from: " + args[0]);
        try {
            CompilerManager manager = new CompilerManager(args[0]);
        } catch (CompilerException e) {
            e.printStackTrace();
        }
    }

}
