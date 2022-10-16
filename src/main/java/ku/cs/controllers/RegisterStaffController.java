package ku.cs.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import com.github.saacsos.FXRouter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.models.RegisterList;
import ku.cs.models.RegisterModel;
import ku.cs.models.StaffList;
import ku.cs.services.RegisterWriteFile;

public class RegisterStaffController extends RegisterController{
    @FXML
    TextField usernameTextfield,passwordPasswordfield,confirmPasswordfield,nameTextfield,surnameTextfield;
    @FXML
    Label labelUsername,labelPassword,labelUsernameforStaff,labelPasswordforStaff;
    @FXML Label labelUsernameStaff;
    private String fileNameImage;
    private RegisterList registerList;
    private String path;
    @FXML public ImageView image;
    private RegisterModel registerModel;
    private StaffList staffList;
    @FXML ChoiceBox<String> categoryButton;
//    @FXML
////    ChoiceBox<String> categoryChicebox;
////    ComboBox<String> comboBox;
////    ObservableList<String> list =FXCollections.observableArrayList("Person","Facilities","Building",
//            "Learning/Lesson","Traffic/Transport");
    private RegisterWriteFile writeFile = new RegisterWriteFile("filescsv","staff.csv");

    public void initialize(){
        fileNameImage = "default1.png";
        path = getClass().getResource("/images/default1.png").toExternalForm();
//        image.setImage(new Image(getClass().getResource("/ku/cs/images/default1.png").toExternalForm()));
        image.setImage(new Image(path));
        staffList = new StaffList();
//        comboBox.getItems().addAll("Person","Facilities","Building",
//                "Learning/Lesson","Traffic/Transport");
        categoryButton.setItems(FXCollections.observableArrayList("Person","Facilities","Building",
                "Learning/Lesson","Traffic/Transport"));
    }
    @Override
    public void handleOkClick(ActionEvent actionEvent) throws IOException {
        System.out.println(usernameTextfield.getText());
        staffList = writeFile.readDataforStaff();
        if (writeFile.checkUserNameforStaff(staffList,usernameTextfield.getText())){
            System.out.println(1);
            labelUsernameStaff.setText("USERNAME IS NOT ALREADY");
        }
        else if (writeFile.checkPassword(passwordPasswordfield.getText(),confirmPasswordfield.getText())) {
            labelPasswordforStaff.setText("PASSWORD IS NOT MATCH");
        }
        else {

//          String category = categoryChicebox.getValue();
          RegisterModel registerModel1 = new RegisterModel(nameTextfield.getText(),surnameTextfield.getText(),usernameTextfield.getText(),passwordPasswordfield.getText(),null,null,fileNameImage,categoryButton.getValue());
          staffList.addStaff(registerModel1);
          writeFile.writeDataforStaff(staffList);



//            writeFile.checkUsernameAndPasswordforStaff(staffList,usernameTextfield.getText(),passwordPasswordfield.getText(),nameTextfield.getText(),surnameTextfield.getText(),fileNameImage,category);
            try {
                FXRouter.goTo("admin");
            } catch (IOException e) {
                System.err.println("ไปที่หน้าhome ไม่ได้");
                System.err.println("ให้ตรวจสอบการกําหนดroute");
            }
        }
    }
    public String getValue(ChoiceBox<String> choiceBox){
        String value = choiceBox.getValue();
        return value;
    }
//    public Boolean checkUsername(){
//        for (RegisterModel registerModel1: staffList.getAllstaff()){
//            if (registerModel1.getUsername().equals(usernameTextfield)){
//                return true;
//            }
//        }
//        return false;
//    }
    @Override
    public void handleBackButton(ActionEvent actionEvent) {
        try {
          FXRouter.goTo("admin");
        } catch (IOException e) {
            System.err.println("ไปที่หน้าhome ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }

    @Override
    public void handleUploadButton(ActionEvent actionEvent) throws MalformedURLException {
        fileNameImage = writeFile.uploadImageFromFile(actionEvent,image);
    }


//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        comboBox.setItems(list);
//    }
}
