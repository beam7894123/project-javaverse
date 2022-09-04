package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.image.Image;
import ku.cs.models.ReportList;
import ku.cs.models.ReportModel;
import ku.cs.services.DataSource;
import ku.cs.services.ReportWriteFile;
import com.github.saacsos.FXRouter;

import java.io.IOException;
import java.time.LocalDateTime;

public class MainController {
    private DataSource<ReportList> dataSource;
    private ReportList reportList;
    @FXML private TreeTableView<ReportModel> reportTable;
    @FXML private TreeTableColumn<ReportModel,ReportList> firstCol,secondCol,thirdCol;

    @FXML public void initialize() {
        dataSource = new ReportWriteFile("filescsv", "report.csv");
        reportList = dataSource.readData();
        showReportView();
    }


    private void showReportView() {
        reportTable = new TreeTableView<>();
        firstCol = new TreeTableColumn<>("Topic");
        secondCol = new TreeTableColumn<>("Status");
        thirdCol = new TreeTableColumn<>("Score");
        firstCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("topic"));
        secondCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("status"));
        thirdCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("score"));
        reportTable.getColumns().add(firstCol);
        reportTable.getColumns().add(secondCol);
        reportTable.getColumns().add(thirdCol);
//        TreeItem mercedes = new TreeItem(new ReportModel("Mercedes","SL500",1,"test",
//                String.valueOf(LocalDateTime.now()) ,"Test"));
//        mercedes.getChildren().add(mercedes);

        reportTable.setTreeColumn(firstCol);
        reportTable.refresh();

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
