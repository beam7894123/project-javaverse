package ku.cs.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import com.github.saacsos.FXRouter;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ku.cs.models.RegisterList;
import ku.cs.models.RegisterModel;
import ku.cs.models.RegistersimpleStringproperty;
import ku.cs.services.StaffHardCodeDataSource;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class AdminStaffListController implements Initializable {
    @FXML
    public TableView<RegistersimpleStringproperty> listStaff; //
    public TableColumn<RegistersimpleStringproperty,String> nameStaff;
    @FXML private Label nameLabel;
    @FXML private Label surnameLabel;
    @FXML private Label usernameLabel;
    @FXML private Label lastloginLabel;
    @FXML private Label departmentLabel;
//    'private StaffHardCodeDataSource dataSource;
    private RegistersimpleStringproperty registersimpleStringproperty;
    public void readData(RegistersimpleStringproperty tableView){
        StaffHardCodeDataSource dataSource = new StaffHardCodeDataSource();
        dataSource.readData(tableView);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameStaff.setCellValueFactory(new PropertyValueFactory<>("nameStaff"));
//        listStaff.setItems(list);
        try {
            readData(registersimpleStringproperty);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ObservableList<RegistersimpleStringproperty> observableList = (ObservableList<RegistersimpleStringproperty>) FXCollections.observableArrayList(
                new RegistersimpleStringproperty("A")
        );
    }
    public  void showSelectedRegister(RegisterModel registerModel) {
        nameLabel.setText(registerModel.getName());
        surnameLabel.setText(registerModel.getSurname());
        usernameLabel.setText(registerModel.getUsername());
        lastloginLabel.setText(registerModel.getTime());
    }
    public void clearSelectedRegister(){
        nameLabel.setText("");
        surnameLabel.setText("");
        usernameLabel.setText("");
        lastloginLabel.setText("");
        departmentLabel.setText("N/A");
    }
   @FXML public void handleBackButtonClick() {
       try {
           FXRouter.goTo("admin");
       } catch (IOException e) {
           System.err.println("ไปที่หน้าregister ไม่ได้");
           System.err.println("ให้ตรวจสอบการกําหนดroute");
       }
   }


}
