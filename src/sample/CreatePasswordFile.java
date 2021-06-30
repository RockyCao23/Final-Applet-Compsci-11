package sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class CreatePasswordFile {

    private static String fileName = "TEST";
    private static ArrayList<String> fileList = new ArrayList<>();
    private static String filePassword = "noPass";
    private static FileReader fr;
    private static BufferedReader br;
    private static String passWord = "";
    private static String passwordTitle = "";
    private static ArrayList<password> pass = new ArrayList<>();

    //Requires: String
    //Creates all the passwordFiles previously made and returns an ArrayList of passwordFiles names
    public static ArrayList createAllPasswords(String fileName) throws IOException {
        fileList.clear();
        fr = new FileReader(fileName);
        br = new BufferedReader(fr);
        String line;
        String passString = "";
        while ((line = br.readLine()) != null){
                passString += line;
            }
                parseFileName(passString);
                passString = "";
                return fileList;
    }

    //Requires: String
    //Effects:parses through each line (parameter) in txt file and gets the file Number
    private static void parseFileName(String s){
        String passwordFileName = "";
        for (int i = 0; i < s.length(); i++){

            passwordFileName = s.substring(i,i+1);
            fileList.add(passwordFileName);
            i++;
        }
    }

    //Requires: String
    //Effects: Gets the password of passwordFile (parameter) chosen
    public static String createGetPassword(String fileName) throws IOException {
        fileList.clear();
        fr = new FileReader(fileName);
        br = new BufferedReader(fr);
        String line;
        String passString = "";
        while ((line = br.readLine()) != null){
            passString += line;
        }
        passString = passString.substring(passString.indexOf(",")+1,passString.indexOf("!"));;
        return passString;
    }


    //Requires: String
    //Effects: Retrieves data (passwords and password names) from txt file chosen (parameter)
    public static ArrayList createAllInfo(String fileName) throws IOException {
        pass.clear();
        fr = new FileReader(fileName);
        br = new BufferedReader(fr);
        String line;
        String friendString = "";
        while ((line = br.readLine()) != null){
            if(!line.equals(";")){
                friendString += line;
            }else{
                parsePassInfo(friendString);
                friendString = "";
            }
        }return pass;
    }

    //Requires: String
    //Effects: parses through each line (parameter) in txt file and gets the password and password Name
    private static void parsePassInfo(String s){
        int pos = 0;
        String passwordTitle = "";
        String passWord = "";

        for (int i = s.indexOf("!")+1; i < s.length(); i++){
            if (s.substring(i,i+1).equals("-")){
                pos = i;
                passwordTitle = s.substring(s.indexOf("!")+1,pos);
                passWord = s.substring(pos+1);
            }

        }
        pass.add(new password(passWord,passwordTitle));

    }

}
