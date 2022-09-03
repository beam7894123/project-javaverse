package ku.cs.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import ku.cs.models.*;
import ku.cs.services.DataSource;
import ku.cs.services.RegisterWriteFile;
import ku.cs.services.ReportWriteFile;
import com.github.saacsos.FXRouter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReportFormController {
    @FXML
    private TextField topicField,detailField,authorField;
    private DataSource<ReportList> dataSource;
    public ReportList reportList;
    private ReportModel reportModel;
    @FXML ComboBox<String> categoryBox;
    private DataSource write = new ReportWriteFile("filescsv", "report.csv");

    @FXML public void initialize() {
        dataSource = new ReportWriteFile("filescsv", "report.csv");
        reportList = new ReportList();
        categoryBox.setItems(FXCollections.observableArrayList("Person","Facilities","Building",
                "Learning/Lesson","Traffic/Transport"));
        authorField.setText("your name");
    }


    @FXML
    public void handleSubmitButton(ActionEvent actionEvent){
        try {
            ReportModel reportModel = new ReportModel(topicField.getText(),detailField.getText(),1,
                    categoryBox.getValue(),String.valueOf(LocalDateTime.now()),authorField.getText());
            reportList.addReport(reportModel);
            write.writeData1(reportList);
            System.out.println("Do write file");
            FXRouter.goTo("main");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า main ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }

}
