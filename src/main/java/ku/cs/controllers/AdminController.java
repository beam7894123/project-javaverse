package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
import com.github.saacsos.FXRouter;
import ku.cs.models.RegisterModel;
import ku.cs.services.DataSource;
import ku.cs.services.RegisterWriteFile;

public class AdminController {
    private RegisterModel john, anna, harry;
//    private DataSource write = new RegisterWriteFile("filescsv", "register.csv");

    @FXML
    public void initialize(){
//        john = new RegisterModel("John ", "Smith","john","123",null,null,);
//        anna = new RegisterModel("Anna ", "Frost","anna","ann",null,null);
//        harry = new RegisterModel("Harry Potter","Potter","harry","999",null,null );


    }
    @FXML public void handleChangePassButtonClick(ActionEvent actionEvent){
        try {
            FXRouter.goTo("AdminChangePassword");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า adminChangePassword.fxml ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }

    @FXML public void handleStaffButtonClick(ActionEvent actionEvent){
        try {
           FXRouter.goTo("AdminStaffList");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า adminStaffList.fxml ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }

    @FXML public void handleStudentButtonClick(ActionEvent actionEvent){
        try {
            FXRouter.goTo("AdminStudentList");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า adminStudentList.fxml ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }
    @FXML public void handleLogOutButton(ActionEvent actionEvent){
        try {
           FXRouter.goTo("signIn");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า adminStudentList.fxml ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }

}
