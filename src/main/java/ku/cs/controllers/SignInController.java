package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import com.github.saacsos.FXRouter;

import java.io.IOException;

public class SignInController {
    @FXML
    Label usernameLabel,passwordLabel;
    @FXML TextField usernameTextfield,passwordPasswordfield;

    @FXML
    public void handleConfirmButtonClick(ActionEvent actionEvent){
        if(usernameTextfield.getText().equals("admin")&&passwordPasswordfield.getText().equals("admin")){
            try {
                FXRouter.goTo("admin");
            } catch (IOException e) {
                System.err.println("ไปที่หน้าhome ไม่ได้");
                System.err.println("ให้ตรวจสอบการกําหนดroute");
            }
        }
        else {
            try {
                FXRouter.goTo("main");
            } catch (IOException e) {
                System.err.println("ไปที่หน้าhome ไม่ได้");
                System.err.println("ให้ตรวจสอบการกําหนดroute");
            }
        }

    }
    @FXML
    public void handleRegisterButtonClick(ActionEvent actionEvent){
        try {
            FXRouter.goTo("register");
        } catch (IOException e) {
            System.err.println("ไปที่หน้าhome ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }

    @FXML
    public void handleCreatorButton(ActionEvent actionEvent) {try
    {
        FXRouter.goTo("creator");}
    catch (IOException e) {
        System.err.println("err");}
    }
}
