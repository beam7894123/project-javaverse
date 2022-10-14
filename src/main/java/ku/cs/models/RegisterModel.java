package ku.cs.models;

import javafx.fxml.FXML;

import java.util.ArrayList;
import java.util.Date;

public class RegisterModel {
    private String name;
    private String surname;
    private String username;
    private String password;
    private String confirmPassword;
    private String  image;
    private String date;
    private String time;
    private String category;
    private Date dateTime;




    //    public RegisterModel(String name,String surname,String username,String password,String confirmPassword,String date,String time,String fileName){
//        this(name,surname,username,password,date,time);
//        this.image = fileName;
//        System.out.println(image);
//        this.confirmPassword = confirmPassword;
//    }
//    public RegisterModel(String name,String surname,String username,String password,String date,String time,String fileName){
//        this(name,surname,username,password,date,time);
//        this.image = fileName;
//        System.out.println(image);
//    }
    public void initialize(){
        String fileNameImage =  "dafault1.png";
    }
//    public RegisterModel(String name, String surname, String username, String password,String confirmPassword,String date,String time,String fileNameImage) {
//        this.name = name;
//        this.surname = surname;
//        this.username = username;
//        this.confirmPassword =confirmPassword;
//        this.password = password;
//        this.date = date;
//        this.time = time;
//        this.image = fileNameImage;
//        System.out.println(image);
//    }
//    public RegisterModel(String name ,String surname,String time){
//        this.name = name;
//        this.surname = surname;
//        this.time = time;
//    }
    public RegisterModel(String name, String surname, String username, String password,String date,String time,String fileNameImage,String category) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.date = date;
        this.time = time;
        this.category = category;
        this.image = fileNameImage;
        System.out.println(image);
    }
    public RegisterModel(String name, String surname, String username, String password,String date,String time,String fileNameImage) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.date = date;
        this.time = time;
        this.image = fileNameImage;
        System.out.println(image);

    }

    public RegisterModel(String name, String surname, String username, String password, String date, String time) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.date = date;
        this.time = time;
    }




    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public void setTime(String time) {this.time = time;}

    public String getTime() {
        return time;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

//    //FOR Check stringDateTime ONLY
//    Locale locale = new Locale("en","en"); //SET LOCALE (if u sys is พศ. it will auto set to คศ. yay~ \^w^/ )
//    SimpleDateFormat timeFormat1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", locale);
//    private final String stringDateTime = timeFormat1.format(dateTime); //Date --> String
//    public String getStringDateTime() {
//        return stringDateTime;
//    }

    @Override
    public String toString() {
        return "RegisterModel{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", image='" + image + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", dateTime=" + dateTime +
//                ", stringDateTime='" + stringDateTime + '\'' +
                ", category='" + category + '\'' +
                '}';
    }

    //    private String name;
//    private String surname;
//    private String username;
//    private String password;
//    private String confirmPassword;
//    private String image;
//
//    private String date;
//    private String time;
//
//
//
//
//    public RegisterModel(String name,String surname,String username,String password,String confirmPassword,String date, String time ,String fileName){
//        this.name = name;
//        this.surname = surname;
//        this.username = username;
//        this.password = password;
//        this.confirmPassword = confirmPassword;
//        this.date  = date;
//        this.time = time;
//        this.image =  fileName;
//    }
//    public RegisterModel(String name,String surname,String username,String password,String date,String time){
//        this.name = name;
//        this.surname = surname;
//        this.username = username;
//        this.password = password;
//        this.image = image;
////        System.out.println(image);
//        this.date =date;
//        this.time = time;
//    }
//    public RegisterModel(String name,String surname,String username,String password,String date,String time,String fileName){
//        this.name = name;
//        this.surname = surname;
//        this.username = username;
//        this.password = password;
//        this.image = fileName;
//        System.out.println(image);
//        this.date = date;
//        this.time = time;
//    }
//    public RegisterModel(String username,String password){
//        this.username = username;
//        this.password = password;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getSurname() {
//        return surname;
//    }
//    private ArrayList<RegisterModel> registerModelArrayList;
//    public RegisterModel(){
//        registerModelArrayList = new ArrayList<>();
//    }
//
//
//    public String getUsername() {
//        return username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }
//
//
//    public String getDate() {
//        return date;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }
//
//    public String getTime() {
//        return time;
//    }
//
//    public void setTime(String time) {
//        this.time = time;
//    }
//
//    @Override
//    public String toString() {
//        return "RegisterModel{" +
//                "name='" + name + '\'' +
//                ", surname='" + surname + '\'' +
//                ", username='" + username + '\'' +
//                ", password='" + password + '\'' +
//                ", Date ='" + time + '\'' +
//                ", time='" + image + '\'' +
//                '}';
//    }
}
