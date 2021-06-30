package sample;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

//Objects passwordFiles
public class passwordFiles {
    private String fileName = "File";
    private ArrayList<password> listPasswords = new ArrayList<>();
    private String filePassword = "noPass";
    private static int fileCount = 1;

    //constructors
    public passwordFiles(){
        fileName = "File"+ fileCount;
        fileCount++;
    }
    public passwordFiles(String fileName, String passwordGiven){
        filePassword = passwordGiven;
        this.fileName = fileName;
        fileCount++;
    }

    //Requires: String
    //Modifies: Sets fileName as parameter given
    public passwordFiles(String name){
        fileName = name;
    }


    //Effects: returns fileName
    public String getFileName(){
        return fileName;
    }

    //Requires: password object
    //Effects: adds parameter (passwords) to listPasswords (arraylist type passwords)
    public void addPassword(password p){
        listPasswords.add(p);
    }
    //Effects: returns a String for the object
    public String toString(){
        return fileName;
    }
    //Effects:gets the File Password
    public String getFilePassword(){
        return filePassword;
    }
    //Effects:gets the number/count of files made.
    public int getFileCount(){
        return fileCount;
    }



}
