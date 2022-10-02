package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import ku.cs.models.ReportList;
import ku.cs.models.ReportModel;
import ku.cs.services.DataSource;
import ku.cs.services.RegisterWriteFile;
import ku.cs.services.ReportWriteFile;

import java.io.IOException;

public class DetailController extends ReportModel {
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

    public DetailController(String topic, String detail, Integer voteScore, String category, String dateTime, String authorName) {
        super(topic, detail, voteScore, category, dateTime, authorName);
    }

    public void initialize(){
        dataSource = (DataSource<ReportList>) new ReportWriteFile("filescsv","report.csv");
        reportList = dataSource.readData();
        topicName.setText(super.getTopic());
        topicDetail.setText(super.getDetail());
        voteScoreLabel.setText(String.valueOf(super.getVoteScore()));
    }

    @FXML
    public void voteScoreButton(ActionEvent actionEvent){
        reportModel.addScore(score);
        voteScoreLabel.setText(String.valueOf(super.getVoteScore()));
        Alert status = new Alert(Alert.AlertType.WARNING,"you vote this report already!!");
        status.setTitle("WARNING!?");
        voteButton.setDisable(true);
    }

    @FXML
    public void handleBackButton(ActionEvent actionEvent) {try
    {
        com.github.saacsos.FXRouter.goTo("home");}
    catch (IOException e) {
        System.err.println("ไปที่หน้าhome ไม่ได้");System.err.println("ให้ตรวจสอบการกําหนดroute");}
    }




}