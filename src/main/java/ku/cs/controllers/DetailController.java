package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    @FXML Label solveText;

    private ReportModel reportModel;
    private DataSource<ReportList> dataSource;
    private ReportList reportList;
    private int score = 1;
    String currentReport = MainController.selectReport;
    String topic,detail,vote,solve;

    ReportWriteFile writefile = new ReportWriteFile("filescsv", "report.csv");


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
                solve = String.valueOf(reportModel.getSolveProblem());
            }
        }
        topicName.setText(topic);
        topicDetail.setText(detail);
        voteScoreLabel.setText(vote);
        solveText.setText(solve);
    }

    @FXML
    public void voteScoreButton(MouseEvent mouseEvent){
        for (ReportModel reportModel : reportList.getReports()){
            if(currentReport .equals(reportModel.getTopic())){
                int score1 = reportModel.getVoteScore();
                score1++;
                voteScoreLabel.setText(String.valueOf(vote));
                reportModel.setVoteScore(score1);
                dataSource.writeData(reportList);
                Alert status = new Alert(Alert.AlertType.WARNING,"you vote this report already!!");
                status.setTitle("WARNING!?");
                voteButton.setDisable(true);
            }
        }
//        voteScoreLabel.setText(String.valueOf(vote));
//        Alert status = new Alert(Alert.AlertType.WARNING,"you vote this report already!!");
//        status.setTitle("WARNING!?");
//        voteButton.setDisable(true);
////        starbutton.setDisable(true);
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