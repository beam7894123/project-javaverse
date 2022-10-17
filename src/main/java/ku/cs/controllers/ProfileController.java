package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ku.cs.models.UserList;
import ku.cs.models.User;
import ku.cs.services.DataSource;
import ku.cs.services.RegisterWriteFile;

import java.io.IOException;

import com.github.saacsos.FXRouter;
public class ProfileController {
    @FXML private Label allName;
    @FXML private Label username;
    @FXML private Label date;
    private DataSource<UserList> dataSource;
    private UserList userList;
    private User register;

    public void initialize(){
        dataSource = new RegisterWriteFile("filescsv","register.csv");
        userList = dataSource.readData();
//        allName.setText(RegisterModel.getName() + " " + RegisterModel.getSurname());
//        username.setText(RegisterModel.getUsername());
//        date.setText(RegisterModel.getDate());
    }

    @FXML
    public void handleBackButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("main");
        } catch (IOException e) {
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
