package ku.cs.services;
import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import ku.cs.models.RegisterList;
import ku.cs.models.RegisterModel;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

public class RegisterWriteFile implements DataSource<RegisterList> {
    private String directoryName;
    private String fileName;
    private RegisterList registerList;
    private String path;
    private ImageView image;
    private String fileNameImage;


    public void initialize(){
        fileNameImage = "default1.png";
        path = getClass().getResource("/images/default1.png").toExternalForm();
        image.setImage(new Image(path));
    }

    public RegisterWriteFile(String directoryName, String fileName) {
      this.directoryName = directoryName;
      this.fileName = fileName;
//      checkFileIsExisted();
      registerList = new RegisterList();
    }
//    private void checkFileIsExisted(){
//        // ถูกเรียกตอน constructor
//        File file = new File(directoryName);
//        if (!file.exists()){// exist ใช้บอกว่าเช็คว่ามีไฟลื
//            file.mkdirs(); // mkdir คือจะสร้าง folder เดียวไม่สร้าง parent ให้ เเต่ถ้าเป็น mkdires มันจะสร้างให้
//        }
//        String filePath = directoryName+fileName;
//        file = new File(filePath);
//        if (!file.exists()){
//            try { // เราจัดการเองเลย
//                file.createNewFile(); // ถ้าไม่มีให้สร้างไฟล์ใหม่
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
    public Boolean checkUserName(RegisterList registerList,String usernameTextfield){
        for (RegisterModel registerModel: registerList.getAllCards()){
            if (registerModel.getUsername().equals(usernameTextfield)){
                return true;
            }
        }
        return false;
    }
    public Boolean checkPassword(String passwordPasswordfield,String confirmPassword){
        if (!passwordPasswordfield.equals(confirmPassword)){
            return true;
        }
        return false;
    }
    public void checkUserNameAndPassword(RegisterList registerList,String usernameTextfield,Label labelUsername,Label labelPassword,String passwordPasswordfield,String confirmPassword,String nameTextField ,String surnameTextField,String fileNameImage){
        if (checkUserName(registerList, usernameTextfield)){
            labelUsername.setText("USERNAME IS NOT ALREADY");
        }
        else if (checkPassword(passwordPasswordfield,confirmPassword)){
            labelPassword.setText("PASSWORD IS NOT MATCH");
        }
        else{
            RegisterModel registerModel = new RegisterModel(nameTextField,surnameTextField,usernameTextfield,passwordPasswordfield,null,null,fileNameImage);
            registerList.addStudent(registerModel);
            writeData(registerList);
            try {
                FXRouter.goTo("main");
            } catch (IOException e) {
                System.err.println("ไปที่หน้าhome ไม่ได้");
                System.err.println("ให้ตรวจสอบการกําหนดroute");
            }
        }
    }
    private void readCustomer() throws IOException {
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String line = "";
        registerList = new RegisterList();
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            RegisterModel customer = new RegisterModel(data[0].trim(),data[1].trim(),data[2].trim(),data[3].trim(),data[5].trim(),data[6].trim());
            customer.setImage(data[4]);
//            RegisterModel customer = new RegisterModel(data[0].trim(),data[1].trim(),data[2].trim(),data[3].trim(),data[4].trim(),data[5].trim()); // obj
//            customer.setImage(data[6].trim());
            registerList.addStudent(customer);
        }
        reader.close();
    }


    public RegisterList Read() {
        try {
            readCustomer();
        } catch (FileNotFoundException e) {
            System.err.println(this.fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
        }
        return registerList;
    }

    @Override
    public RegisterList readData() {
        try {
            readCustomer();
        } catch (FileNotFoundException e) {
            System.err.println(this.fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
        }
        return registerList;
    }

    @Override
    public void writeData(RegisterList registerList) {
        String filePath = directoryName+File.separator+fileName;
        File file = new File(filePath);
        FileWriter writer = null;
        BufferedWriter buffer = null;
        try {
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);
            for (RegisterModel registerModel : registerList.getAllCards()){
                String line = registerModel.getName()+","
                        +registerModel.getSurname()+","
                        +registerModel.getUsername()+","
                        +registerModel.getPassword()+","
                        +registerModel.getImage()+","
                        +registerModel.getDate()+","
                        +registerModel.getTime();
                buffer.append(line);
                buffer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                buffer.close();
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public String uploadImageFromFile(ActionEvent event,ImageView image) throws MalformedURLException {
        FileChooser chooser = new FileChooser();
        // SET FILECHOOSER INITIAL DIRECTORY
        chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        // DEFINE ACCEPTABLE FILE EXTENSION
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("images PNG JPG", "*.png", "*.jpg", "*.jpeg"));
        // GET FILE FROM FILECHOOSER WITH JAVAFX COMPONENT WINDOW
        Node source = (Node) event.getSource();
        File file = chooser.showOpenDialog(source.getScene().getWindow());
        String fileName = "";
        if (file != null){
            fileName = file.getName();
            try {
                // CREATE FOLDER IF NOT EXIST
                File destDir = new File("src/main/resources/images");
                if (!destDir.exists()) destDir.mkdirs();
                // RENAME FILE
                String[] fileSplit = file.getName().split("\\.");
                fileNameImage = LocalDate.now() + "_"+System.currentTimeMillis() + "."
                        + fileSplit[fileSplit.length - 1];
                Path target = FileSystems.getDefault().getPath(
                        destDir.getAbsolutePath()+System.getProperty("file.separator")+fileNameImage
                );
                // COPY WITH FLAG REPLACE FILE IF FILE IS EXIST
                Files.copy(file.toPath(), target, StandardCopyOption.REPLACE_EXISTING );
//                File destDir = new File("picture");
//                if (!destDir.exists()) destDir.mkdirs();
//                // RENAME FILE
//                String[] fileSplit = file.getName().split("\\.");
//                String filename = LocalDate.now() + "_" + System.currentTimeMillis() + "."
//                        + fileSplit[fileSplit.length - 1];
//                Path target = FileSystems.getDefault().getPath(
//                        destDir.getPath() + System.getProperty("file.separator") + target2
//                );
//                Path temp = Files.move(Paths.get(String.valueOf(target)), Paths.get(String.valueOf(target2)));
                // COPY WITH FLAG REPLACE FILE IF FILE IS EXIST
//                Files.copy(file.toPath(), target2, StandardCopyOption.REPLACE_EXISTING);
                // SET NEW FILE PATH TO IMAGE
                image.setImage(new Image(target.toUri().toString()));
//                purchase.setImagePath(destDir + "/" + filename);
//                write.writeData(control);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return fileName;
    }

}
