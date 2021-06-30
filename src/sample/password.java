package sample;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

//Object password
public class password {

    private String passWord = "";
    private String passwordTitle = "";

    //constructors
    public password(){
        passWord = "default";
    }
    public password(String p, String title){
        passWord = p;
        passwordTitle = title;
    }
    //Effects: returns a String for the object
    public String toString(){
        return passwordTitle;
    }

    //Effects: returns password
    public String getPassword(){
        return passWord;
    }
    //Effects: returns passwordTitle (or password Name)
    public String getPasswordTitle(){
        return passwordTitle;
    }
    //Requires:String
    //Modifies:Sets password as the parameter
    public void setPassWord(String pass){
        passWord = pass;
    }
    //Requires:String
    //Modifies:Sets passwordTitle (or password Name) as the parameter
    public void setPasswordTitle(String title){
        passwordTitle = title;
    }

    //Requires:String
    //Effects: Writes the passwordTitle and Password to the parameter (password file name)
    public void writeToFile(String s) throws IOException {

        FileWriter fw = new FileWriter(s, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(passwordTitle + "-\r");
        bw.write(passWord + "\r");
        bw.write(";\r");
        bw.close();
    }

}
