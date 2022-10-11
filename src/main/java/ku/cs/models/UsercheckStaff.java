package ku.cs.models;

import ku.cs.services.RegisterWriteFile;

public class UsercheckStaff {
    private RegisterWriteFile registerWriteFile;
    //    private RegisterList registerList;
    public Boolean userCheck(RegisterList registerList, String usernameTextField, String passwordPasswordField) {
        registerWriteFile = new RegisterWriteFile("filescsv","staff.csv");
        registerList = registerWriteFile.readData();
        for (RegisterModel registerModel : registerList.getAllCards()) {
            System.out.println(registerList.getAllCards());
            if ((registerModel.getUsername().equals(usernameTextField)) && registerModel.getPassword().equals(passwordPasswordField)) {
                return true;
            }
        }
        return false;
    }
}
