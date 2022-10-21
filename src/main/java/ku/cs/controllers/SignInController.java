package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.github.saacsos.FXRouter;
import ku.cs.models.UserList;
import ku.cs.services.DataSource;
import ku.cs.services.RegisterWriteFile;
import ku.cs.services.SignInWriteFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class SignInController {
    @FXML
    Label usernameLabel,passwordLabel,loginChecker;
    @FXML
    Button staffButton;
    @FXML TextField usernameTextfield;
    @FXML PasswordField passwordPasswordfield;
    private UserList control;
    public  String strUserID;
    public  String strPassword;
    private UserList userList;
    private String receive;
    private SignInWriteFile signInWriteFile;
    public static String currentUser;
    public static LocalDateTime currentDateTime;

    private DataSource registerWriteFile;
    private RegisterWriteFile writeFile = new RegisterWriteFile("filescsv","register.csv");
    @FXML public void initialize(){
        userList = new UserList();
        registerWriteFile = new RegisterWriteFile("filescsv","register.csv");
        System.out.println(userList.getAllCards());
    }

    @FXML
    public void handleConfirmButtonClick(ActionEvent actionEvent) throws IOException {
        signInWriteFile = new SignInWriteFile("filescsv","register.csv",usernameTextfield.getText(),passwordPasswordfield.getText());
        if (!(signInWriteFile.checkConfirmsignIn(userList,usernameTextfield.getText(),passwordPasswordfield.getText()))){
            loginChecker.setText("Username or Password is incorrect");
        }
        currentUser = usernameTextfield.getText();
        currentDateTime = LocalDateTime.now();
    }
    @FXML
    public void handleRegisterButtonClick(ActionEvent actionEvent){
        try {
            FXRouter.goTo("register");
        } catch (IOException e) {
            System.err.println("ไปที่หน้าhome ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }
    @FXML
    public void handleStaffbuttonClick(ActionEvent actionEvent){
        try {
            FXRouter.goTo("loginStaff");
        } catch (IOException e) {
            System.err.println("ไปที่หน้าhome ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }

    @FXML
    public void handleCreatorButton(ActionEvent actionEvent) {
        try {
        FXRouter.goTo("creator");}
        catch (IOException e) {
        System.err.println("err");}
    }

}
