package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.github.saacsos.FXRouter;
import ku.cs.models.RegisterList;
import ku.cs.services.LoginStaffWriteFile;
import ku.cs.services.RegisterWriteFile;
import ku.cs.services.SignInWriteFile;

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
    private RegisterList registerList;

    private RegisterWriteFile writeFile = new RegisterWriteFile("filescsv","staff.csv");
    public void initialize(){
        registerList = new RegisterList();
        writeFile = new RegisterWriteFile("filescsv","staff.csv");
        System.out.println(registerList.getAllCards());
    }
    public void handleConfirmButtonClick(ActionEvent actionEvent) throws IOException {
        loginStaffWriteFile = new LoginStaffWriteFile("filescsv","staff.csv",usernameTextfield.getText(),passwordPasswordfield.getText());
        if (!(loginStaffWriteFile.checkConfirmsignIn(registerList,usernameTextfield.getText(),passwordPasswordfield.getText()))){
            loginChecker.setText("Username or Password is incorrect");
        }
//        try {
//           FXRouter.goTo("for_staff");
//        } catch (IOException e) {
//            System.err.println("ไปที่หน้าhome ไม่ได้");
//            System.err.println("ให้ตรวจสอบการกําหนดroute");
//        }
    }

    public void handleRegisterButtonClick(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("registerStaff");
        } catch (IOException e) {
            System.err.println("ไปที่หน้าhome ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }
    public void handleBackbuttonClick(ActionEvent actionEvent){
        try {
            FXRouter.goTo("signIn");
        } catch (IOException e) {
            System.err.println("ไปที่หน้าhome ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }
}
