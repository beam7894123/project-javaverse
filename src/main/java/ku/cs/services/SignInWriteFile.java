package ku.cs.services;

import ku.cs.controllers.SignInController;
import ku.cs.models.RegisterList;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SignInWriteFile {
    private String directory;
    private String fileName;
    private RegisterList registerList;
    private String username;
    private String password;

    public SignInWriteFile(String directory, String fileName, String username, String password) {
        this.directory = directory;
        this.fileName = fileName;
        this.username = username;
        this.password = password;
//        StrUserID = strUserID;
    }

    public void SignInRecieveReadFile() throws IOException {
        StringBuilder newStudent = new StringBuilder();
        File file = new File("filescsv/register.csv");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            if (newStudent.length()>0){
                newStudent.append(System.getProperty("line.separator"));
            }
            String[] data = line.split(",");
            if (username.equals(data[1]) && password.equals(data[2])) {
//                SignInController.StrUserID = data[6];
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy ");
                SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
                Date d = new Date(System.currentTimeMillis());
                newStudent.append(data[0]).append(",").append(data[1])
                        .append(",").append(data[2])
                        .append(",").append(data[3])
                        .append(",").append(data[4])
                        .append(",").append(sdf.format(d))
                        .append(",").append(sdf1.format(d));
//                    break;
            }
            else {
                newStudent.append(line);
            }
        }
        bufferedReader.close();
        FileOutputStream fos = new FileOutputStream(file);
        OutputStreamWriter osw =new OutputStreamWriter(fos);
        osw.write(newStudent.toString());
        osw.close();
    }
}
