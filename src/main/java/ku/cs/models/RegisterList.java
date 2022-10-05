package ku.cs.models;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ku.cs.controllers.LoginStaffController;
import ku.cs.services.LoginStaffWriteFile;
import ku.cs.services.RegisterWriteFile;

import java.util.ArrayList;

public class RegisterList  {
    @FXML TextField usernameTextField;
    @FXML PasswordField passwordPasswodfield;
    private RegisterWriteFile registerWriteFile;
    private LoginStaffWriteFile registerWriteFileforStaff;
    private ArrayList<RegisterModel> registerModelArrayList;
//    registerWriteFile = new RegisterWriteFile("filescsv","register.csv");
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

    public Boolean userCheck(RegisterList registerList, String usernameTextField, String passwordPasswordField) {
        registerWriteFile = new RegisterWriteFile("filescsv","register.csv");
        registerList = registerWriteFile.readData();
        for (RegisterModel registerModel : registerList.getAllCards()) {
            System.out.println(registerList.getAllCards());
            if ((registerModel.getUsername().equals(usernameTextField)) && registerModel.getPassword().equals(passwordPasswordField)) {
                return true;
            }
        }
        return false;
    }
    public Boolean checkForStaff(RegisterList registerList, String usernameTextField, String passwordPasswordField) {
//        registerWriteFileforStaff = new LoginStaffWriteFile("filescsv","staff.csv",usernameTextField,passwordPasswordField);
        registerWriteFile = new RegisterWriteFile("filescsv","staff.csv");
        System.out.println(1);
        registerList = registerWriteFile.readData();
        System.out.println(registerList);
        for (RegisterModel registerModel : registerList.getAllCards()) {
            System.out.println(registerList.getAllCards());
            if ((registerModel.getUsername().equals(usernameTextField)) && registerModel.getPassword().equals(passwordPasswordField)) {
                return true;
            }
        }
        return false;
    }

}
