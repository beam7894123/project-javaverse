package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.github.saacsos.FXRouter;
import java.io.IOException;

public class RegisterController {
    @FXML
    private Label nameLabel,surnameLabel,usernameLabel,passwordLabel,confirmPasswordLabel,registerLabel,usernameDuplicate;
    @FXML
    private TextField nameTextfield,surnameTextfield,usernameTextfield;
    @FXML
    private PasswordField passwordPasswordfield,confirmPasswordfield;
    @FXML
    private ImageView image;

    String url = getClass().getResource("/images/default1.png").toExternalForm();
    @FXML public void initialize(){
        image.setImage(new Image(url));
    }

    @FXML
    public void handleOkClick(ActionEvent actionEvent){
        try {
            FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้าhome ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }

    @FXML
    public void handleBackButton(ActionEvent actionEvent) {try
    {
        FXRouter.goTo("signIn");}
    catch (IOException e) {
        System.err.println("error");}
    }
}
