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
import ku.cs.services.RegisterWriteFile;
import ku.cs.services.SortList;
import ku.cs.services.DataSource;


import java.io.IOException;
import java.util.*;

public class AdminStudentListController {
    @FXML private Label nameLabel, surnameLabel, usernameLabel, lastloginLabel, banLabel;
    @FXML private ImageView image;
    private DataSource<RegisterList> dataSource;
    private RegisterList list;

// TableView ZONE // TableView ZONE // TableView ZONE // TableView ZONE // TableView ZONE // TableView ZONE // TableView ZONE // TableView ZONE //
//    @FXML private ListView<RegisterModel> studentListView; //OLD CODE (LISTVIEW)
    @FXML private TableView<RegisterModel> listTable;
    @FXML private TableColumn<RegisterModel, String> listTable_LastLogin;
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

        listTable_Name.setCellValueFactory(new PropertyValueFactory("name"));
        listTable_Surname.setCellValueFactory(new PropertyValueFactory("surname"));
        listTable_LastLogin.setCellValueFactory(new PropertyValueFactory("StringDateTime"));
        listTable.setItems(TEMP);

        //Anti Tamper code XD // Anti Tamper code XD // Anti Tamper code XD //
        listTable_Name.setReorderable(false);
        listTable_Surname.setReorderable(false);
        listTable_LastLogin.setReorderable(false);
        listTable_Name.setSortable(false);
        listTable_Surname.setSortable(false);
        //Anti Tamper code END // Anti Tamper code END // Anti Tamper code END //

        //Sorter @m@" //Sorter @m@" //Sorter @m@" //Sorter @m@" //
        Collections.sort(TEMP, SortList.ascendingDateTime());
//      listTable_LastLogin.setSortType(TableColumn.SortType.DESCENDING);
//      listTable.getSortOrder().add(listTable_LastLogin);
        //Sorter END //Sorter END //Sorter END //Sorter END

        listTable.refresh(); //Fix every unexpected error ^w^b 555

    }

// END TableView ZONE // END TableView ZONE // END TableView ZONE // END TableView ZONE // END TableView ZONE // END TableView ZONE // END TableView ZONE //

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
    private void showSelectedStudent(RegisterModel registerModels) {
        String url = Objects.requireNonNull(getClass().getResource("/images/" + registerModels.getImage())).toExternalForm();
        image.setImage(new Image(url)); //Set image url

        nameLabel.setText(registerModels.getName());
        surnameLabel.setText(registerModels.getSurname());
        usernameLabel.setText(registerModels.getUsername());
        lastloginLabel.setText(registerModels.getTime());
//        image.setImage(card.setImage());
//        banLabel.setText(card);
//        usernameLabel.setText(String.format("%.2f", card.getCumulativePurchase()));
    }


// INITIALIZE IS DOWN HERE // INITIALIZE IS DOWN HERE // INITIALIZE IS DOWN HERE // INITIALIZE IS DOWN HERE // INITIALIZE IS DOWN HERE // INITIALIZE IS DOWN HERE //
    @FXML public void initialize(){
        //READ FILE
        dataSource = new RegisterWriteFile("filescsv","register.csv");
        list = dataSource.readData();
        //READ FILE END

//        System.out.println(list.getAllCards().get(1)); //Test read
//        System.out.println(list.getAllCards().get(1).getdate() + list.getAllCards().get(1).getTime()); //Test day+time read
//        System.out.println(list.getAllCards().get(1).getDateTime()); //Test datetime read

        showStudentListView(list);
        clearSelectedStudent();
        handleSelectedListView();
    }
// INITIALIZE IS UP HERE // INITIALIZE IS UP HERE // INITIALIZE IS UP HERE // INITIALIZE IS UP HERE // INITIALIZE IS UP HERE // INITIALIZE IS UP HERE //
}
