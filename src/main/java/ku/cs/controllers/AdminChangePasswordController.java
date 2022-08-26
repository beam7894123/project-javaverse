package ku.cs.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;
import com.github.saacsos.FXRouter;
public class AdminChangePasswordController {

    @FXML private PasswordField OldPasswordfield,NewPasswordfield;
    @FXML private TextField usernameTextfield;

    @FXML
    public void handleBackButtonClick(ActionEvent actionEvent){
        try {
            FXRouter.goTo("admin");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า admin ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }
    @FXML
    public void handleConfirmButtonClick(ActionEvent actionEvent){
        try {
            FXRouter.goTo("signIn");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า singIn ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }

}
