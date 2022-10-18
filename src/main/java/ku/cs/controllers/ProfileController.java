package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import ku.cs.models.UserList;
import ku.cs.models.User;
import ku.cs.models.ReportList;
import ku.cs.models.ReportModel;
import ku.cs.services.DataSource;
import ku.cs.services.RegisterWriteFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import com.github.saacsos.FXRouter;

import static ku.cs.controllers.SignInController.currentDateTime;

public class ProfileController {
    @FXML private Label allName;
    @FXML private Label username;
    @FXML private Label date;
    @FXML private ImageView imageUpload;
    private DataSource<UserList> dataSource;
    private UserList registerList;
    private User register;

    String usernameText = SignInController.currentUser;
   String loginName;
   String loginSurname;
    private String  fileNameImage;
    private String path;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    String timeReport = currentDateTime.format(formatter);
    private RegisterWriteFile writeFile = new RegisterWriteFile("filescsv","register.csv");


    public void initialize(){
        dataSource = new RegisterWriteFile("filescsv","register.csv");
        registerList = dataSource.readData();
        for (User registerModel : registerList.getAllCards()){
            registerModel.getUsername();
            if(registerModel.getUsername() .equals(usernameText)){
                System.out.println(registerModel.getName());
                System.out.println(registerModel.getSurname());
                System.out.println(registerModel.getImage());
                loginName = registerModel.getName();
                loginSurname = registerModel.getSurname();
                fileNameImage = registerModel.getImage();
            }
        }
        allName.setText(loginName + " " + loginSurname );
        username.setText(usernameText);
        date.setText(timeReport);
        path = getClass().getResource("/images/"+fileNameImage).toExternalForm();
//        image.setImage(new Image(getClass().getResource("/ku/cs/images/default1.png").toExternalForm()));
        imageUpload.setImage(new Image(path));
        registerList = new UserList();
    }

    @FXML
    public void handleBackButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("main");
        } catch (IOException e) {
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    public void handleUploadButton(ActionEvent actionEvent) throws MalformedURLException {

        fileNameImage = writeFile.uploadImageFromFile(actionEvent,imageUpload);
    }
}
