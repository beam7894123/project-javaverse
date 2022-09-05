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
import ku.cs.models.RegisterList;
import ku.cs.models.RegisterModel;
import ku.cs.services.DataSource;
import ku.cs.services.RegisterWriteFile;


import java.io.IOException;
import java.util.Objects;

public class AdminStudentListController {

    @FXML private Label nameLabel, surnameLabel, usernameLabel, lastloginLabel, banLabel;
    @FXML private ImageView image;
    private DataSource<RegisterList> dataSource;
    private RegisterList list;

// INITIALIZE IS DOWN HERE // INITIALIZE IS DOWN HERE // INITIALIZE IS DOWN HERE // INITIALIZE IS DOWN HERE // INITIALIZE IS DOWN HERE // INITIALIZE IS DOWN HERE //
    @FXML public void initialize(){

        //READ FILE (Borrow RegisterWriteFile)
        dataSource = new RegisterWriteFile("filescsv","register.csv");
        list = dataSource.readData();
        //READ FILE END

        showStudentListView(list);
        clearSelectedStudent();
        handleSelectedListView();
    }
// INITIALIZE IS UP HERE // INITIALIZE IS UP HERE // INITIALIZE IS UP HERE // INITIALIZE IS UP HERE // INITIALIZE IS UP HERE // INITIALIZE IS UP HERE //

    @FXML public void handleBackButtonClick(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("admin");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า admin.fxml ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }
// TableView ZONE // TableView ZONE // TableView ZONE // TableView ZONE // TableView ZONE // TableView ZONE // TableView ZONE // TableView ZONE //
//    @FXML private ListView<RegisterModel> studentListView; //OLD CODE (LISTVIEW)
    @FXML private TableView<RegisterModel> listTable;
    @FXML private TableColumn<RegisterModel , String> listTable_LastLogin;
    @FXML private TableColumn<RegisterModel , String> listTable_Name;
    @FXML private TableColumn<RegisterModel , String> listTable_Surname;

    private void showStudentListView(RegisterList list) {
        //OLD CODE (LISTVIEW)
//        studentListView.getItems().addAll(list.getAllCards());
//        studentListView.refresh();

        // ArrayList >> ObservableList
        ObservableList<RegisterModel> TEMP = FXCollections.observableArrayList(
                list.getAllCards()
        );

        listTable_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        listTable_Surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        listTable_LastLogin.setCellValueFactory(new PropertyValueFactory<>("date"));

        listTable_Name.setReorderable(false);
        listTable_Surname.setReorderable(false);
        listTable_LastLogin.setReorderable(false);
        listTable_Name.setSortable(false);
        listTable_Surname.setSortable(false);

        listTable.setItems(TEMP);

        //Sort
        listTable_LastLogin.setSortType(TableColumn.SortType.DESCENDING);
        listTable.getSortOrder().add(listTable_LastLogin);

        listTable.refresh();

    }

// END TableView ZONE // END TableView ZONE // END TableView ZONE // END TableView ZONE // END TableView ZONE // END TableView ZONE // END TableView ZONE //


    private void handleSelectedListView() {
        //OLD CODE + New Code (LISTVIEW)
        listTable.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<RegisterModel>() {
                    @Override
                    public void changed(ObservableValue<? extends RegisterModel> observable,
                                        RegisterModel oldValue, RegisterModel newValue) {
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
        banLabel.setText("N/A");
    }
    private void showSelectedStudent(RegisterModel registerModel) {
        String url = Objects.requireNonNull(getClass().getResource("/images/" + registerModel.getImage())).toExternalForm();
        image.setImage(new Image(url)); //Set image url

        nameLabel.setText(registerModel.getName());
        surnameLabel.setText(registerModel.getSurname());
        usernameLabel.setText(registerModel.getUsername());
        lastloginLabel.setText(registerModel.getTime());
//        image.setImage(card.setImage());
//        banLabel.setText(card);
//        usernameLabel.setText(String.format("%.2f", card.getCumulativePurchase()));
    }
}
