package ku.cs.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.models.RegisterList;
import ku.cs.models.RegisterModel;
import ku.cs.services.DataSource;
import ku.cs.services.AdminReadFile;


import java.io.IOException;

public class AdminStudentListController {

    @FXML private Label nameLabel, surnameLabel, usernameLabel, lastloginLabel, banLabel;
    @FXML private ImageView image;
    String url = getClass().getResource("/images/default1.png").toExternalForm();
    private DataSource<RegisterList> dataSource;
    private RegisterList list;
    @FXML public void initialize(){
        image.setImage(new Image(url));
        dataSource = new AdminReadFile("filescsv","register.csv");
        list = dataSource.readData();
        showStudentListView();
        clearSelectedStudent();
        handleSelectedListView();
    }

    private void clearSelectedStudent() {
        nameLabel.setText("");
        surnameLabel.setText("");
        usernameLabel.setText("");
        lastloginLabel.setText("");
        banLabel.setText("N/A");
    }

    @FXML public void handleBackButtonClick(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("admin");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า admin.fxml ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }

    @FXML private ListView<RegisterModel> studentListView;
    private void showStudentListView() {
        studentListView.getItems().addAll(list.getAllCards() );
        studentListView.refresh();
    }

    private void handleSelectedListView() {
        studentListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<RegisterModel>() {
                    @Override
                    public void changed(ObservableValue<? extends RegisterModel> observable,
                                        RegisterModel oldValue, RegisterModel newValue) {
                        System.out.println(newValue + " is selected");
                        showSelectedStudent(newValue);
                    }
                });
    }

    private void showSelectedStudent(RegisterModel card) {
        nameLabel.setText(card.getName());
        surnameLabel.setText(card.getSurname());
        usernameLabel.setText(card.getUsername());
        lastloginLabel.setText(card.getTime());
//        image.setImage(card.setImage());
//        banLabel.setText(card);
//        usernameLabel.setText(String.format("%.2f", card.getCumulativePurchase()));
    }
}
