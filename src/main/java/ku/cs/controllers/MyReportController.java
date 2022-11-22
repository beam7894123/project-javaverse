package ku.cs.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ku.cs.models.ReportList;
import ku.cs.models.ReportModel;
import ku.cs.services.DataSource;
import ku.cs.services.ReportWriteFile;
import ku.cs.services.StringConfig;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MyReportController implements Initializable {
    @FXML
    private TableView<ReportModel> myreportTable;
   @FXML private TextField input;


    private DataSource<ReportList> dataSource;
    private ReportList reportList;
    private ObservableList<ReportModel> reportObservableList;
    SortedList<ReportModel> sortedList;
    FilteredList<ReportModel> filteredList;
    public String selectReport;
    String usernameText = SignInController.currentUser;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(usernameText);
        dataSource = new ReportWriteFile("filescsv", "report.csv");
        reportList = dataSource.readData();
        showReportView();
        myreportTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        showSelectedReport(newValue);
                    }
                }
        );

    }
    private void showReportView() {
        ArrayList<ReportModel> reports = new ArrayList<>();
        reportObservableList = FXCollections.observableArrayList(reportList.getAnything());

        ReportList myReport = reportList.findMyThing(usernameText);
        reportObservableList = FXCollections.observableArrayList(myReport.getAnything());
        myreportTable.setItems(reportObservableList);
        ArrayList<StringConfig> configs = new ArrayList<>();
        configs.add(new StringConfig("title:Topic","field:topic"));
        configs.add(new StringConfig("title:Score","field:voteScore"));
        configs.add(new StringConfig("title:Category","field:category"));
        configs.add(new StringConfig("title:Date","field:dateTime"));
        configs.add(new StringConfig("title:Author","field:authorName"));
        configs.add(new StringConfig("title:Status","field:status"));
        for (StringConfig conf: configs){
            TableColumn col = new TableColumn(conf.get("title"));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            myreportTable.getColumns().add(col);
        }
        }

    @FXML private void showSelectedReport(ReportModel reportModel){
        selectReport = reportModel.getTopic();
        try {
            com.github.saacsos.FXRouter.goTo("detail",selectReport);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า detail ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }

    @FXML
    public void handleBackButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("profile");
        } catch (IOException e) {
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
