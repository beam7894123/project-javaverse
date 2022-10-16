package ku.cs.services;

import ku.cs.models.RegisterList;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.github.saacsos.FXRouter;
import ku.cs.models.StaffList;

public class LoginStaffWriteFile {
    private String strUsername,usernameText;
    private String strPassword;
    private String directory;
    private String fileName;
    private String password,username;
    Locale locale = new Locale("en","en"); //SET LOCALE (if u sys is พศ. it will auto set to คศ. yay~ \^w^/ )

//    public LoginStaffWriteFile(String strUsername, String usernameText, String strPassword) {
//        this.strUsername = strUsername;
//        this.usernameText = usernameText;
//        this.strPassword = strPassword;
//    }

        public LoginStaffWriteFile(String directory, String fileName, String username, String password) {
        this.directory = directory;
        this.fileName = fileName;
        this.username = username;
        this.password = password;
    }


    public void SignInRecieveReadFileforStaff(String usernameTextField, String passwordPasswordField) throws IOException {
        StringBuilder newPurchaseCsv = new StringBuilder();
        String filePath = directory+File.separator+fileName;
        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            if (newPurchaseCsv.length()>0){
                newPurchaseCsv.append(System.getProperty("line.separator"));
            }
            String[] data = line.split(",");
            if (usernameTextField.equals(data[2]) && passwordPasswordField.equals(data[3])) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy ", locale); // <-- ทำไหมไม่เอา space ออกหะ??? 555
                SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss", locale);
                Date d = new Date(System.currentTimeMillis());
                newPurchaseCsv.append(data[0]).append(",").append(data[1])
                        .append(",").append(data[2])
                        .append(",").append(data[3])
                        .append(",").append(data[4])
                        .append(",").append(sdf.format(d))
                        .append(",").append(sdf1.format(d))
                        .append(",").append(data[7]);
//                    break;
            }
            else {
                newPurchaseCsv.append(line);
            }
        }
        bufferedReader.close();
        FileOutputStream fos = new FileOutputStream(file);
        OutputStreamWriter osw =new OutputStreamWriter(fos);
        osw.write(newPurchaseCsv.toString());
        osw.close();
    }

    public boolean checkConfirmsignIn(StaffList staffList, String usernameTextfield, String passwordPasswordfield) {
        if (staffList.checkForStaff(staffList, usernameTextfield, passwordPasswordfield)) {
            strUsername = usernameTextfield;
            strPassword = passwordPasswordfield;
            LoginStaffWriteFile loginStaffWriteFile = new LoginStaffWriteFile("filescsv", "staff.csv", strUsername, strPassword);
            try {
                loginStaffWriteFile.SignInRecieveReadFileforStaff(usernameTextfield, passwordPasswordfield);
                FXRouter.goTo("staff");
            } catch (IOException e) {
                System.err.println("ไปที่หน้า staff ไม่ได้");
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
