package ku.cs.services;

import ku.cs.models.RegisterList;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.github.saacsos.FXRouter;
public class LoginStaffWriteFile extends SignInWriteFile{
    private String strUsername,usernameText;
    private String strPassword;
    public LoginStaffWriteFile(String directory, String fileName, String username, String password) {
        super(directory, fileName, username, password);
    }

    @Override
    public void SignInRecieveReadFile(String usernameTextField, String passwordPasswordField) throws IOException {
        super.SignInRecieveReadFile(usernameTextField,passwordPasswordField);
    }

    @Override
    public boolean checkConfirmsignIn(RegisterList registerList, String usernameTextfield, String passwordPasswordfield) {
        if (registerList.checkForStaff(registerList, usernameTextfield, passwordPasswordfield)) {
            strUsername = usernameTextfield;
            strPassword = passwordPasswordfield;
            SignInWriteFile signInWriteFile = new SignInWriteFile("filescsv", "staff.csv", strUsername, strPassword);
            try {
                signInWriteFile.SignInRecieveReadFile(usernameTextfield, passwordPasswordfield);
                FXRouter.goTo("main");
            } catch (IOException e) {
                System.err.println("ไปที่หน้า main ไม่ได้");
                System.err.println("ให้ตรวจสอบการกําหนดroute");
                return false;
            }
            usernameText = usernameTextfield;
            System.out.println(usernameText);
            return true;
        }
        return false;
    }
}
