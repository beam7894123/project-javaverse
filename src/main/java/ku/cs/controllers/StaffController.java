package ku.cs.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ku.cs.models.*;
import ku.cs.services.*;
import com.github.saacsos.FXRouter;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

public class StaffController implements Initializable {
    @FXML private TableView<ReportModel> reportTable;
    @FXML private TableColumn<ReportModel,String> topicName;
    @FXML private TableColumn<ReportModel, String> status;
    @FXML private TableColumn<ReportModel, Integer> voteScore;
    @FXML private TableColumn<ReportModel, String> dateTime;
    @FXML private Label topic;
    @FXML private Label detail;
    @FXML private Label score;
    @FXML private Label category;
    @FXML private TextField solve;
    private DataSource<ReportList> dataSource;
    private DataSource<StaffList> staffsource;
    private ReportList reportList;
    private ReportList reportListTemp; //สร้าง list เพื่มแก้เขียนทับ (จริงๆก็ใช้ filter ก็ได้ละแต่มันจะยุ่งยากเอง -w- )
    private StaffList staffList;
    private SortedList<ReportModel> sortedList;
    private ObservableList<ReportModel> reportObservableList;
    private ReportModel selectReport;
    private RegisterWriteFile readDataforStaff;
    String usernameText = (String) FXRouter.getData();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        dataSource = new ReportWriteFile("filescsv", "report.csv");
        reportList = dataSource.readData();
        readDataforStaff = new RegisterWriteFile("filescsv","staff.csv");
        staffList = readDataforStaff.readDataforStaff();
        showReportView();
        reportTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        selectReport(newValue);
                    }
                }
        );
    }

    public void selectReport(ReportModel report){
        selectReport = report;
        topic.setText(report.getTopic());
        detail.setText(report.getDetail());
        score.setText(String.valueOf(report.getVoteScore()));
        category.setText(report.getCategory());
    }

    private void showReportView() {
        Staff staff = staffList.findMyUsername(usernameText);
        reportListTemp = reportList.findMyCategory(staff.getCategory());
//        reportObservableList = FXCollections.observableList(reportList.getAnything());
        reportObservableList = FXCollections.observableList(reportListTemp.getAnything());

        reportTable.setItems(reportObservableList);
        ArrayList<StringConfig> configs = new ArrayList<>();
        configs.add(new StringConfig("title:Topic","field:topic"));
        configs.add(new StringConfig("title:Detail","field:detail"));
        configs.add(new StringConfig("title:Score","field:voteScore"));
        configs.add(new StringConfig("title:Category","field:category"));
        configs.add(new StringConfig("title:Date","field:dateTime"));
        configs.add(new StringConfig("title:Status","field:status"));
        for (StringConfig conf: configs){
            TableColumn col = new TableColumn(conf.get("title"));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            reportTable.getColumns().add(col);
        }
    }

    @FXML
    public void handleLogOutButton(ActionEvent actionEvent){
        try {
            usernameText = "";
            FXRouter.goTo("signIn");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า signIn ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }

    @FXML
    public void submitSolve(ActionEvent actionEvent){
        selectReport.setSolveProblem(solve.getText());
        selectReport.setStatus("success");
        clearText();
        reportTable.refresh();
        reportTable.getSelectionModel().clearSelection();
        dataSource.writeData(reportList);
    }

    @FXML
    public void submitSolve2(ActionEvent actionEvent){
        selectReport.setSolveProblem(solve.getText());
        selectReport.setStatus("processing");
        clearText();
        reportTable.refresh();
        reportTable.getSelectionModel().clearSelection();
        dataSource.writeData(reportList);
    }

    @FXML
    public void clearText(){
        topic.setText("");
        detail.setText("");
        score.setText("");
        category.setText("");
        solve.setText("");
    }

    @FXML
    public void sortByDateUp(ActionEvent actionEvent){
        Collections.sort(reportObservableList, SortList.descendingReportDateTime());
    }

    @FXML
    public void sortByDateDown(ActionEvent actionEvent){
        Collections.sort(reportObservableList, SortList.ascendingReporDateTime());
    }

    @FXML
    public void sortByVoteUp(ActionEvent actionEvent) {
        Collections.sort(reportObservableList, new Comparator<ReportModel>() {
            @Override
            public int compare(ReportModel o1, ReportModel o2) {
                Integer score1 = ((ReportModel) o1).getVoteScore();
                Integer score2 = ((ReportModel) o2).getVoteScore();
                return score2.compareTo(score1);
            }});
    }

    @FXML
    public void sortByVoteDown(ActionEvent actionEvent) {
        Collections.sort(reportObservableList, new Comparator<ReportModel>() {
            @Override
            public int compare(ReportModel o1, ReportModel o2) {
                Integer score1 = ((ReportModel) o1).getVoteScore();
                Integer score2 = ((ReportModel) o2).getVoteScore();
                return score1.compareTo(score2);
            }});
    }
}