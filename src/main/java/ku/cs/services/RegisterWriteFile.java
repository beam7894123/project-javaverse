package ku.cs.services;
import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import ku.cs.models.Category;
import ku.cs.models.RegisterList;
import ku.cs.models.RegisterModel;
import ku.cs.models.StaffList;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import static ku.cs.controllers.SignInController.strUsername;

public class RegisterWriteFile implements DataSource<RegisterList> {
    private String directoryName;
    private String fileName;
    private RegisterList registerList;
    private String path;
    private ImageView image;
    private String fileNameImage;
    private RegisterModel registerModel;
    private String categoryReceive;
    private StaffList staffList;


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
      staffList = new StaffList();
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

    public Boolean checkUsernameAndpassword(String usernameTextField,String passwordPasswordfield,String confirmPassword){
        for (RegisterModel registerModel: registerList.getAllCards()){
            if (registerModel.getUsername().equals(usernameTextField) && passwordPasswordfield.equals(confirmPassword)){
                return true;
            }
        }
        return false;
    }
    public Boolean checkUserName(RegisterList registerList,String usernameTextfield){
        System.out.println(2);
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
    public Boolean checkUserNameforStaff(StaffList staffList,String usernameTextfield){
        System.out.println(22);
        for (RegisterModel registerModel : staffList.getAllstaff()){
            if (registerModel.getUsername().equals(usernameTextfield)){
                return true;
            }
        }
        return false;
    }
//    public Boolean checkPasswordforStaff(String passwordPasswordfield,String confirmPassword){
//        if (!passwordPasswordfield.equals(confirmPassword)){
//            return true;
//        }
//        return false;
//    }

    public void checkUserNameAndPassword(RegisterList registerList,String usernameTextfield,String passwordPasswordfield,String nameTextField ,String surnameTextField,String fileNameImage,String confirmPasswordpasswordField){
            RegisterModel registerModel = new RegisterModel(nameTextField,surnameTextField,usernameTextfield,passwordPasswordfield,null,null,fileNameImage);
            registerList.addStudent(registerModel);
            writeData(registerList);
        }
    public void checkUsernameAndPasswordforStaff(StaffList staffList,String usernameTextfield,String passwordPasswordfield,String nameTextfield,String surnameTextfield,String fileNameImage,String categoryChicebox) throws IOException {
        RegisterModel registerModel = new RegisterModel(nameTextfield,surnameTextfield,usernameTextfield,passwordPasswordfield,null,null,fileNameImage,categoryChicebox);
        staffList.addStaff(registerModel);
        writeDataforStaff(staffList);
//        System.out.println(categoryChicebox);
//
//        RegisterModel staffModel = new RegisterModel(nameTextfield,surnameTextfield,usernameTextfield,passwordPasswordfield,null,null,fileNameImage,categoryChicebox);
//        System.out.println(1);
//        System.out.println(staffList);
//        staffList.addStaff(staffModel);
//        System.out.println("after");
//        System.out.println(staffList);
//        System.out.println(2);
////        writeDataforStaff(nameTextField,surnameTextField,usernameTextfield,passwordPasswordfield,null,null,fileNameImage,category);
//        System.out.println("before WRITE");
//        System.out.println(staffList.toString());
//        writeDataforStaff(staffList);
//        System.out.println(3);
//        if (checkUserName(registerList,usernameTextfield) && checkPassword(passwordPasswordfield,confirmPasswordpasswordField)){
//
//            writeDataforStaff(registerList,usernameTextfield,)
//        }
//        RegisterModel registerModel = new RegisterModel(nameTextField,surnameTextField,usernameTextfield,passwordPasswordfield,null,null,fileNameImage);
//        registerList.
//        registerList.addStudent(registerModel);
//        writeData(registerList);
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
//            RegisterModel customer = new RegisterModel(data[0].trim(),data[1].trim(),data[2].trim(),data[3].trim(),data[4].trim(),data[6].trim(),data[7].trim());
//            customer.setImage(data[5]);
//            RegisterModel customer = new RegisterModel(data[0].trim(),data[1].trim(),data[2].trim(),data[3].trim(),data[4].trim(),data[6].trim()); // obj
//            customer.setImage(data[5].trim());
            registerList.addStudent(customer);
        }
        reader.close();
    }
    private void readStaff() throws IOException {
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String line = "";
        staffList = new StaffList();
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            RegisterModel customer = new RegisterModel(data[0].trim(),data[1].trim(),data[2].trim(),data[3].trim(),data[5].trim(),data[6].trim());
            customer.setImage(data[4]);
            customer.setCategory(data[7]);
            staffList.addStaff(customer);
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
    public StaffList readDataforStaff(){
        try {
            readStaff();
        } catch (FileNotFoundException e) {
            System.err.println(this.fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
        }
        return staffList;
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
                System.out.println(registerList);
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
    public void writeDataforStaff(StaffList staffList) throws IOException {
        System.out.println(staffList.toString());
        String filePath = directoryName+File.separator+fileName;
        File file = new File(filePath);
        FileWriter writer = null;
        BufferedWriter buffer = null;
        try {
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);
            for (RegisterModel registerModel : staffList.getAllstaff()){
                System.out.println(staffList);
                String line = registerModel.getName()+","
                        +registerModel.getSurname()+","
                        +registerModel.getUsername()+","
                        +registerModel.getPassword()+","
                        +registerModel.getImage()+","
                        +registerModel.getDate()+","
                        +registerModel.getTime()+","
                        +registerModel.getCategory();
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
    public void writeDataforStaff1(String nameTextfield,String surnameTextfield,String usernameTextfield,String passwordPasswordField,String date,String time,String category,String fileNameImage ) {
        System.out.println(category);
        File file = new File("filescsv","staff.csv");
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                PrintWriter writer = null;
                try {
                    FileWriter fw = new FileWriter(file,true);
                    writer=new PrintWriter(fw);
//                while (Str_Username.equals(Data_Username)&& Str_Password.equals(Data_Password))
//                Data_Username=data[1];
//                Data_Password=data[2];
                    if(usernameTextfield.equals(data[2]) && passwordPasswordField.equals(data[3])){
                        writer.println(data[0] + "," + data[1] + "," + data[2] + "," +data[3]+","+ data[4]+","+data[5]+","+data[6]);
                    }else{
                        writer.println(data[0] + "," + data[1] + "," + data[2] + "," + data[3]
                                + "," + data[4] + "," + data[5] + "," + data[6]);
                    }

                } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

                writer.close();
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        File file = new File("filescsv","staff.csv");
        PrintWriter writer = null;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(nameTextfield).append(",")
            .append(category).append("\n");
            FileWriter fileWriter = new FileWriter("filescsv/staff.csv", true);
            fileWriter.write(sb.toString());
            fileWriter.close();
        } catch (IOException e) {
            System.err.println();
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
