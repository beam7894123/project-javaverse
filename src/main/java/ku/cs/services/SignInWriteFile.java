package ku.cs.services;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ku.cs.controllers.SignInController;
import ku.cs.models.RegisterList;
import com.github.saacsos.FXRouter;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SignInWriteFile {
    private String directory;
    private String fileName;
    private RegisterList registerList;
    private String username;
    private String password;
    private String strUsername;
    private String strPassword;

    public SignInWriteFile(String directory, String fileName, String username, String password) {
        this.directory = directory;
        this.fileName = fileName;
        this.username = username;
        this.password = password;
//        StrUserID = strUserID;
    }

    public void SignInRecieveReadFile() throws IOException {
        StringBuilder newPurchaseCsv = new StringBuilder();
        File file = new File("filescsv/register.csv");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            if (newPurchaseCsv.length()>0){
                newPurchaseCsv.append(System.getProperty("line.separator"));
            }
            String[] data = line.split(",");
            if (username.equals(data[2]) && password.equals(data[3])) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy ");
                SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
                Date d = new Date(System.currentTimeMillis());
                newPurchaseCsv.append(data[0]).append(",").append(data[1])
                        .append(",").append(data[2])
                        .append(",").append(data[3])
                        .append(",").append(data[4])
                        .append(",").append(sdf.format(d))
                        .append(",").append(sdf1.format(d));
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

    public void checkConfirmsignIn(RegisterList registerList,TextField usernameTextfield,PasswordField passwordPasswordfield,Label loginChecker){
        if(usernameTextfield.equals("admin")&&passwordPasswordfield.equals("admin")){
            try {
                FXRouter.goTo("admin");
            } catch (IOException e) {
                System.err.println("ไปที่หน้าhome ไม่ได้");
                System.err.println("ให้ตรวจสอบการกําหนดroute");
            }
        }else if (registerList.UserCheck(registerList,usernameTextfield,passwordPasswordfield)) {
            strUsername = usernameTextfield.getText();
            strPassword = passwordPasswordfield.getText();
            SignInWriteFile signInWriteFile = new SignInWriteFile("filescsv","register.csv",strUsername,strPassword);
            try {
                signInWriteFile.SignInRecieveReadFile();
                FXRouter.goTo("main");
            } catch (IOException e) {
//                throw new RuntimeException(e);
                loginChecker.setText("Username or Password is incorrect");
                System.err.println("ไปที่หน้า main ไม่ได้");
                System.err.println("ให้ตรวจสอบการกําหนดroute");
            }

        }

        else {
            loginChecker.setText("Username or Password is incorrect");
//            try {
//                loginChecker.setText("Username or Password is incorrect");
//            } catch (IOException e) {
//                System.err.println("ไปที่หน้าhome ไม่ได้");
//                System.err.println("ให้ตรวจสอบการกําหนดroute");
//            }
        }
    }
//    public void checkConfirm(RegisterList registerList,String usernameTextfield,String passwordPasswordfield,Label loginChecker){
//        if(usernameTextfield.equals("admin")&&passwordPasswordfield.equals("admin")){
//            try {
//                FXRouter.goTo("admin");
//            } catch (IOException e) {
//                System.err.println("ไปที่หน้าhome ไม่ได้");
//                System.err.println("ให้ตรวจสอบการกําหนดroute");
//            }
//        }else if (registerList.UserCheck(registerList,usernameTextfield,passwordPasswordfield)) {
////            strUsername = usernameTextfield;
////            strPassword = passwordPasswordfield;
//            SignInWriteFile signInWriteFile = new SignInWriteFile("filescsv","register.csv",usernameTextfield,passwordPasswordfield);
//            try {
//                signInWriteFile.SignInRecieveReadFile();
//                FXRouter.goTo("main");
//            } catch (IOException e) {
////                throw new RuntimeException(e);
//                loginChecker.setText("Username or Password is incorrect");
//                System.err.println("ไปที่หน้า main ไม่ได้");
//                System.err.println("ให้ตรวจสอบการกําหนดroute");
//            }
//
//        }
//
//        else {
//            loginChecker.setText("Username or Password is incorrect");
////            try {
////                loginChecker.setText("Username or Password is incorrect");
////            } catch (IOException e) {
////                System.err.println("ไปที่หน้าhome ไม่ได้");
////                System.err.println("ให้ตรวจสอบการกําหนดroute");
////            }
//        }
//
//    }
}
