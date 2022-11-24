package ku.cs.controllers;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.models.UserList;
import ku.cs.services.RegisterWriteFile;


import java.io.IOException;
import java.net.MalformedURLException;

public class  RegisterController {
    Label nameLabel,surnameLabel,usernameLabel,passwordLabel,confirmPasswordLabel,registerLabel,usernameDuplicate;
    @FXML
    private Label labelUsername,labelPassword,usernameAndpasswordLabel;

    @FXML
    private TextField nameTextfield,surnameTextfield,usernameTextfield;
    @FXML
    private PasswordField passwordPasswordfield,confirmPasswordfield;
    @FXML
    private ImageView image;
    private String fileName;
    private String path;
    private RegisterWriteFile registerWriteFile;
    private UserList userList;
    private String  fileNameImage;
    private String date,time;

    private RegisterWriteFile writeFile = new RegisterWriteFile("filescsv","student.csv");

    public void initialize(){
        fileNameImage = "default1.png";
        path = getClass().getResource("/images/default1.png").toExternalForm();
        image.setImage(new Image(path));
        userList = new UserList("student.csv");
    }
    public void handleOkClick(ActionEvent actionEvent) throws IOException {
        labelUsername.setText("");
        labelPassword.setText("");
        System.out.println(usernameTextfield.getText());
        userList = writeFile.readData();
        if (writeFile.checkUserName(userList,usernameTextfield.getText())){
            System.out.println(1);
            labelUsername.setText("USERNAME IS NOT ALREADY");
        }
        else if (writeFile.checkPassword(passwordPasswordfield.getText(),confirmPasswordfield.getText())){
            labelPassword.setText("PASSWORD IS NOT MATCH");
        }
        else {
            System.out.println(fileNameImage);
            writeFile.checkUserNameAndPassword(userList,usernameTextfield.getText(),passwordPasswordfield.getText(),nameTextfield.getText(),surnameTextfield.getText(),fileNameImage);
            try {
                FXRouter.setAnimationType("fade");
                FXRouter.goTo("signIn");
            } catch (IOException e) {
                System.err.println("ไปที่หน้า signIn ไม่ได้");
                System.err.println("ให้ตรวจสอบการกําหนดroute");
            }
        }
    }
    public void handleBackButton(ActionEvent actionEvent){
        try {
            FXRouter.setAnimationType("fade");
            FXRouter.goTo("signIn");}
        catch (IOException e) {
            System.err.println("error");}
    }
    public void handleUploadButton(ActionEvent actionEvent) throws MalformedURLException {

        fileNameImage = writeFile.uploadImageFromFile(actionEvent,image);
    }

}
