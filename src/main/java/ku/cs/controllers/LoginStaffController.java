package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.github.saacsos.FXRouter;
import ku.cs.models.StaffList;
import ku.cs.services.LoginStaffWriteFile;

import java.io.IOException;

public class LoginStaffController {

    @FXML
    TextField usernameTextfield;
    @FXML
    PasswordField passwordPasswordfield;
    @FXML
    Label loginChecker;
    @FXML
    Button backButton;
    private LoginStaffWriteFile loginStaffWriteFile;
    private StaffList staffList;
    String usernameStaff;
    public void initialize(){
        staffList = new StaffList();
        System.out.println(staffList.getAllCards());
    }
    public void handleConfirmButtonClick(ActionEvent actionEvent) throws IOException {
        loginStaffWriteFile = new LoginStaffWriteFile("filescsv","staff.csv",usernameTextfield.getText(),passwordPasswordfield.getText());
        if (!(loginStaffWriteFile.checkConfirmsignIn(staffList,usernameTextfield.getText(),passwordPasswordfield.getText()))){
            loginChecker.setText("Username or Password is incorrect");
        }
        usernameStaff = usernameTextfield.getText();
        System.out.println(usernameStaff);
    }

    public void handleRegisterButtonClick(ActionEvent actionEvent) {
        try {
            FXRouter.setAnimationType("fade");
            FXRouter.goTo("registerStaff");
        } catch (IOException e) {
            System.err.println("ไปที่หน้าhome ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }
    public void handleBackbuttonClick(ActionEvent actionEvent){
        try {
            FXRouter.setAnimationType("fade");
            FXRouter.goTo("signIn");
        } catch (IOException e) {
            System.err.println("ไปที่หน้าhome ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }
}