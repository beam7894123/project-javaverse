package ku.cs.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ku.cs.models.ReportList;
import ku.cs.models.ReportModel;
import ku.cs.services.DataSource;
import ku.cs.services.ReportWriteFile;
import com.github.saacsos.FXRouter;
import ku.cs.services.SortList;
import ku.cs.services.StringConfig;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML private TableView<ReportModel> reportTable;
    @FXML private TextField input;
    @FXML private Button howTouseButton;
    SortedList<ReportModel> sortedList;

    private DataSource<ReportList> dataSource;
    private ReportList reportList;
    private ObservableList<ReportModel> reportObservableList;
    private ArrayList<ReportModel> reports;
    public static String selectReport;
    String usernameText = SignInController.currentUser;
    String UsernameFromSignin = (String) FXRouter.getData();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(usernameText);
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
        reportObservableList = FXCollections.observableArrayList(reportList.getAnything());
        sortedList = new SortedList(reportObservableList);
        FilteredList<ReportModel> filterCategory = new FilteredList<>(sortedList, b-> true);

        input.textProperty().addListener((observable, oldValue, newValue) -> {
            filterCategory.setPredicate(reportModel -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (reportModel.getCategory().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }else if (reportModel.getAuthorName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }else if (reportModel.getTopic().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }else if (reportModel.getStatus().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }else if (reportModel.getDetail().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<ReportModel> sortedData = new SortedList<>(filterCategory);
        sortedData.comparatorProperty().bind(reportTable.comparatorProperty());

        reportTable.setItems(sortedData);
        ArrayList<StringConfig> configs = new ArrayList<>();
        configs.add(new StringConfig("title:Topic","field:topic"));
        configs.add(new StringConfig("title:Detail","field:detail"));
        configs.add(new StringConfig("title:Score","field:voteScore"));
        configs.add(new StringConfig("title:Date","field:dateTime"));
        configs.add(new StringConfig("title:Category","field:category"));
        configs.add(new StringConfig("title:Author","field:authorName"));
        configs.add(new StringConfig("title:Status","field:status"));
        for (StringConfig conf: configs){
            TableColumn col = new TableColumn(conf.get("title"));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            reportTable.getColumns().add(col);
        };
    }

    @FXML private void showSelectedReport(ReportModel reportModel){
        selectReport = reportModel.getTopic();
        try {
            FXRouter.setAnimationType("fade");
            FXRouter.goTo("detail");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า detail ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }

    @FXML
    public void handleAddReportButton(ActionEvent actionEvent){
        try {
            FXRouter.setAnimationType("fade");
            FXRouter.goTo("addreport");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า report ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }
    @FXML
    public void handleLogOutButton(ActionEvent actionEvent){
        try {
            usernameText = "";
            System.out.println(usernameText);
            FXRouter.goTo("signIn");
            FXRouter.setAnimationType("fade");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า signIn ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }

    @FXML
    public void profileButton(ActionEvent actionEvent){
        try {
            FXRouter.goTo("profile", UsernameFromSignin);
            FXRouter.setAnimationType("fade");
        } catch (IOException e) {
            System.out.println("err");
            System.out.println(e);
        }
    }
//    @FXML
//    public void handleHowtoUseButton(ActionEvent actionEvent){
//        try {
//            FXRouter.goTo("howTouse");
//            FXRouter.setAnimationType("fade");
//        } catch (IOException e) {
//            System.out.println("error howtouse");
//            System.out.println(e);
//        }
//    }

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