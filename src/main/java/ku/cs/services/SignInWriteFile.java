package ku.cs.services;

import ku.cs.models.UserList;
import com.github.saacsos.FXRouter;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SignInWriteFile {
    private String directoryName;
    private String fileName;
    private UserList userList;
    private String strUsername;
    private String strPassword;
    private String usernameText;
    private String username;
    private String password;
//    private UsercheckRegister usercheckRegister;

    public SignInWriteFile(String directoryName, String fileName, String username, String password) {
        this.directoryName = directoryName;
        this.fileName = fileName;
        this.username = username;
        this.password = password;
    }

    Locale locale = new Locale("en","en"); //SET LOCALE (if u sys is พศ. it will auto set to คศ. yay~ \^w^/ )
    public void SignInRecieveReadFile(String usernameTextField,String passwordPasswordField) throws IOException {
        StringBuilder newPurchaseCsv = new StringBuilder();
        String filePath = directoryName+File.separator+fileName;
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

    public boolean checkConfirmsignIn(UserList userList, String usernameTextfield, String passwordPasswordfield){
        if(usernameTextfield.equals("admin")&&passwordPasswordfield.equals("admin")){
            try {
                FXRouter.goTo("admin");
                return true;
            } catch (IOException e) {
                System.err.println("ไปที่หน้าhome ไม่ได้");
                System.err.println("ให้ตรวจสอบการกําหนดroute");
            }
        }else if (userList.userCheck(userList,usernameTextfield,passwordPasswordfield)) {
            strUsername = usernameTextfield;
            strPassword = passwordPasswordfield;
            SignInWriteFile signInWriteFile = new SignInWriteFile("filescsv","register.csv",strUsername,strPassword);
            try {
                signInWriteFile.SignInRecieveReadFile(usernameTextfield,passwordPasswordfield);
                FXRouter.goTo("main");
            } catch (IOException e) {
//                throw new RuntimeException(e);
//                loginChecker.setText("Username or Password is incorrect");
                System.err.println("ไปที่หน้า main ไม่ได้");
                System.err.println("ให้ตรวจสอบการกําหนดroute");
                return false;
            }
            usernameText =  usernameTextfield;
            System.out.println(usernameText);
            return true;
        }

        return false;
    }
    }