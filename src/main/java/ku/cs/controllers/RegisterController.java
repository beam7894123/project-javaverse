package ku.cs.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.github.saacsos.FXRouter;
import javafx.stage.FileChooser;
import ku.cs.models.RegisterList;
import ku.cs.models.RegisterModel;
import ku.cs.services.DataSource;
import ku.cs.services.RegisterWriteFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

public class RegisterController {
    @FXML
    private Label nameLabel,surnameLabel,usernameLabel,passwordLabel,confirmPasswordLabel,registerLabel,usernameDuplicate;
    public Label labelPassword,labelUsername;
    @FXML
    private TextField nameTextfield,surnameTextfield,usernameTextfield;
    @FXML
    private PasswordField passwordPasswordfield,confirmPasswordfield;
    @FXML
    private ImageView image;
    private DataSource<RegisterList> dataSource;
    private RegisterList registerList;
    private ObservableList<RegisterList> registerListObservableList;
    private RegisterModel registerModel;
    private String filename;
    private String path;


//    String url = getClass().getResource("/images/default1.png").toExternalForm();
    private DataSource write = new RegisterWriteFile("filescsv", "register.csv");
    @FXML public void initialize(){
//        dataSource = new RegisterWriteFile("filescsv","register.csv");
        filename = "register.csv";
        path = getClass().getResource("/images/default1.png").toExternalForm();
        image.setImage(new Image(path));
//        registerList = dataSource.readData();
//        System.out.println(registerList.getAllCards());
//        System.out.println(System.getProperty("user.dir"));
//        image.setImage(new Image(getClass().getResource("/images/default1.png").toExternalForm()));
//        image.setImage(new Image(url));
    }
    public  void setList(RegisterList registerList){
       this.registerList =registerList;
    }
    @FXML
    public void handleOkClick(ActionEvent actionEvent){
        System.out.println(registerList.getAllCards());
        if (checkUsername()){
            labelUsername.setText("USERNAME IS DUPLICATE");
        }
        else if (checkPassword()){
            labelPassword.setText("PASSWORD IS NOT MATCH");
        }
        else{
            RegisterModel registerModel = new RegisterModel(nameTextfield.getText(),surnameTextfield.getText(),usernameTextfield.getText(),passwordPasswordfield.getText(),filename);
//            System.out.println(registerModel);
            registerList.addStudent(registerModel);
            write.writeData(registerList);
            try {
                FXRouter.goTo("main");
            } catch (IOException e) {
                System.err.println("ไปที่หน้าhome ไม่ได้");
                System.err.println("ให้ตรวจสอบการกําหนดroute");
            }
        }

    }
    public Boolean checkUsername(){
        if (usernameTextfield.equals(registerList.getAllCards())){
            return true;
        }
        return false;
    }
    public Boolean checkPassword(){
        if (!(passwordPasswordfield.getText().equals(confirmPasswordfield.getText()))){
            return true;
        }
        return false;
    }
    @FXML
    public void handleBackButton(ActionEvent actionEvent) {try
    {
        FXRouter.goTo("signIn");}
    catch (IOException e) {
        System.err.println("error");}
    }
    @FXML public void handleUploadButton(ActionEvent event){
        FileChooser chooser = new FileChooser();
        // SET FILECHOOSER INITIAL DIRECTORY
        chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        // DEFINE ACCEPTABLE FILE EXTENSION
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("images PNG JPG", "*.png", "*.jpg", "*.jpeg"));
        // GET FILE FROM FILECHOOSER WITH JAVAFX COMPONENT WINDOW
        Node source = (Node) event.getSource();
        File file = chooser.showOpenDialog(source.getScene().getWindow());
        if (file != null){
            try {
                // CREATE FOLDER IF NOT EXIST
                File destDir = new File("images");
                if (!destDir.exists()) destDir.mkdirs();
                // RENAME FILE
                String[] fileSplit = file.getName().split("\\.");
                String filename = LocalDate.now() + "_"+System.currentTimeMillis() + "."
                        + fileSplit[fileSplit.length - 1];
                Path target = FileSystems.getDefault().getPath(
                        destDir.getAbsolutePath()+System.getProperty("file.separator")+filename
                );
                // COPY WITH FLAG REPLACE FILE IF FILE IS EXIST
                Files.copy(file.toPath(), target, StandardCopyOption.REPLACE_EXISTING );
                // SET NEW FILE PATH TO IMAGE
                image.setImage(new Image(target.toUri().toString()));
//                registerModel.setImage(destDir + "/" + filename);
                dataSource.writeData(registerList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
