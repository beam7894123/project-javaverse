package ku.cs.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ku.cs.models.ReportList;
import ku.cs.models.ReportModel;
import ku.cs.models.User;
import ku.cs.models.UserList;
import ku.cs.services.DataSource;
import ku.cs.services.RegisterWriteFile;
import ku.cs.services.ReportWriteFile;
import com.github.saacsos.FXRouter;
import ku.cs.services.StringConfig;

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
    private DataSource<UserList> staffsource;
    private ReportList reportList;
    private UserList registerList;
    private SortedList<ReportModel> sortedList;
    private ObservableList<ReportModel> reportObservableList;
    private ReportModel selectReport;
    private ArrayList<ReportModel> reports;
    private DataSource<UserList> dataSourceUser;
    private User staff = new User("Facilities","Facilities","Facilities","Facilities","Facilities","Facilities","Facilities","Facilities");
    String usernameText = SignInController.currentUser;
    String loginName;
    String loginSurname;
    private UserList userList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataSource = new ReportWriteFile("filescsv", "report.csv");
        reportList = dataSource.readData();

        dataSourceUser = new RegisterWriteFile("filescsv","staff.csv");
        reportList = dataSource.readData();

        System.out.println(User.getCategory());
        StaffController staffController = new StaffController();

        int count = 0;
        reports = new ArrayList<>();
        for (ReportModel reportModel : reportList.getReports()) {
            System.out.println(User.getCategory());
            System.out.println(reportModel.getCategory());
            if (User.getCategory().equals(reportModel.getCategory())) {
                reports.add(reportModel);
            }
            count = count + 1;
        }


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

        reportObservableList = FXCollections.observableArrayList(reportList.getReports());
        staffsource = new RegisterWriteFile("filescsv","staff.csv");
        registerList = staffsource.readData();
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
        };
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
    public void submitSolve(ActionEvent actionEvent){
        selectReport.setSolveProblem(solve.getText());
        selectReport.setStatus("done");
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
        Collections.sort(reportObservableList, new Comparator<ReportModel>() {
            @Override
            public int compare(ReportModel o1, ReportModel o2) {
                String dateTime1 = ((ReportModel) o1).getDateTime();
                String dateTime2 = ((ReportModel) o2).getDateTime();
                return dateTime2.compareTo(dateTime1);
            }});
    }

    @FXML
    public void sortByDateDown(ActionEvent actionEvent){
        Collections.sort(reportObservableList, new Comparator<ReportModel>() {
            @Override
            public int compare(ReportModel o1, ReportModel o2) {
                String dateTime1 = ((ReportModel) o1).getDateTime();
                String dateTime2 = ((ReportModel) o2).getDateTime();
                return dateTime1.compareTo(dateTime2);
            }});
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