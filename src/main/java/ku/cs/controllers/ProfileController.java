package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import ku.cs.models.RegisterList;
import ku.cs.models.RegisterModel;
import ku.cs.models.ReportList;
import ku.cs.models.ReportModel;
import ku.cs.services.DataSource;
import ku.cs.services.RegisterWriteFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

public class ProfileController extends RegisterModel {
    @FXML private Label allName;
    @FXML private Label username;
    @FXML private Label date;
    private DataSource<RegisterList> dataSource;
    private RegisterList registerList;
    private RegisterModel register;

    String usernameText = SignInController.loginUser;
   String loginName = "name";
   String loginSurname = "surname";



    public ProfileController(String name, String surname, String username) {
        super(name, surname, username);
    }





    public void initialize(){
        dataSource = new RegisterWriteFile("filescsv","register.csv");
        registerList = dataSource.readData();
        allName.setText(loginName + " " + loginSurname);
        username.setText(usernameText);
        date.setText(RegisterModel.getDate());
    }

    @FXML
    public void handleBackButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("main");
        } catch (IOException e) {
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
