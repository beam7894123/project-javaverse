package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.github.saacsos.FXRouter;
import ku.cs.models.RegisterList;
import ku.cs.services.DataSource;
import ku.cs.services.RegisterWriteFile;

import java.io.IOException;

public class HomeController {
    private RegisterList registerList;

    public void setRegisterList(RegisterList registerList){
        this.registerList =  registerList;
    }
    private DataSource<RegisterList> registerWriteFile;

    public void initialize(){
        registerList = new RegisterList();
        registerWriteFile = new RegisterWriteFile("filecsv","register.csv");
//        registerList.setRegisterModelArrayList(registerWriteFile.readData().getAllCards());
        System.out.println(registerList.getAllCards());
    }
    @FXML
    public void handleCreatorButton(ActionEvent actionEvent) {try {
        FXRouter.goTo("creator");}
    catch (IOException e) {
        System.err.println("ไปทีหน้า creator ไม่ได้");System.err.println("ให้ตรวจสอบการกําหนดroute");}}
    //ontrol = new PurchaseList();
    //        registerWriteFile = new RegisterWriteFile("filecsv","purchase.csv");
    //        control.setPurchaseArrayList(registerWriteFile.readData().toList());
    //        System.out.println(control.toList());
    @FXML
    public void handleRegisterButton(ActionEvent actionEvent){
        try {
            FXRouter.goTo("register");
            setRegisterList(registerList);
        } catch (IOException e) {
            System.err.println("ไปที่หน้าregister ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }

    }
    @FXML
    public void handleSignInButton(ActionEvent actionEvent){
        try {
            FXRouter.goTo("signIn");
        } catch (IOException e) {
            System.err.println("ไปที่หน้าhome ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }
}
