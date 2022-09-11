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
import ku.cs.models.AdminList;
import ku.cs.models.AdminModels;
import ku.cs.services.AdminReadFile;
import ku.cs.services.DataSource;


import java.io.IOException;
import java.util.*;

public class AdminStudentListController {

    @FXML private Label nameLabel, surnameLabel, usernameLabel, lastloginLabel, banLabel;
    @FXML private ImageView image;
    private DataSource<AdminList> dataSource;
    private AdminList list;


// INITIALIZE IS DOWN HERE // INITIALIZE IS DOWN HERE // INITIALIZE IS DOWN HERE // INITIALIZE IS DOWN HERE // INITIALIZE IS DOWN HERE // INITIALIZE IS DOWN HERE //
    @FXML public void initialize(){
        //READ FILE
        dataSource = new AdminReadFile("filescsv","register.csv");
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
    @FXML private TableView<AdminModels> listTable;
    @FXML private TableColumn<AdminModels, String> listTable_LastLogin;
    @FXML private TableColumn<AdminModels , String> listTable_Name;
    @FXML private TableColumn<AdminModels , String> listTable_Surname;

    private void showStudentListView(AdminList list) {
        //OLD CODE (LISTVIEW)
//        studentListView.getItems().addAll(list.getAllCards());
//        studentListView.refresh();

        // ArrayList >> ObservableList
        ObservableList<AdminModels> TEMP = FXCollections.observableArrayList(
                list.getAllCards()
        );

        listTable_Name.setCellValueFactory(new PropertyValueFactory("name"));
        listTable_Surname.setCellValueFactory(new PropertyValueFactory("surname"));
        listTable_LastLogin.setCellValueFactory(new PropertyValueFactory("stringDateTime"));
        listTable.setItems(TEMP);

        //Anti Tamper code XD // Anti Tamper code XD // Anti Tamper code XD //
        listTable_Name.setReorderable(false);
        listTable_Surname.setReorderable(false);
        listTable_LastLogin.setReorderable(false);
        listTable_Name.setSortable(false);
        listTable_Surname.setSortable(false);
        //Anti Tamper code END // Anti Tamper code END // Anti Tamper code END //

        //Sorter @m@" //Sorter @m@" //Sorter @m@" //Sorter @m@" //
        Collections.sort(TEMP); //Sort small --> big
        Collections.reverse(TEMP);//reverse from "small --> big" --> "big --> small"
//      listTable_LastLogin.setSortType(TableColumn.SortType.DESCENDING);
//      listTable.getSortOrder().add(listTable_LastLogin);
        //Sorter END //Sorter END //Sorter END //Sorter END

        listTable.refresh(); //Fix every unexpected error ^w^b 555

    }

// END TableView ZONE // END TableView ZONE // END TableView ZONE // END TableView ZONE // END TableView ZONE // END TableView ZONE // END TableView ZONE //


    private void handleSelectedListView() {
        //OLD CODE + New Code (LISTVIEW)
        listTable.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<AdminModels>() {
                    @Override
                    public void changed(ObservableValue<? extends AdminModels> observable,
                                        AdminModels oldValue, AdminModels newValue) {
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
    private void showSelectedStudent(AdminModels adminModels) {
        String url = Objects.requireNonNull(getClass().getResource("/images/" + adminModels.getImage())).toExternalForm();
        image.setImage(new Image(url)); //Set image url

        nameLabel.setText(adminModels.getName());
        surnameLabel.setText(adminModels.getSurname());
        usernameLabel.setText(adminModels.getUsername());
        lastloginLabel.setText(adminModels.getTime());
//        image.setImage(card.setImage());
//        banLabel.setText(card);
//        usernameLabel.setText(String.format("%.2f", card.getCumulativePurchase()));
    }
}
