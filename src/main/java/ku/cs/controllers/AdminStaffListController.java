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
import ku.cs.services.RegisterWriteFile;
import ku.cs.services.SortList;

import java.io.IOException;
import java.util.Collections;
import java.util.Objects;

public class AdminStaffListController {
    @FXML
    private Label nameLabel, surnameLabel, usernameLabel, lastloginLabel, departmentLabel;
    @FXML private ImageView image;
    private RegisterWriteFile readDataforStaff;
    private UserList list;


// INITIALIZE IS DOWN HERE // INITIALIZE IS DOWN HERE // INITIALIZE IS DOWN HERE // INITIALIZE IS DOWN HERE // INITIALIZE IS DOWN HERE // INITIALIZE IS DOWN HERE //
    @FXML public void initialize(){
        //READ FILE
        readDataforStaff = new RegisterWriteFile("filescsv","staff.csv");
        list = readDataforStaff.readDataforStaff();
        //READ FILE END

//        System.out.println(list.getAllCards().get(1)); //Test read
//        System.out.println(list.getAllCards().get(1).getdate() + list.getAllCards().get(1).getTime()); //Test day+time read
//        System.out.println(list.getAllCards().get(1).getDateTime()); //Test datetime read

        showStaffListView(list);
        clearSelectedStaff();
        handleSelectedListView();
    }
// INITIALIZE IS UP HERE // INITIALIZE IS UP HERE // INITIALIZE IS UP HERE // INITIALIZE IS UP HERE // INITIALIZE IS UP HERE // INITIALIZE IS UP HERE //

// TableView ZONE // TableView ZONE // TableView ZONE // TableView ZONE // TableView ZONE // TableView ZONE // TableView ZONE // TableView ZONE //
//    @FXML private ListView<RegisterModel> studentListView; //OLD CODE (LISTVIEW)
    @FXML private TableView<User> listTable;
    @FXML private TableColumn<User, String> listTable_LastLogin;
    @FXML private TableColumn<User , String> listTable_Name;
    @FXML private TableColumn<User , String> listTable_Surname;

    private void showStaffListView(UserList list) {
        //OLD CODE (LISTVIEW)
//        studentListView.getItems().addAll(list.getAllCards());
//        studentListView.refresh();

        // ArrayList >> ObservableList
        ObservableList<User> TEMP = FXCollections.observableArrayList(
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
    private void showSelectedStudent(User user) {
        String url = Objects.requireNonNull(getClass().getResource("/images/" + user.getImage())).toExternalForm();
        image.setImage(new Image(url)); //Set image url

        nameLabel.setText(user.getName());
        surnameLabel.setText(user.getSurname());
        usernameLabel.setText(user.getUsername());
        lastloginLabel.setText(user.getStringDateTime());
        departmentLabel.setText(user.getCategory());
    }
    private void clearSelectedStaff() {
        String url = getClass().getResource("/images/default1.png").toExternalForm();
        image.setImage(new Image(url)); //Set image url

        nameLabel.setText("------");
        surnameLabel.setText("------");
        usernameLabel.setText("------");
        lastloginLabel.setText("------");
        departmentLabel.setText("------");
    }


    @FXML public void handleBackButtonClick(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("admin");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า admin.fxml ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }

}
