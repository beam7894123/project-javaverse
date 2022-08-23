package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    @FXML TextField usernameTextfield;

    @FXML PasswordField passwordPasswordfield;
    private RegisterList control;
    public  String strUserID;
    public  static String strUsername;
    public  String strPassword;
    private RegisterList registerList;
    public static String StrUserID;
    

    private DataSource registerWriteFile;
    @FXML public void initialize(){
        registerList = new RegisterList();
        registerWriteFile = new RegisterWriteFile("filescsv","register.csv");
        registerList.setRegisterModelArrayList(registerWriteFile.readData().getAllCards());
        System.out.println(registerList.getAllCards());
    }

    @FXML
    public void handleConfirmButtonClick(ActionEvent actionEvent){
        if(usernameTextfield.getText().equals("admin")&&passwordPasswordfield.getText().equals("admin")){
            try {
                FXRouter.goTo("admin");
            } catch (IOException e) {
                System.err.println("ไปที่หน้าhome ไม่ได้");
                System.err.println("ให้ตรวจสอบการกําหนดroute");
            }
        }if (registerList.UserCheck(registerList,usernameTextfield,passwordPasswordfield)) {
            strUsername = usernameTextfield.getText();
            strPassword = passwordPasswordfield.getText();
            SignInWriteFile signInWriteFile = new SignInWriteFile("filescsv","register.csv",strUsername,strPassword);
            try {
                signInWriteFile.SignInRecieveReadFile();
                FXRouter.goTo("main");
          } catch (IOException e) {
//                throw new RuntimeException(e);
                System.err.println("ไปที่หน้า main ไม่ได้");
                System.err.println("ให้ตรวจสอบการกําหนดroute");
            }

        }

         else {
            try {
                FXRouter.goTo("main");
            } catch (IOException e) {
                System.err.println("ไปที่หน้าhome ไม่ได้");
                System.err.println("ให้ตรวจสอบการกําหนดroute");
            }
        }

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
    public void handleCreatorButton(ActionEvent actionEvent) {try
    {
        FXRouter.goTo("creator");}
    catch (IOException e) {
        System.err.println("err");}
    }
}
