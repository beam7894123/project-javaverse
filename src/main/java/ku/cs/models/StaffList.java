package ku.cs.models;

import ku.cs.services.RegisterWriteFile;

import java.util.ArrayList;

public class StaffList {
    private  RegisterWriteFile registerWriteFile;
    private  ArrayList<RegisterModel> staffList;

    public StaffList() {
        staffList = new ArrayList<>();
    }

    public  void addStaff(RegisterModel model){
        staffList.add(model);
    }

    public Boolean checkForStaff(StaffList staffList, String usernameTextField, String passwordPasswordField) {
//        registerWriteFileforStaff = new LoginStaffWriteFile("filescsv","staff.csv",usernameTextField,passwordPasswordField);
        registerWriteFile = new RegisterWriteFile("filescsv","staff.csv");
        System.out.println(1);
        staffList = registerWriteFile.readDataforStaff();
        System.out.println(staffList);
        for (RegisterModel registerModel : staffList.getAllstaff()) {
            System.out.println(staffList.getAllstaff());
            if ((registerModel.getUsername().equals(usernameTextField)) && registerModel.getPassword().equals(passwordPasswordField)) {
                return true;
            }
        }
        return false;
    }
    public  ArrayList<RegisterModel> getAllstaff(){
        return staffList;
    }

    @Override
    public String toString() {
        return "StaffList{" +
                "registerWriteFile=" + registerWriteFile +
                ", staffList=" + staffList +
                '}';
    }
}
