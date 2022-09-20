package ku.cs.models;


import java.util.ArrayList;

public class AdminList {

    private ArrayList<AdminModels> adminModelArrayList;

    public AdminList() {
        // ใช้new เพื'อสร้างinstance ของArrayList
        this.adminModelArrayList = new ArrayList<>(); // dynamic type มันสามารถเปลี่ยนเเปลงได้
    }
    public ArrayList<AdminModels> getAllCards() {
        return adminModelArrayList;
    }
    public void addStudent(AdminModels adminModels) {
        // เรียกmethod add จากArrayList เพื'อเพิ'มข้อมูล
        adminModelArrayList.add(adminModels);
    }
}
