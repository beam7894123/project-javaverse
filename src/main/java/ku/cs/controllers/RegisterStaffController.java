package ku.cs.controllers;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.MalformedURLException;
import com.github.saacsos.FXRouter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.models.RegisterList;
import ku.cs.services.RegisterWriteFile;

public class RegisterStaffController extends RegisterController{
    @FXML
    TextField usernameTextfield,passwordPasswordfield,confirmPasswordfield,nameTextfield,surnameTextfield;
    @FXML
    Label labelUsername,labelPassword;
    private String fileNameImage;
    private RegisterList registerList;
    private String path;
    @FXML public ImageView image;
    private RegisterWriteFile writeFile = new RegisterWriteFile("filescsv","staff.csv");

    public void initialize(){
        fileNameImage = "default1.png";
        path = getClass().getResource("/images/default1.png").toExternalForm();
//        image.setImage(new Image(getClass().getResource("/ku/cs/images/default1.png").toExternalForm()));
        image.setImage(new Image(path));
        registerList = new RegisterList();
    }
    @Override
    public void handleOkClick(ActionEvent actionEvent) {
        System.out.println(usernameTextfield.getText());
        registerList = writeFile.readData();
        if (writeFile.checkUserName(registerList,usernameTextfield.getText())){
            System.out.println(1);
            labelUsername.setText("USERNAME IS NOT ALREADY");
        }
        else if (writeFile.checkPassword(passwordPasswordfield.getText(),confirmPasswordfield.getText())){
            labelPassword.setText("PASSWORD IS NOT MATCH");
        }
        else {
            System.out.println(fileNameImage);
            writeFile.checkUserNameAndPassword(registerList,usernameTextfield.getText(),passwordPasswordfield.getText(),nameTextfield.getText(),surnameTextfield.getText(),fileNameImage);
            try {
                FXRouter.goTo("loginStaff");
            } catch (IOException e) {
                System.err.println("ไปที่หน้าhome ไม่ได้");
                System.err.println("ให้ตรวจสอบการกําหนดroute");
            }
        }
    }

    @Override
    public void handleBackButton(ActionEvent actionEvent) {
        try {
          FXRouter.goTo("loginStaff");
        } catch (IOException e) {
            System.err.println("ไปที่หน้าhome ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }

    @Override
    public void handleUploadButton(ActionEvent actionEvent) throws MalformedURLException {
        fileNameImage = writeFile.uploadImageFromFile(actionEvent,image);
    }
}
