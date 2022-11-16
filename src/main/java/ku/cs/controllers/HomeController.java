package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.github.saacsos.FXRouter;
import ku.cs.models.UserList;
import ku.cs.services.DataSource;
import ku.cs.services.RegisterWriteFile;

import java.io.IOException;

public class HomeController {
    private UserList userList;

    public void setRegisterList(UserList userList){
        this.userList = userList;
    }
    private DataSource<UserList> registerWriteFile;

    public void initialize(){
        userList = new UserList();
        registerWriteFile = new RegisterWriteFile("filecsv","student.csv");
        System.out.println(userList.getAllCards());
    }
    @FXML
    public void handleCreatorButton(ActionEvent actionEvent) {try {
        FXRouter.setAnimationType("fade");
        FXRouter.goTo("creator");}
    catch (IOException e) {
        System.err.println("ไปทีหน้า creator ไม่ได้");System.err.println("ให้ตรวจสอบการกําหนดroute");}}

    @FXML
    public void handleRegisterButton(ActionEvent actionEvent){
        try {
            FXRouter.setAnimationType("fade");
            FXRouter.goTo("register");
            setRegisterList(userList);
        } catch (IOException e) {
            System.err.println("ไปที่หน้าregister ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }

    }
    @FXML
    public void handleSignInButton(ActionEvent actionEvent){
        try {
            FXRouter.setAnimationType("fade");
            FXRouter.goTo("signIn");
        } catch (IOException e) {
            System.err.println("ไปที่หน้าhome ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }
}
