package ku.cs.services;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import ku.cs.models.UserList;
import ku.cs.models.User;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class RegisterWriteFile implements DataSource<UserList> {
    private String directoryName;
    private String fileName;
    private UserList userList;
    private String path;
    private ImageView image;
    private String fileNameImage;
    private User user;
    private String categoryReceive;
    private UserList staffList;


    public void initialize(){
        fileNameImage = "default1.png";
        path = getClass().getResource("/images/default1.png").toExternalForm();
        image.setImage(new Image(path));
    }

// TIME CONVERTER !!! DO NOT TOUCH !!! ห้ามลบ ถ้าเกิด Error บอก beam7894123 ก่อน ไม่งั้นต่อยนะ >:< //
    Locale locale = new Locale("en","en"); //SET LOCALE (if u sys is พศ. it will auto set to คศ. yay~ \^w^/ )
    SimpleDateFormat timeFormat1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", locale);
    private final Date defaultdateTime; {try {defaultdateTime = timeFormat1.parse("01-01-2000 00:00:00");} catch (
            ParseException e) {throw new RuntimeException(e);}}
    public Date setDateTime(String date, String time) {
        String datePlusTime = date + " " + time;

        try {
            return timeFormat1.parse(datePlusTime);
        } catch (ParseException e) {
            System.err.println("\n!!TIME CONVERTER(v.2) ERROR!!");
            System.err.println("Time, Dr. Freeman?");
//            System.err.println("Look like user date " + "\"" + getName()+ " " + getSurname() + "\"" + " is mess up -w-");
//            System.err.println("Here input is: " + takeDateTime + "");
//          takeDateTime = "00-00-0000 00:00:00";
            return defaultdateTime;
//            System.out.println("Continue running...\n");
//          dateTime = new GregorianCalendar(2000, 2, 1).getTime();
//          throw new RuntimeException(e);
        }
    }
// TIME CONVERTER // END // END // END // END // END // END // END // END // END // END // END // END // END

    public RegisterWriteFile(String directoryName, String fileName) {
      this.directoryName = directoryName;
      this.fileName = fileName;
//      checkFileIsExisted();
//      userList = new UserList();
//      staffList = new UserList();
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
        for (User user : userList.getAllCards()){
            if (user.getUsername().equals(usernameTextField) && passwordPasswordfield.equals(confirmPassword)){
                return true;
            }
        }
        return false;
    }
    public Boolean checkUserName(UserList userList, String usernameTextfield){
        System.out.println(2);
        for (User user : userList.getAllCards()){
            if (user.getUsername().equals(usernameTextfield)){
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
    public Boolean checkUserNameforStaff(UserList staffList,String usernameTextfield){
        System.out.println(22);
        for (User user : staffList.getAllCards()){
            if (user.getUsername().equals(usernameTextfield)){
                return true;
            }
        }
        return false;
    }


    public void checkUserNameAndPassword(UserList userList, String usernameTextfield, String passwordPasswordfield, String nameTextField , String surnameTextField, String fileNameImage, String confirmPasswordpasswordField){
            User user = new User(nameTextField,surnameTextField,usernameTextfield,passwordPasswordfield,null,null,fileNameImage);
            userList.addStudent(user);
            writeData(userList);
        }
    public void checkUsernameAndPasswordforStaff(UserList staffList,String usernameTextfield,String passwordPasswordfield,String nameTextfield,String surnameTextfield,String fileNameImage,String categoryChicebox) throws IOException {
        User user = new User(nameTextfield,surnameTextfield,usernameTextfield,passwordPasswordfield,null,null,fileNameImage,categoryChicebox);
        staffList.addStudent(user);
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
        userList = new UserList();
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            User customer = new User(data[0].trim(),data[1].trim(),data[2].trim(),data[3].trim(),data[4].trim(),data[5].trim());
            customer.setImage(data[4]);
            customer.setDateTime(setDateTime(data[5], data[6])); // TIME CONVERTER !!! DO NOT TOUCH !!! ห้ามลบ ถ้าเกิด Error บอก beam7894123 ก่อน ไม่งั้นต่อยนะ >:< //
//            RegisterModel customer = new RegisterModel(data[0].trim(),data[1].trim(),data[2].trim(),data[3].trim(),data[4].trim(),data[6].trim(),data[7].trim());
//            customer.setImage(data[5]);
//            RegisterModel customer = new RegisterModel(data[0].trim(),data[1].trim(),data[2].trim(),data[3].trim(),data[4].trim(),data[6].trim()); // obj
//            customer.setImage(data[5].trim());
            userList.addStudent(customer);
        }
        reader.close();
    }
    private void readStaff() throws IOException {
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String line = "";
        staffList = new UserList();
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            User customer = new User(data[0].trim(),data[1].trim(),data[2].trim(),data[3].trim(),data[5].trim(),data[6].trim());
            customer.setImage(data[4]);
            customer.setCategory(data[7]);
            customer.setDateTime(setDateTime(data[5], data[6])); // TIME CONVERTER !!! DO NOT TOUCH !!! ห้ามลบ ถ้าเกิด Error บอก beam7894123 ก่อน ไม่งั้นต่อยนะ >:< //
            staffList.addStudent(customer);
        }
        reader.close();
    }


    public UserList Read() {
        try {
            readCustomer();
        } catch (FileNotFoundException e) {
            System.err.println(this.fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
        }
        return userList;
    }

    @Override
    public UserList readData() {
        try {
            readCustomer();
        } catch (FileNotFoundException e) {
            System.err.println(this.fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
        }
        return userList;
    }
    public UserList readDataforStaff(){
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
    public void writeData(UserList userList) {

        String filePath = directoryName+File.separator+fileName;
        File file = new File(filePath);
        FileWriter writer = null;
        BufferedWriter buffer = null;
        try {
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);
            for (User user : userList.getAllCards()){
                System.out.println(userList);
                String line = user.getName()+","
                        + user.getSurname()+","
                        + user.getUsername()+","
                        + user.getPassword()+","
                        + user.getImage()+","
                        + user.getDate()+","
                        + user.getTime();
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
    public void writeDataforStaff(UserList staffList) throws IOException {
        System.out.println(staffList.toString());
        String filePath = directoryName+File.separator+fileName;
        File file = new File(filePath);
        FileWriter writer = null;
        BufferedWriter buffer = null;
        try {
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);
            for (User user : staffList.getAllCards()){
                System.out.println(staffList);
                String line = user.getName()+","
                        + user.getSurname()+","
                        + user.getUsername()+","
                        + user.getPassword()+","
                        + user.getImage()+","
                        + user.getDate()+","
                        + user.getTime()+","
                        + user.getCategory();
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
                fileNameImage = fileName;
                Path target = FileSystems.getDefault().getPath(
                        destDir.getAbsolutePath()+System.getProperty("file.separator")+fileNameImage
                );
                // COPY WITH FLAG REPLACE FILE IF FILE IS EXIST
                Files.copy(file.toPath(), target, StandardCopyOption.REPLACE_EXISTING );
                // SET NEW FILE PATH TO IMAGE
                image.setImage(new Image(target.toUri().toString()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return fileName;
    }

}
