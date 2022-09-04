package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.Image;
import ku.cs.models.ReportList;
import ku.cs.models.ReportModel;
import ku.cs.services.DataSource;
import ku.cs.services.ReportWriteFile;
import com.github.saacsos.FXRouter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private DataSource<ReportList> dataSource;
    private ReportList reportList;
    private ReportModel selectReport;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

    private void showSelectedReport(ReportModel reportModel){
        selectReport = reportModel;

    }

    @FXML
    public void handleAddReportButton(ActionEvent actionEvent){
        try {
            FXRouter.goTo("addreport");
        } catch (IOException e) {
            System.err.println("ไปที่หน้าhome ไม่ได้");
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

}