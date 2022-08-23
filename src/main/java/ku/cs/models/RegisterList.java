package ku.cs.models;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ku.cs.services.DataSource;

import java.util.ArrayList;

public class RegisterList  {
    private ArrayList<RegisterModel> registerModelArrayList;
    public RegisterList() {
        // ใช้new เพื'อสร้างinstance ของArrayList
        this.registerModelArrayList = new ArrayList<>(); // dynamic type มันสามารถเปลี่ยนเเปลงได้
    }
    public void addStudent(RegisterModel registerList) {
        // เรียกmethod add จากArrayList เพื'อเพิ'มข้อมูล
        registerModelArrayList.add(registerList);
    }
    public ArrayList<RegisterModel> getAllCards() {
        return registerModelArrayList;}

    public void setRegisterModelArrayList(ArrayList<RegisterModel> registerModelArrayList) { // login
        this.registerModelArrayList = registerModelArrayList;
    }

    public Boolean UserCheck(RegisterList registerList,TextField username, PasswordField password) {
        for (RegisterModel p : registerList.getAllCards()) {
            if ((username.getText().equals(p.getUsername())) && password.getText().equals(p.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
