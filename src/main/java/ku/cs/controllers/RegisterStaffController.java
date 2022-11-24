package ku.cs.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.MalformedURLException;

import com.github.saacsos.FXRouter;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.models.Staff;
import ku.cs.models.UserList;
import ku.cs.services.RegisterWriteFile;

public class RegisterStaffController {
    @FXML
    TextField usernameTextfield,passwordPasswordfield,confirmPasswordfield,nameTextfield,surnameTextfield;
    @FXML
    Label labelPasswordforStaff, labelUsernameStaff;
    private String fileNameImage;
    private String path;
    @FXML public ImageView image;
    private UserList staffList;
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
        staffList = new UserList("staff.csv");
//        comboBox.getItems().addAll("Person","Facilities","Building",
//                "Learning/Lesson","Traffic/Transport");
        categoryButton.setItems(FXCollections.observableArrayList("Person","Facilities","Building",
                "Learning","Traffic"));
    }

    public void handleOkClick(ActionEvent actionEvent) throws IOException {
        labelUsernameStaff.setText("");
        labelPasswordforStaff.setText("");
        System.out.println(usernameTextfield.getText());
        staffList = writeFile.readDataforStaff();
        if (writeFile.checkUserNameforStaff(staffList,usernameTextfield.getText())){
//            System.out.println(1); //aHHHHHHHHHHHHH USE DEBUG >m<
            labelUsernameStaff.setText("USERNAME IS NOT ALREADY");
        }
        else if (writeFile.checkPassword(passwordPasswordfield.getText(),confirmPasswordfield.getText())) {
            labelPasswordforStaff.setText("PASSWORD IS NOT MATCH");
        }
        else {

//          String category = categoryChicebox.getValue();
          Staff staff1 = new Staff(nameTextfield.getText(),surnameTextfield.getText(),usernameTextfield.getText(),passwordPasswordfield.getText(),fileNameImage,null,null,categoryButton.getValue());
          staffList.addUser(staff1);
          writeFile.writeDataforStaff(staffList);



//            writeFile.checkUsernameAndPasswordforStaff(staffList,usernameTextfield.getText(),passwordPasswordfield.getText(),nameTextfield.getText(),surnameTextfield.getText(),fileNameImage,category);
            try {
                FXRouter.setAnimationType("fade");
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

    public void handleBackButton(ActionEvent actionEvent) {
        try {
          FXRouter.setAnimationType("fade");
          FXRouter.goTo("admin");
        } catch (IOException e) {
            System.err.println("ไปที่หน้าhome ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }


    public void handleUploadButton(ActionEvent actionEvent) throws MalformedURLException {
        fileNameImage = writeFile.uploadImageFromFile(actionEvent,image);
    }


//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        comboBox.setItems(list);
//    }
}
