package ku.cs.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import ku.cs.models.RegisterList;
import ku.cs.models.RegisterModel;
import ku.cs.services.DataSource;
import ku.cs.services.AdminReadFile;
import com.github.saacsos.FXRouter;
import ku.cs.services.RegisterWriteFile;

import java.io.IOException;

public class AdminStudentListController {
    @FXML Label nameLabel, surnameLabel, usernameLabel, lastloginLabel, banLabel;
    @FXML
    TextArea banReasontextArea;

    private String fileNameImage,path;
    RegisterWriteFile writeFile = new RegisterWriteFile("filecsv","register.csv");

//    @FXML
//    public void initialize(){
//        fileNameImage = "default1.png";
////        dataSource = new RegisterWriteFile("filescsv","register.csv");
//        path = getClass().getResource("/images/default1.png").toExternalForm();
//        image.setImage(new Image(path));
//        registerList = writeFile.readData();
//        handleShowListView();
//        clearSelectedListView();
//        handleSelectListView();
////
//    }
//    private void handleShowListView(){
//        studentListView.getItems().addAll(registerList.getAllCards());
//        studentListView.refresh();
//    }
//    private void handleSelectListView(){
//        studentListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<RegisterModel>() {
//            @Override
//            public void changed(ObservableValue<? extends RegisterModel> observableValue, RegisterModel registerModel, RegisterModel t1) {
//                System.out.println(t1 + "is selected");
//                handleSelectView(t1);
//            }
//        });
//    }
//    public void handleSelectView(RegisterModel model){
//        RegisterModel registerModel = studentListView.getSelectionModel().getSelectedItem();
//        nameLabel.setText(model.getName());
//        surnameLabel.setText(model.getSurname());
//    }
//    private void clearSelectedListView(){
//        nameLabel.setText("");
//        surnameLabel.setText("");
//        usernameLabel.setText("");
//        lastloginLabel.setText("");
//        banLabel.setText("");
//
//    }
//



    @FXML private ImageView image;
    @FXML ListView<RegisterModel> studentListView;
    String url = getClass().getResource("/images/default1.png").toExternalForm();
    private DataSource<RegisterList> dataSource;
    private RegisterList list;
    @FXML public void initialize(){
        fileNameImage = "default1.png";
        path = getClass().getResource("/images/default1.png").toExternalForm();
//        image.setImage(new Image(getClass().getResource("/ku/cs/images/default1.png").toExternalForm()));
//        image.setImage(new Image(path));
        image.setImage(new Image(path));
        dataSource = new RegisterWriteFile("filescsv","register.csv");
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
            FXRouter.goTo("admin");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า admin.fxml ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }

    public void showStudentListView() {
        studentListView.getItems().addAll(list.getAllCards() );
        studentListView.refresh();
    }

    public void handleSelectedListView() {
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
//        image.setImage(card.getImage());
//        image.setImage(card.setImage());
//        banLabel.setText(card);
//        usernameLabel.setText(String.format("%.2f", card.getCumulativePurchase()));
    }
}
