package ku.cs.models;

import java.util.Date;

public class User {
    private String name;
    private String surname;
    private String username;
    private String password;

//    private String confirmPassword; // <-- This is never use so why it here -w-
    private String  image;
    private String date;
    private String time;

    public void initialize(){
        String fileNameImage =  "dafault1.png";
    }

    public User(String name, String surname, String username, String password,String fileNameImage, String date, String time) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.image = fileNameImage;
        this.date = date;
        this.time = time;
//        System.out.println(image); //debug

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

// TIME CONVERTER !!! DO NOT TOUCH !!! ห้ามลบ ถ้าเกิด Error บอก beam7894123 ก่อน ไม่งั้นต่อยนะ >:< //
    private Date dateTime;
    public Date getDateTime() {
        return dateTime;
    }
    //    private String StringDateTime = date + " " + time;
    public String getStringDateTime() { //use by tableview in AdminStudentListController
        return date + " " + time;
    }
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
// TIME CONVERTER // END // END // END // END // END // END // END // END // END // END // END // END // END


    @Override
    public String toString() {
        return "RegisterModel{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", image='" + image + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", dateTime=" + dateTime + // นี้ด้วย <<
                '}';
    }


}
