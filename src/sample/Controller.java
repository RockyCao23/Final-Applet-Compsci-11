package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Controller {
    public ListView<passwordFiles> passwordFileList = new ListView<passwordFiles>();
    public Button newFileButton;
    public Button loadFileButton;
    public Pane newFilePanel;
    public PasswordField newFilePassword;
    public PasswordField reEnterPassword;
    public Label createNewPasswordStatus;
    public Pane mainPane;
    public Pane filePasswordCheck;
    public PasswordField filePassword;
    public Pane passwordFilePane;
    public Label passwordCheckStatus;
    public ListView<password> passwordList = new ListView<>();
    public Label passwordMain;
    public Label passwordTitleStatus;
    public Label passwordMainStatus;
    public Button savePassword;
    public Button cancelPassword;
    public TextField passwordTitleEdit;
    public TextField passwordEdit;
    public passwordFiles chosen;
    public Label passwordTitle;
    public int newFileCount = 0;
    public Button loadOldFilesButton;
    public Label maxAmountFiles;
    public Button saveNewFileButton;


    //Opens the create-new-file panel
    public void newFile(ActionEvent actionEvent) {
        createNewPasswordStatus.setText("");
        mainPane.setVisible(false);
        newFilePanel.setVisible(true);
        newFilePassword.clear();
        reEnterPassword.clear();

    }
    //Creates and Saves a new password File
    public void saveNewFile(ActionEvent actionEvent) throws IOException{

        String newPassword = newFilePassword.getText();
        String reEnteredPassword = reEnterPassword.getText();
        //Checks if password and password re-entered are correct
        if ( (newPassword.equals(reEnteredPassword)) && (newPassword.length() >=8)){


            ArrayList<String> passwords = CreatePasswordFile.createAllPasswords("password.txt");

            //Max number of password files is 5
            if (newFileCount<=5) {
                if(passwords.size() > 0) {
                    newFileCount = Integer.parseInt(passwords.get(passwords.size() - 1))+1;
                }else{
                    newFileCount++;
                }
                FileWriter fw = new FileWriter("password.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(newFileCount + ",\r");
                bw.close();

                FileWriter fw2 = new FileWriter("File"+newFileCount,true);
                BufferedWriter bw2 = new BufferedWriter(fw2);

                bw2.write("File"+newFileCount+",\r");
                bw2.write(newPassword+"!\r");
                bw2.close();

                passwordFiles newFile = new passwordFiles("File" + newFileCount, newPassword);
                passwordFileList.getItems().add(newFile);
                mainPane.setVisible(true);
                newFilePanel.setVisible(false);
            }else{
                newFileButton.setDisable(true);
                maxAmountFiles.setVisible(true);
                saveNewFileButton.setDisable(true);

            }
        //If passwords do not match
        }else if (!newPassword.equals(reEnteredPassword)){
            createNewPasswordStatus.setText("PASSWORDS DO NOT MATCH");
            newFilePassword.clear();
            reEnterPassword.clear();
        }
        //If passwords are less than 8 characters
        else{
            createNewPasswordStatus.setText("PASSWORDS LESS THAN 8 CHARACTERS. PLEASE ENTER NEW PASSWORD");
            newFilePassword.clear();
            reEnterPassword.clear();
        }


    }
    //Back button - exits create-new-file pane
    public void cancelNewFile(ActionEvent actionEvent) {
        newFilePanel.setVisible(false);
        mainPane.setVisible(true);
    }

    //Accesses password file chosen - opens a security pane
    public void loadFile(ActionEvent actionEvent) {

        filePasswordCheck.setVisible(true);
        mainPane.setVisible(false);
        filePassword.clear();
        passwordCheckStatus.setText("");
        chosen = passwordFileList.getSelectionModel().getSelectedItem();
    }

    //Access Button - Checks if password matches file password in order to access file
    public void filePasswordEnterButton(ActionEvent actionEvent) throws IOException{
        passwordFiles selected = passwordFileList.getSelectionModel().getSelectedItem();

        if(filePassword.getText().equals(CreatePasswordFile.createGetPassword(selected.getFileName()))){
            filePasswordCheck.setVisible(false);
            passwordFilePane.setVisible(true);
        }else {
            passwordCheckStatus.setText("Incorrect Password");
            filePassword.clear();
        }
        //Adds the already known passwords into the passwordsList (List View) for next pane
        ArrayList<password> pass = CreatePasswordFile.createAllInfo(selected.getFileName());

        for (password p : pass) {
            passwordList.getItems().add(p);
        }


    }

    //Back button - exits file security pane
    public void filePasswordCancelButton(ActionEvent actionEvent) {
        filePasswordCheck.setVisible(false);
        mainPane.setVisible(true);
    }


    //If the an object from FileList is clicked, then the load file button will not disabled
    public void fileListClicked(MouseEvent mouseEvent) {
        loadFileButton.setDisable(false);

    }

    //Opens option to add a password into the file
    public void addPasswordButton(ActionEvent actionEvent) {
        passwordEdit.clear();
        passwordTitleEdit.clear();
        passwordMainStatus.setVisible(false);
        passwordTitleStatus.setVisible(false);
        passwordEdit.setVisible(true);
        passwordTitleEdit.setVisible(true);
        savePassword.setVisible(true);
        cancelPassword.setVisible(true);



    }

    //Saves the newly added password into the current password file
    public void savePasswordButton(ActionEvent actionEvent) throws IOException {
        passwordFiles selected = passwordFileList.getSelectionModel().getSelectedItem();
        selected.addPassword(new password(passwordEdit.getText(),passwordTitleEdit.getText()));
        //write into the file
        String fileName = selected.getFileName();

        password newPassword = new password(passwordEdit.getText(),passwordTitleEdit.getText());
        passwordList.getItems().add(newPassword);
        newPassword.writeToFile(fileName);

        passwordEdit.setVisible(false);
        passwordTitleEdit.setVisible(false);
        savePassword.setVisible(false);
        cancelPassword.setVisible(false);
        passwordTitleStatus.setText("");
        passwordMainStatus.setText("");

    }

    //Back button - cancels adding a new password into the current password file
    public void cancelPasswordButton(ActionEvent actionEvent) {
        passwordEdit.setVisible(false);
        passwordTitleEdit.setVisible(false);
        savePassword.setVisible(false);
        cancelPassword.setVisible(false);
    }

    //Back button - exits the password file pane and returns bakc to main pane
    public void passwordBackButton(ActionEvent actionEvent) {
        passwordEdit.clear();
        passwordTitleEdit.clear();
        passwordEdit.setVisible(false);
        passwordTitleEdit.setVisible(false);
        passwordTitleStatus.setText("");
        passwordMainStatus.setText("");
        passwordFilePane.setVisible(false);
        mainPane.setVisible(true);

        passwordList.getItems().clear();


    }


    //If an object in the passwordList is clicked, then it will displays its information
    public void passwordListClicked(MouseEvent mouseEvent) {

        password selected = passwordList.getSelectionModel().getSelectedItem();
        passwordMainStatus.setVisible(true);
        passwordTitleStatus.setVisible(true);
        passwordTitleStatus.setText(selected.getPasswordTitle());
        passwordMainStatus.setText(selected.getPassword());

    }



    //Enter Button (the very first button) - Loads all the previous password files made into the passwordFileList
    public void loadOldFiles(ActionEvent actionEvent) throws IOException{ //enter button

        ArrayList<String> passwords = CreatePasswordFile.createAllPasswords("password.txt");

        for (String f : passwords) {
            passwordFileList.getItems().add(new passwordFiles("File" + f));
        }
        mainPane.setVisible(true);
        loadOldFilesButton.setVisible(false);
        maxAmountFiles.setVisible(false);


    }
}
