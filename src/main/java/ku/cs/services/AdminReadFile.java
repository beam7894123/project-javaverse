package ku.cs.services;

import ku.cs.controllers.AdminController;
import ku.cs.models.RegisterList;
import ku.cs.models.RegisterModel;

import java.io.*;

public class AdminReadFile {
    private String fileDirectoryName;
    private String fileName;
    private RegisterList registerList;

    public AdminReadFile(String fileDirectoryName,String fileName){
        this.fileDirectoryName =fileDirectoryName;
        this.fileName = fileName;
//        RegisterList registerList1 = new RegisterList();
    }
    public void ReadFile() throws IOException{
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader fileReader = null;
        BufferedReader reader = new BufferedReader(fileReader);
        String line = "";
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            RegisterModel customer = new RegisterModel(data[0].trim(),data[1].trim(),data[2].trim(),data[3].trim(),data[4],data[5]); // obj
            customer.setImage(data[6].trim());
            registerList.addStudent(customer);
        }
        try {
            reader.close();
        } catch (FileNotFoundException e) {
            System.err.println(this.fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
        }
    }
}
