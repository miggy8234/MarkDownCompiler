package ebu.towson.cis.cosc455.mfernandez.project1.interfaces;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

/**
 * Created by MasterMiggy on 10/25/15.
 */
public interface SemanticAnalyzer {

    public void writeToFile(String fileLocation) throws FileNotFoundException, UnsupportedEncodingException;

}
