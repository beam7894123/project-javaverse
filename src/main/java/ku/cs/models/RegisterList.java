package ku.cs.models;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ku.cs.services.DataSource;
import ku.cs.services.RegisterWriteFile;

import java.util.ArrayList;

public class RegisterList  {
    private RegisterWriteFile registerWriteFile;
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
        registerWriteFile = new RegisterWriteFile("filescsv","register.csv");
        registerList = registerWriteFile.readData();
        for (RegisterModel registerModel : registerList.getAllCards()) {
            System.out.println(registerList.getAllCards());
            if ((registerModel.getUsername().equals(username.getText())) && registerModel.getPassword().equals(password.getText())) {
                return true;
            }
        }
        return false;
    }
}
