package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import com.github.saacsos.FXRouter;
import ku.cs.models.User;

public class AdminController {

    @FXML public void handleChangePassButtonClick(ActionEvent actionEvent){
        try {
            FXRouter.setAnimationType("fade");
            FXRouter.goTo("AdminChangePassword");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า adminChangePassword.fxml ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }

    @FXML public void handleStaffButtonClick(ActionEvent actionEvent){
        try {
            FXRouter.setAnimationType("fade");
           FXRouter.goTo("AdminStaffList");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า adminStaffList.fxml ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }

    @FXML public void handleStudentButtonClick(ActionEvent actionEvent){
        try {
            FXRouter.setAnimationType("fade");
            FXRouter.goTo("AdminStudentList");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า adminStudentList.fxml ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }
    @FXML public void handleLogOutButton(ActionEvent actionEvent){
        try {
            FXRouter.setAnimationType("fade");
           FXRouter.goTo("signIn");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า adminStudentList.fxml ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }

    @FXML public void handleAddButtonClick(ActionEvent actionEvent){
        try {
            FXRouter.setAnimationType("fade");
            FXRouter.goTo("registerStaff");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า adminStudentList.fxml ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }
}
