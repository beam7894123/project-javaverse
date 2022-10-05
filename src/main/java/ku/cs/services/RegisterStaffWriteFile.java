package ku.cs.services;

import ku.cs.models.RegisterList;

public class RegisterStaffWriteFile extends RegisterWriteFile {

    public RegisterStaffWriteFile(String directoryName, String fileName) {
        super(directoryName, fileName);
    }

    @Override
    public void checkUserNameAndPassword(RegisterList registerList, String usernameTextfield, String passwordPasswordfield, String nameTextField, String surnameTextField, String fileNameImage) {
        super.checkUserNameAndPassword(registerList, usernameTextfield, passwordPasswordfield, nameTextField, surnameTextField, fileNameImage);
    }
}
