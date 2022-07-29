package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class HomeController {
    @FXML
    public void handleCreatorButton(ActionEvent actionEvent) {try {
        com.github.saacsos.FXRouter.goTo("creator");}
    catch (IOException e) {
        System.err.println("ไปทีหน้า creator ไม่ได้");System.err.println("ให้ตรวจสอบการกําหนดroute");}}
}
