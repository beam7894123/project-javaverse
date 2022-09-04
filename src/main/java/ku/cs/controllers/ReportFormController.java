package ku.cs.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import ku.cs.models.RegisterList;
import ku.cs.models.RegisterModel;
import ku.cs.models.ReportList;
import ku.cs.models.ReportModel;
import ku.cs.services.DataSource;
import ku.cs.services.RegisterWriteFile;
import ku.cs.services.ReportWriteFile;
import com.github.saacsos.FXRouter;

import java.io.IOException;

public class ReportFormController {
    @FXML
    private TextField topicField,detailField;
    private DataSource<ReportList> dataSource;
    public ReportList reportList;
    private ObservableList<ReportList> reportListObservableList;
    private ReportModel reportModel;

    @FXML
    public void initialize(){
        dataSource = (DataSource<ReportList>) new ReportWriteFile("data","reportList.csv");
        reportList = dataSource.readData();
    }

    @FXML
    ChoiceBox categoryButton = new ChoiceBox();
    HBox hbox = new HBox(categoryButton);
//    private DataSource write = new ReportWriteFile("filescsv", "report.csv");
//    categoryButton
//    choiceBox.getItems().add("Choice 2");
//    choiceBox.getItems().add("Choice 3");

    String value = ("String");
//    categoryButton.g


//    @FXML
//    public void handleSubmitButton(ActionEvent actionEvent){
//        try {
//            ReportModel reportModel = new ReportModel(topicField.getText(),detailField.getText(),null,null,null);
//            reportList.addReport(reportModel);
//            write.writeData1(reportList);
//            System.out.println("Do write file");
//            FXRouter.goTo("main");
//        } catch (IOException e) {
//            System.err.println("ไปที่หน้า main ไม่ได้");
//            System.err.println("ให้ตรวจสอบการกําหนดroute");
//        }
//    }

}
