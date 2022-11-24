package ku.cs.models;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ku.cs.services.LoginStaffWriteFile;
import ku.cs.services.RegisterWriteFile;

import java.util.ArrayList;

public class UserList {
    @FXML TextField usernameTextField;
    @FXML PasswordField passwordPasswodfield;
    private RegisterWriteFile registerWriteFile;
    private LoginStaffWriteFile registerWriteFileforStaff;
    private ArrayList<User> userArrayList;
    public UserList(String filename) {
        // ใช้new เพื'อสร้างinstance ของArrayList
        this.userArrayList = new ArrayList<>(); // dynamic type มันสามารถเปลี่ยนเเปลงได้
//        registerWriteFile =

        registerWriteFile = new RegisterWriteFile("filescsv",filename);

    }
    public void addUser(User user) {
        // เรียกmethod add จากArrayList เพื'อเพิ'มข้อมูล
        userArrayList.add(user);
    }
    public ArrayList<User> getAllCards() {
        return userArrayList;}
    public void setRegisterModelArrayList(ArrayList<User> userArrayList) { // login
        this.userArrayList = userArrayList;
    }

    public Boolean userCheck(UserList userList, String usernameTextField, String passwordPasswordField) {
        userList = registerWriteFile.readData();
        for (User user : userList.getAllCards()) {
            System.out.println(userList.getAllCards());
            if ((user.getUsername().equals(usernameTextField)) && user.getPassword().equals(passwordPasswordField)) {
                return true;
            }
        }
        return false;
    }
    public User findMyUsername(String user){
        for(User a : userArrayList) {
            if(user.equals(a.getUsername())){
                return a;
            }
        }
        return  null;
    }

    @Override
    public String toString() {
        return "UserList{" +
                "usernameTextField=" + usernameTextField +
                ", passwordPasswodfield=" + passwordPasswodfield +
                ", registerWriteFile=" + registerWriteFile +
                ", registerWriteFileforStaff=" + registerWriteFileforStaff +
                ", userArrayList=" + userArrayList +
                '}';
    }
}
