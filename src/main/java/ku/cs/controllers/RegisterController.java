package ku.cs.controllers;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.models.RegisterList;
import ku.cs.services.DataSource;
import ku.cs.services.RegisterWriteFile;


import java.io.IOException;
import java.net.MalformedURLException;

public class RegisterController {
    Label nameLabel,surnameLabel,usernameLabel,passwordLabel,confirmPasswordLabel,registerLabel,usernameDuplicate;
    @FXML
    private Label labelUsername,labelPassword;

    @FXML
    private TextField nameTextfield,surnameTextfield,usernameTextfield;
    @FXML
    private PasswordField passwordPasswordfield,confirmPasswordfield;
    @FXML
    private ImageView image;
    private String fileName;
    private String path;
    private RegisterWriteFile registerWriteFile;
    private RegisterList registerList;
    private String  fileNameImage;
    private String date,time;

    private RegisterWriteFile writeFile = new RegisterWriteFile("filescsv","register.csv");

    public void initialize(){
        fileNameImage = "default1.png";
        path = getClass().getResource("/images/default1.png").toExternalForm();
//        image.setImage(new Image(getClass().getResource("/ku/cs/images/default1.png").toExternalForm()));
        image.setImage(new Image(path));
        registerList = new RegisterList();
    }
    public void handleOkClick(ActionEvent actionEvent){

        registerList = writeFile.readData();
        writeFile.checkUserNameAndPassword(registerList,usernameTextfield.getText(),labelUsername,labelPassword,passwordPasswordfield.getText(),confirmPasswordfield.getText(),nameTextfield.getText(),surnameTextfield.getText(),fileNameImage);
    }
    public void handleBackButton(ActionEvent actionEvent){
        try {
            FXRouter.goTo("signIn");}
        catch (IOException e) {
            System.err.println("error");}
    }
    public void handleUploadButton(ActionEvent actionEvent) throws MalformedURLException {

        fileNameImage = writeFile.uploadImageFromFile(actionEvent,image);
    }

}
