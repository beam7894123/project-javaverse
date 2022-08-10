package ku.cs.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.net.URISyntaxException;

public class ChangePasswordController {

    @FXML private PasswordField OldPasswordfield,NewPasswordfield;
    @FXML private TextField usernameTextfield;
    @FXML private Hyperlink handleHelpUrl;

    //อย่าลืมใส่ Code ส่งค่าต่อ
    @FXML
    public void handleBackButtonClick(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้าhome ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }
    @FXML
    public void handleConfirmButtonClick(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("singIn");
        } catch (IOException e) {
            System.err.println("ไปที่หน้าsingIn ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }

//    so many error dont use
//    @FXML
//    public void handleHelpUrl(ActionEvent actionEvent) throws URISyntaxException, IOException {
//        try {
////          Desktop.getDesktop().open(file);
//            Desktop.getDesktop().browse(new URL("google.com"));
//            System.out.println("Going to world wide web");
//        } catch (IOException e) {
//            System.err.println("URL ERROR XmX");
//        }
//    }

}
