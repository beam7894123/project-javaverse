package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.github.saacsos.FXRouter;
import ku.cs.models.RegisterList;
import ku.cs.services.DataSource;
import ku.cs.services.RegisterWriteFile;
import ku.cs.services.SignInWriteFile;

import java.io.IOException;

public class SignInController {
    @FXML
    Label usernameLabel,passwordLabel,loginChecker;
    @FXML
    Button staffButton;
    @FXML TextField usernameTextfield;

    @FXML PasswordField passwordPasswordfield;
    private RegisterList control;
    public  String strUserID;
    public  static String strUsername;
    public  String strPassword;
    private RegisterList registerList;
    public static String StrUserID;
    private String receive;
    private SignInWriteFile signInWriteFile;

    

    private DataSource registerWriteFile;
    private RegisterWriteFile writeFile = new RegisterWriteFile("filescsv","register.csv");
    @FXML public void initialize(){
//        registerWriteFile = new RegisterWriteFile("filescsv","register.csv");
        registerList = new RegisterList();
        registerWriteFile = new RegisterWriteFile("filescsv","register.csv");
        System.out.println(registerList.getAllCards());


    }

    @FXML
    public void handleConfirmButtonClick(ActionEvent actionEvent) throws IOException {
//        registerList = writeFile.readData();
        signInWriteFile = new SignInWriteFile("filescsv","register.csv",usernameTextfield.getText(),passwordPasswordfield.getText());
        if (!(signInWriteFile.checkConfirmsignIn(registerList,usernameTextfield.getText(),passwordPasswordfield.getText()))){
            loginChecker.setText("Username or Password is incorrect");
        }
//        signInWriteFile.SignInRecieveReadFile();
//        signInWriteFile.checkConfirmsignIn(registerList,usernameTextfield.getText(),passwordPasswordfield.getText(),loginChecker);

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
