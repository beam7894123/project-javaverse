package ku.cs.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.models.User;
import ku.cs.models.UserList;
import ku.cs.services.AscendingDateTimeComparator;
import ku.cs.services.RegisterWriteFile;
import ku.cs.services.SortList;
import ku.cs.services.DataSource;


import java.io.File;
import java.io.IOException;
import java.util.*;

public class AdminStudentListController {
    @FXML private Label nameLabel, surnameLabel, usernameLabel, lastloginLabel;
    @FXML private ImageView image;
    private DataSource<UserList> dataSource;
    private UserList list;

    @FXML private TableView<User> listTable;
    @FXML private TableColumn<User, String> listTable_LastLogin;
    @FXML private TableColumn<User , String> listTable_Name;
    @FXML private TableColumn<User , String> listTable_Surname;

    private void showStudentListView(UserList list) {
        ObservableList<User> TEMP = FXCollections.observableArrayList(
                list.getAllCards()
        );

        listTable_Name.setCellValueFactory(new PropertyValueFactory("name"));
        listTable_Surname.setCellValueFactory(new PropertyValueFactory("surname"));
        listTable_LastLogin.setCellValueFactory(new PropertyValueFactory("StringDateTime"));
        listTable.setItems(TEMP);
        listTable_Name.setReorderable(false);
        listTable_Surname.setReorderable(false);
        listTable_LastLogin.setReorderable(false);
        listTable_Name.setSortable(false);
        listTable_Surname.setSortable(false);

        Collections.sort(TEMP, new AscendingDateTimeComparator()); // polymor


        listTable.refresh(); //Fix every unexpected error ^w^b 555

    }

    @FXML public void handleBackButtonClick(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("admin");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า admin.fxml ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }
    private void handleSelectedListView() {
        //OLD CODE + New Code (LISTVIEW)
        listTable.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<User>() {
                    @Override
                    public void changed(ObservableValue<? extends User> observable,
                                        User oldValue, User newValue) {
                        System.out.println(newValue + " is selected");
                        showSelectedStudent(newValue);
                    }
                });
    }

    private void clearSelectedStudent() {
        String url = getClass().getResource("/images/default1.png").toExternalForm();
        image.setImage(new Image(url)); //Set image url

        nameLabel.setText("------");
        surnameLabel.setText("------");
        usernameLabel.setText("------");
        lastloginLabel.setText("------");
    }
    private void showSelectedStudent(User user) {
        File imageFile = new File("src/main/resources/images");
//        String url = Objects.requireNonNull(imageFile.getAbsolutePath()+"/"+user.getImage());
        image.setImage(new Image(imageFile.getAbsolutePath()+"/"+user.getImage())); //Set image url

        nameLabel.setText(user.getName());
        surnameLabel.setText(user.getSurname());
        usernameLabel.setText(user.getUsername());
        lastloginLabel.setText(user.getStringDateTime());
    }


// INITIALIZE IS DOWN HERE // INITIALIZE IS DOWN HERE // INITIALIZE IS DOWN HERE // INITIALIZE IS DOWN HERE // INITIALIZE IS DOWN HERE // INITIALIZE IS DOWN HERE //
    @FXML public void initialize(){
        dataSource = new RegisterWriteFile("filescsv","student.csv");
        list = dataSource.readData();

        showStudentListView(list);
        clearSelectedStudent();
        handleSelectedListView();
    }
}
