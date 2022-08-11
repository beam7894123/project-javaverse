package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class AdminController {

    @FXML public void handleChangePassButtonClick(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("AdminChangePassword");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า AdminChangePassword.fxml ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }

    @FXML public void handleStaffButtonClick(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("AdminStaffList");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า AdminStaffList.fxml ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }

    @FXML public void handleStudentButtonClick(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("AdminStudentList");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า AdminStudentList.fxml ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }
    @FXML public void handleLogOutButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("signIn");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า AdminStudentList.fxml ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }

}
