package ku.cs.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import ku.cs.models.ReportList;
import ku.cs.models.ReportModel;
import ku.cs.services.DataSource;
import ku.cs.services.ReportWriteFile;
import com.github.saacsos.FXRouter;
import ku.cs.services.StringConfig;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML private TableView<ReportModel> reportTable;
    @FXML private TableColumn<ReportModel,String> topicName;
    @FXML private TableColumn<ReportModel, String> status;
    @FXML private TableColumn<ReportModel, Integer> voteScore;
    @FXML private TableColumn<ReportModel, String> dateTime;

    private DataSource<ReportList> dataSource;
    private ReportList reportList;
    private ObservableList<ReportModel> reportObservableList;
    private ReportModel selectReport;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataSource = new ReportWriteFile("filescsv", "report.csv");
        reportList = dataSource.readData();
        showReportView();
        reportTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showSelectedReport(newValue);
            }
        }
        );

    }

    private void showReportView() {
        reportObservableList = FXCollections.observableArrayList(reportList.getReports());
        reportTable.setItems(reportObservableList);

        ArrayList<StringConfig> configs = new ArrayList<>();
        configs.add(new StringConfig("title:Topic","field:reportTopic"));
        configs.add(new StringConfig("title:Detail","field:reportDetail"));
        configs.add(new StringConfig("title:Score","field:reportVoteScore"));
        configs.add(new StringConfig("title:Category","field:reportCategory"));
        configs.add(new StringConfig("title:Date","field:dateTime"));
        configs.add(new StringConfig("title:Author","field:authorName"));
        configs.add(new StringConfig("title:Status","field:reportStatus"));
        for (StringConfig conf: configs){
            TableColumn col = new TableColumn(conf.get("title"));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            reportTable.getColumns().add(col);
        };
    }

    private void showSelectedReport(ReportModel reportModel){
//        ไปหน้าใหม่และโชว์ detail ของ report (´;ω;)
        selectReport = reportModel;
    }

    @FXML
    public void handleAddReportButton(ActionEvent actionEvent){
        try {
            FXRouter.goTo("addreport");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า report ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }
    @FXML
    public void handleLogOutButton(ActionEvent actionEvent){
        try {
            FXRouter.goTo("signIn");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า signIn ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }
    @FXML
    public void handleAdminButtonClick(ActionEvent actionEvent){
        try {
            FXRouter.goTo("register");
        } catch (IOException e) {
            System.err.println("ไปที่หน้าhome ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }

    @FXML
    public void profileButton(ActionEvent actionEvent){
        try {
            FXRouter.goTo("profile");
        } catch (IOException e) {
            System.out.println("err");
        }
    }

    @FXML
    public void sortByDate(ActionEvent actionEvent){
//        Collections.sort(allDate,new sortItems());
//        รอสร้าง allDate เป็น list ของวันทั้งหมด (´;ω;)
    }

    @FXML
    public void sortByVote(ActionEvent actionEvent) {
        Collections.sort(reportObservableList, new Comparator<ReportModel>() {
            @Override
            public int compare(ReportModel o1, ReportModel o2) {
                Integer score1 = ((ReportModel) o1).getVoteScore();
                Integer score2 = ((ReportModel) o2).getVoteScore();
                return score1.compareTo(score2);
            }});
    }
}