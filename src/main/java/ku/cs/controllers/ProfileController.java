package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ProfileController {
    @FXML private Label allName;
    @FXML private Label username;
    @FXML private Label date;
    @FXML private ImageView imageUpload;
    private DataSource<RegisterList> dataSource;
    private RegisterList registerList;
    private RegisterModel register;

   static String usernameText = SignInController.currentUser;
   String loginName;
   String loginSurname;
   static LocalDateTime dateTime = SignInController.currentDateTime;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    String timeReport = dateTime.format(formatter);


    public void initialize(){
        dataSource = new RegisterWriteFile("filescsv","register.csv");
        registerList = dataSource.readData();
        for (RegisterModel registerModel : registerList.getAllCards()){
            registerModel.getUsername();
            if(registerModel.getUsername() .equals(usernameText)){
                System.out.println(registerModel.getName());
                System.out.println(registerModel.getSurname());
                loginName = registerModel.getName();
                loginSurname = registerModel.getSurname();
            }
        }
        allName.setText(loginName + " " + loginSurname );
        username.setText(usernameText);
        date.setText(timeReport);
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
