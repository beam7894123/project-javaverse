package ku.cs.models;

import ku.cs.services.RegisterWriteFile;

import java.util.ArrayList;

public class StaffList {
    private RegisterWriteFile registerWriteFile1;
    private ArrayList<Staff> staffArrayList;

    public void addStaffToArrayList(Staff registerList) {
        // เรียกmethod add จากArrayList เพื'อเพิ'มข้อมูล
        staffArrayList.add(registerList);
    }
    public StaffList() {
        // ใช้new เพื'อสร้างinstance ของArrayList
        this.staffArrayList = new ArrayList<>(); // dynamic type มันสามารถเปลี่ยนเเปลงได้
//        registerWriteFile =
        registerWriteFile1 = new RegisterWriteFile("filescsv","staff.csv");
    }

    public ArrayList<Staff> getAllCards() {
        return staffArrayList;}

    public Boolean checkForStaff(StaffList staffList, String usernameTextField, String passwordPasswordField) {
        staffList = registerWriteFile1.readDataforStaff();
//        System.out.println(staffList); //so musch text ew -^-
        for (Staff staff : staffList.getAllCards()) {
//            System.out.println(staffList.getAllCards()); //so musch ew text -^-
            if ((staff.getUsername().equals(usernameTextField)) && staff.getPassword().equals(passwordPasswordField)) {
                return true;
            }
        }
        return false;
    }

    public Staff findMyUsername(String staff){
        for(Staff a : staffArrayList) {
            if(staff.equals(a.getUsername())){
                return a;
            }
        }
        return  null;
    }
}
