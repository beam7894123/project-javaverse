package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.Image;
import ku.cs.models.ReportList;
import ku.cs.models.ReportModel;
import ku.cs.services.DataSource;
import ku.cs.services.ReportWriteFile;

import java.io.IOException;

public class MainController {
    private DataSource<ReportList> dataSource;
    private ReportList reportList;

    @FXML public void initialize() {
//        dataSource = new ReportWriteFile("filescsv", "report.csv");
//        reportList = dataSource.readData1();
        showReportView();
    }
    @FXML private TreeTableView<ReportModel> reportTable;
    TreeTableColumn firstCol = new TreeTableColumn<>("หัวข้อเรื่อง");
    TreeTableColumn secondCol = new TreeTableColumn<>("Status");
    TreeTableColumn thirdCol = new TreeTableColumn<>("คะแนน");
    private void showReportView() {
//        reportTable.getColumns().addAll();
//        reportTable.refresh();
    }



//    reportTable.getColumns().addAll(firstCol, secondCol, thirdCol);



    @FXML
    public void handleAddReportButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("addreport");
        } catch (IOException e) {
            System.err.println("ไปที่หน้าhome ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }
    @FXML
    public void handleLogOutButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("signIn");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า signIn ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }
    @FXML
    public void handleAdminButtonClick(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("register");
        } catch (IOException e) {
            System.err.println("ไปที่หน้าhome ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }




}
