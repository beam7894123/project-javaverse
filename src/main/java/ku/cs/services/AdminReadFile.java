package ku.cs.services;

import ku.cs.controllers.AdminController;
import ku.cs.models.RegisterList;
import ku.cs.models.RegisterModel;
import ku.cs.models.ReportList;

import java.io.*;

public class AdminReadFile implements DataSource<RegisterList>{
    private String fileDirectoryName;
    private String fileName;

    public AdminReadFile(String fileDirectoryName,String fileName){
        this.fileDirectoryName =fileDirectoryName;
        this.fileName = fileName;
    }
    // NOT USE //
//    public void ReadFile() throws IOException{
//        String filePath = fileDirectoryName + File.separator + fileName;
//        File file = new File(filePath);
//        FileReader fileReader = null;
//        BufferedReader reader = new BufferedReader(fileReader);
//        String line = "";
//        while ((line = reader.readLine()) != null) {
//            String[] data = line.split(",");
//            RegisterModel customer = new RegisterModel(data[0].trim(),data[1].trim(),data[2].trim(),data[3].trim(),data[4],data[5]); // obj
//            customer.setImage(data[6].trim());
//            registerList.addStudent(customer);
//        }
//        try {
//            reader.close();
//        } catch (FileNotFoundException e) {
//            System.err.println(this.fileName + " not found");
//        } catch (IOException e) {
//            System.err.println("IOException from reading " + this.fileName);
//        }
//    }

    @Override
    public RegisterList readData() {
        RegisterList list = new RegisterList();
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader reader = null;
        BufferedReader buffer = null;

        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            String line = "";
            while ((line = buffer.readLine()) != null){
                String[] data = line.split(",");
                RegisterModel card = new RegisterModel(
                        data[0].trim(),
                        data[1].trim(),
                        data[2].trim(),
                        data[3].trim(),
                        data[4].trim(),
                        data[5].trim()

                );
                list.addStudent(card);
            }

        } catch (FileNotFoundException e) {
            System.err.println(this.fileName + " not found");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
            throw new RuntimeException(e);
        } finally {
            try{
                buffer.close();
                reader.close();
            } catch (IOException e){
                throw new RuntimeException(e);
            }
        }


        return list;
    }



    @Override
    public void writeData(RegisterList write) {

    }


}
