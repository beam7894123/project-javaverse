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
        try {
            FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้าhome ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }

}
