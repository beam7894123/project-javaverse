package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import ku.cs.models.User;
import ku.cs.models.ReportList;
import ku.cs.models.ReportModel;
import ku.cs.services.DataSource;
import ku.cs.services.RegisterWriteFile;
import ku.cs.services.ReportWriteFile;

import java.io.IOException;

public class DetailController {
    @FXML
    private Label topicName;
    @FXML
    private Label topicDetail;
    @FXML
    private Label voteScoreLabel;
    @FXML
    private Button voteButton;

    private ReportModel reportModel;
    private DataSource<ReportList> dataSource;
    private ReportList reportList;
    private int score = 1;
    String currentReport = MainController.selectReport;
    String topic;
    String detail;
    String vote;

//    public DetailController(String topic, String detail, Integer voteScore, String category, String dateTime, String authorName) {
//        super(topic, detail, voteScore, category, dateTime, authorName);
//    }

    public void initialize(){
        dataSource = (DataSource<ReportList>) new ReportWriteFile("filescsv","report.csv");
        reportList = dataSource.readData();
        for (ReportModel reportModel : reportList.getReports()){
            if(currentReport .equals(reportModel.getTopic())){
                System.out.println(reportModel.getTopic());
                System.out.println(reportModel.getDetail());
                System.out.println(reportModel.getVoteScore());
                topic = reportModel.getTopic();
                detail = reportModel.getDetail();
                vote = String.valueOf(reportModel.getVoteScore());
            }
        }
        topicName.setText(topic);
        topicDetail.setText(detail);
        voteScoreLabel.setText(vote);
    }

    @FXML
    public void voteScoreButton(ActionEvent actionEvent){
        reportModel.addScore(score);
        voteScoreLabel.setText(String.valueOf(vote));
        Alert status = new Alert(Alert.AlertType.WARNING,"you vote this report already!!");
        status.setTitle("WARNING!?");
        voteButton.setDisable(true);
    }

    @FXML
    public void handleBackButton(ActionEvent actionEvent) {try
    {
        com.github.saacsos.FXRouter.goTo("main");}
    catch (IOException e) {
        System.err.println("ไปที่หน้า main ไม่ได้");
        System.err.println("ให้ตรวจสอบการกําหนดroute");}
    }




}