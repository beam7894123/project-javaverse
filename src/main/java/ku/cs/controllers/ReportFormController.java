package ku.cs.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import ku.cs.models.RegisterList;
import ku.cs.models.RegisterModel;
import ku.cs.models.ReportList;
import ku.cs.models.ReportModel;
import ku.cs.services.DataSource;
import ku.cs.services.RegisterWriteFile;
import ku.cs.services.ReportWriteFile;

import java.io.IOException;

public class ReportFormController {
    private DataSource<ReportList> dataSource;
    public ReportList reportList;
    private ObservableList<ReportList> reportListObservableList;
    private ReportModel reportModel;
    @FXML
    ChoiceBox categoryButton = new ChoiceBox();
    HBox hbox = new HBox(categoryButton);
    private DataSource write = new ReportWriteFile("filescsv", "report.csv");
//    hbox.getItems().add("Choice 1");
//    choiceBox.getItems().add("Choice 2");
//    choiceBox.getItems().add("Choice 3");

    String value = ("String");
//    categoryButton.g


    @FXML
    public void handleSubmitButton(ActionEvent actionEvent){
        try {
            dataSource = new RegisterWriteFile("filescsv","report.csv");
//        image.setImage(new Image("/images/default1.png"));
            reportList = new ReportList();
            com.github.saacsos.FXRouter.goTo("main");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า main ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }

}
