package ku.cs.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ku.cs.models.*;
import ku.cs.services.DataSource;
import ku.cs.services.ReportWriteFile;
import com.github.saacsos.FXRouter;
import ku.cs.models.ReportModel;
import ku.cs.services.SignInWriteFile;
import ku.cs.controllers.SignInController;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReportFormController extends SignInController {
    LocalDateTime localDateTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    String timeReport = localDateTime.format(formatter);
    @FXML Label voteScoreLabel;
    @FXML
    private TextField topicField,detailField;
    private DataSource<ReportList> dataSource;
    public ReportList reportList;
    private ReportModel reportModel;
    @FXML ChoiceBox<String> categoryButton;
    private DataSource<ReportList> write = new ReportWriteFile("filescsv", "report.csv");


    @FXML public void initialize() {
        dataSource = new ReportWriteFile("filescsv", "report.csv");
        reportList = write.readData();
        categoryButton.setItems(FXCollections.observableArrayList("Person","Facilities","Building",
                "Learning","Traffic"));
    }


    @FXML
    public void handleSubmitButton(ActionEvent actionEvent){
        if(topicField.getText() != "" & detailField.getText() != "") {
            try {
                ReportModel reportModel = new ReportModel(topicField.getText(), detailField.getText(),0,
                        categoryButton.getValue(),timeReport, currentUser);
                reportList.addReport(reportModel);
                write.writeData(reportList);
                System.out.println("Do write file");
                FXRouter.goTo("main");
            } catch (IOException e) {
                System.err.println("ไปที่หน้า main ไม่ได้");
                System.err.println("ให้ตรวจสอบการกําหนดroute");
            }
        }
        else {
            Alert text = new Alert(Alert.AlertType.WARNING,"PLEASE FILL THE FORM!!!"); text.show();
        }
    }

    @FXML
    public void handleBackButtonClick(ActionEvent actionEvent){
        try {
            FXRouter.goTo("main");
        } catch (IOException e) {
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }
}