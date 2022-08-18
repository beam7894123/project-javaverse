package ku.cs.services;

import ku.cs.models.RegisterList;
import ku.cs.models.RegisterModel;

import java.io.*;

public class RegisterWriteFile implements DataSource {

    private String fileDirectoryName;
    private String fileName;
    private RegisterList registerList;


//    public RegisterWriteFile() {
//        this("filescsv", "register.csv");
//    }

    public RegisterWriteFile(String fileDirectoryName, String fileName) {
        this.fileDirectoryName = fileDirectoryName;
        this.fileName = fileName;
        checkFileIsExisted();
        registerList = new RegisterList();
    }

    private void checkFileIsExisted() {
        File file = new File(fileDirectoryName);
        if (!file.exists()) {
            file.mkdirs();
        }
        String filePath = fileDirectoryName + File.separator + fileName;
        file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Cannot create " + filePath);
            }
        }
    }

    private void readCustomer() throws IOException {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String line = "";
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            RegisterModel customer = new RegisterModel(data[0].trim(),data[1].trim(),data[3].trim(),data[4].trim(),data[5],data[6]); // obj
            customer.setImage(data[7].trim());
            registerList.addStudent(customer);
        }
        reader.close();
    }

    public RegisterList Read() {
        try {
            readCustomer();
        } catch (FileNotFoundException e) {
            System.err.println(this.fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
        }
        return registerList;
    }

    @Override
    public RegisterList readData() {
        try {
            readCustomer();
        } catch (FileNotFoundException e) {
            System.err.println(this.fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
        }
        return registerList;
    }

    @Override
    public void writeData(RegisterList write) {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (RegisterModel registerModel : write.getAllCards()) {
                String line = registerModel.getName() + ","
                        + registerModel.getSurname() + ","
                        + registerModel.getUsername() + ","
                        + registerModel.getPassword() + ","
                        + registerModel.getImage()+","
                        +registerModel.getDate()+","
                        + registerModel.getTime();
                writer.append(line);
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            System.err.println("Cannot write " + filePath);
        }
    }
}

//    private void checkFileIsExisted() {
//        File file = new File(fileDirectoryName);
//        if (!file.exists()) {
//            file.mkdirs();
//        }
//        String filePath = fileDirectoryName + File.separator + fileName;
//        file = new File(filePath);
//        if (!file.exists()) {
//            try {
//                file.createNewFile();
//            } catch (IOException e) {
//                System.err.println("Cannot create " + filePath);
//            }
//        }
//    }
//
//
//    @Override
//    public RegisterList readData() {
//       RegisterList list = new RegisterList();
//       String filePath = fileDirectoryName + File.separator + fileName;
//       File file = new File(filePath);
//       FileReader reader = null;
//       BufferedReader buffer = null;
//       try{
//           reader = new FileReader(file);
//           buffer = new BufferedReader(reader);
//           String line = "";
//           while((line = buffer.readLine())!=null){
//               String [] data = line.split(",");
//               RegisterModel registerModel = new RegisterModel(data[0].trim(),data[1].trim(),data[2].trim(),data[3].trim());
//               registerModel.setImage(data[4]);
//               registerList.addStudent(registerModel);
//
//           }
//       } catch (FileNotFoundException e) {
//           throw new RuntimeException(e);
//       } catch (IOException e) {
//           throw new RuntimeException(e);
//       }finally {
//           try {
//               reader.close();
//               buffer.close();
//           } catch (IOException e) {
//               throw new RuntimeException(e);
//           }
//       }
//       return list;
//    }
//
//    @Override
//    public void writeData(RegisterList write) {
//        String filePath = fileDirectoryName+File.separator+fileName;
//        File file = new File(filePath);
//        try {
//            FileWriter fileWriter = new FileWriter(file);
//            BufferedWriter buffer = new BufferedWriter(fileWriter);
//
//            for (RegisterModel registerModel: write.getAllCards()){
//                String line =registerModel.getName()+","
//                        +registerModel.getSurname()+","
//                        +registerModel.getUsername()+","
//                        +registerModel.getPassword()+","
//                        +registerModel.getImage();
//                buffer.append(line);
//                buffer.newLine();
//            }
//            buffer.close();
//        } catch (IOException e) {
//            System.err.println("Cannot write "+filePath);
//        }
//
//    }



