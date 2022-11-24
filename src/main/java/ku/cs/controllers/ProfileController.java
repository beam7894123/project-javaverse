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

    String UsernameFromSignin = (String) FXRouter.getData();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    String timeReport = currentDateTime.format(formatter);
    private RegisterWriteFile writeFile = new RegisterWriteFile("filescsv","student.csv");


    public void initialize(){
        dataSource = new RegisterWriteFile("filescsv","student.csv");
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
        File imageFile = new File("src/main/resources/images");
//        String url = Objects.requireNonNull(imageFile.getAbsolutePath()+"/"+user.getImage());
        imageUpload.setImage(new Image(imageFile.getAbsolutePath()+"/"+fileNameImage));
//        path = getClass().getResource("/images/"+fileNameImage).toExternalForm();
//        imageUpload.setImage(new Image(path));
    }

    @FXML
    public void handleBackButton(ActionEvent actionEvent) {
        try {
            FXRouter.setAnimationType("fade");
            FXRouter.goTo("main");
        } catch (IOException e) {
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    // polymor ?
    public void handleUploadButton(ActionEvent actionEvent) throws MalformedURLException {
        fileNameImage = writeFile.uploadImageFromFile(actionEvent,imageUpload);
        for (User registerModel : registerList.getAllCards()){
            registerModel.getUsername();
            if(registerModel.getUsername().equals(usernameText)) {
                registerModel.setImage(fileNameImage);
            }
        }
        System.out.println(fileNameImage);
        dataSource.writeData(registerList);
//        registerList = new UserList();
//        registerList

    }
//    @FXML public void handleUploadButton(ActionEvent event){
//        FileChooser chooser = new FileChooser();
//        chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
//        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("images PNG JPG", "*.png", "*.jpg", "*.jpeg"));
//        Node source = (Node) event.getSource();
//        File file = chooser.showOpenDialog(source.getScene().getWindow());
//        if (file != null){
//            try {
//                File destDir = new File("images");
//                if (!destDir.exists()) destDir.mkdirs();
//                String[] fileSplit = file.getName().split("\\.");
//                String filename = LocalDate.now() + "_"+System.currentTimeMillis() + "."
//                        + fileSplit[fileSplit.length - 1];
//                Path target = FileSystems.getDefault().getPath(
//                        destDir.getAbsolutePath()+System.getProperty("file.separator")+filename
//                );
//                Files.copy(file.toPath(), target, StandardCopyOption.REPLACE_EXISTING );
//
//                imageUpload.setImage(new Image(target.toUri().toString()));
//                this.path = destDir + "/" + filename;
//                System.out.println(this.path);
//
//                if (path == null){
//                    path = "images/default1.png";
//                }
//
//                int count = 0;
//                for (User userData : registerList.getAllCards()) {
//                    if (UsernameFromSignin.equals(userData.getUsername())) {
//                        userData.setImage(path);
//                        registerList.getAllCards().set(count, userData);
//                        dataSource.writeData(registerList);
//                    }
//                    count = count + 1;
//                }
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//    }
    @FXML
    public void handleReportButton(ActionEvent actionEvent) {
        try {
            FXRouter.setAnimationType("fade");
            FXRouter.goTo("myreport");
        } catch (IOException e) {
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
