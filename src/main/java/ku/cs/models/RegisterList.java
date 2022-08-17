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
    public ArrayList<RegisterModel> getAllCards() {return registerModelArrayList;}

    public void setRegisterModelArrayList(ArrayList<RegisterModel> registerModelArrayList) { // login
        this.registerModelArrayList = registerModelArrayList;
    }

    public Boolean studentCheck(RegisterList registerList, TextField usernameTextField, PasswordField passwordField){ // ใช้กับ login
        for (RegisterModel registerModel: registerList.getAllCards()){
            if ((usernameTextField.getText().equals(registerModel.getUsername()))&&(passwordField.getText().equals(registerModel.getPassword()))){
                return true;
            }
        }
        return false;
    }

}
