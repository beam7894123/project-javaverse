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
import ku.cs.models.Staff;
import ku.cs.models.StaffList;

import ku.cs.services.RegisterWriteFile;
import ku.cs.services.SortList;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Objects;

public class AdminStaffListController {
    @FXML
    private Label nameLabel, surnameLabel, usernameLabel, lastloginLabel, departmentLabel;
    @FXML private ImageView image;
    private RegisterWriteFile readDataforStaff;
    private StaffList list;


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
    @FXML private TableView<Staff> listTable;
    @FXML private TableColumn<Staff, String> listTable_LastLogin;
    @FXML private TableColumn<Staff , String> listTable_Name;
    @FXML private TableColumn<Staff , String> listTable_Surname;

    private void showStaffListView(StaffList list) {
        //OLD CODE (LISTVIEW)
//        studentListView.getItems().addAll(list.getAllCards());
//        studentListView.refresh();

        // ArrayList >> ObservableList
        ObservableList<Staff> TEMP = FXCollections.observableArrayList(
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
                new ChangeListener<Staff>() {
                    @Override
                    public void changed(ObservableValue<? extends Staff> observable,
                                        Staff oldValue, Staff newValue) {
                        System.out.println(newValue + " is selected");
                        showSelectedStudent(newValue);
                    }
                });
    }
    private void showSelectedStudent(Staff staff) {
        File imageFile = new File("src/main/resources/images");
//        String url = Objects.requireNonNull(imageFile.getAbsolutePath()+"/"+user.getImage());
        image.setImage(new Image(imageFile.getAbsolutePath()+"/"+staff.getImage()));
//        String url = Objects.requireNonNull(getClass().getResource("/images/" + user.getImage())).toExternalForm();
//        image.setImage(new Image(url)); //Set image url

        nameLabel.setText(staff.getName());
        surnameLabel.setText(staff.getSurname());
        usernameLabel.setText(staff.getUsername());
        lastloginLabel.setText(staff.getStringDateTime());
        departmentLabel.setText(staff.getCategory());
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
