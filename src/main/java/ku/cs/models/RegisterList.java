package ku.cs.models;

import java.util.ArrayList;

public class RegisterList {
    private ArrayList<RegisterModel> registerModelArrayList;
    public RegisterList() {
        // ใช้new เพื'อสร้างinstance ของArrayList
        registerModelArrayList = new ArrayList<>(); // dynamic type มันสามารถเปลี่ยนเเปลงได้
    }
    public void addStudent(RegisterModel registerList) {
        // เรียกmethod add จากArrayList เพื'อเพิ'มข้อมูล
        registerModelArrayList.add(registerList);
    }
    public ArrayList<RegisterModel> getAllCards() {return registerModelArrayList;}
}
