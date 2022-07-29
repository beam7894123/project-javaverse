package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.github.saacsos.FXRouter;

import java.io.IOException;

public class HomeController {
    @FXML
    public void handleCreatorButton(ActionEvent actionEvent) {try {
        FXRouter.goTo("creator");}
    catch (IOException e) {
        System.err.println("ไปทีหน้า creator ไม่ได้");System.err.println("ให้ตรวจสอบการกําหนดroute");}}
    @FXML
    public void handleRegisterButton(ActionEvent actionEvent){
        try {
            FXRouter.goTo("register");
        } catch (IOException e) {
            System.err.println("ไปที่หน้าhome ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }
    @FXML
    public void handleSignInButton(ActionEvent actionEvent){
        try {
            FXRouter.goTo("signIn");
        } catch (IOException e) {
            System.err.println("ไปที่หน้าhome ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }
}
