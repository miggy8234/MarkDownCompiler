package edu.towson.cis.cosc455.mfernandez.project1.implementation;

/**
 * Created by MasterMiggy on 10/15/15.
 */
public class myCompiler {

    public static void main(String[] args){
        System.out.println("Compiler Started: " + "\n");
        System.out.println("File loaded from: " + args[0] + "\n");
        long startTime = System.currentTimeMillis();;
        try {
            CompilerManager manager = new CompilerManager(args[0]);
        } catch (CompilerException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();;
        long duration = (endTime - startTime);
        System.out.println("Program completed exiting in " + duration + " milliseconds" + "\n");
    }

}
