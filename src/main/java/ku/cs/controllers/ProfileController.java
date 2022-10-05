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
import com.github.saacsos.FXRouter;
public class ProfileController {
    @FXML private Label allName;
    @FXML private Label username;
    @FXML private Label date;
    private DataSource<RegisterList> dataSource;
    private RegisterList registerList;
    private RegisterModel register;

    public void initialize(){
        dataSource = new RegisterWriteFile("filescsv","register.csv");
        registerList = dataSource.readData();
//        allName.setText(RegisterModel.getName() + " " + RegisterModel.getSurname());
//        username.setText(RegisterModel.getUsername());
//        date.setText(RegisterModel.getDate());
    }

    @FXML
    public void handleBackButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("main");
        } catch (IOException e) {
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
