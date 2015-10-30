package edu.towson.cis.cosc455.mfernandez.project1.implementation;

/**
 * Created by MasterMiggy on 10/15/15.
 */
public class myCompiler {

    public static void main(String[] args){
        System.out.println("Compiler Started: " + "\n");
        System.out.println("File loaded from: " + args[0] + "\n");
        String fileType = args[0].substring(args[0].lastIndexOf('.')+1, args[0].length());
        long startTime = System.currentTimeMillis();;
        try {
            if(fileType.trim().toUpperCase().equals("MKD")){
                CompilerManager manager = new CompilerManager(args[0]);
            }
            else{
                throw new CompilerException("Input file of wrong file type got " + fileType + " expected MKD");
            }
        } catch (CompilerException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();;
        long duration = (endTime - startTime);
        System.out.println("Program completed exiting in " + duration + " milliseconds" + "\n");
    }

}
