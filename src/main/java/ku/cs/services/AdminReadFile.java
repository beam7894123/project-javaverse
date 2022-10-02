package ku.cs.services;

import ku.cs.controllers.AdminController;
import ku.cs.models.*;

import java.io.*;
import java.util.Comparator;

public class AdminReadFile implements DataSource<AdminList>{
    private String fileDirectoryName;
    private String fileName;
    private AdminList adminList;

    public AdminReadFile(String fileDirectoryName,String fileName){
        this.fileDirectoryName =fileDirectoryName;
        this.fileName = fileName;
    }

    public AdminList readData() { //readData checker
        try {
            readCustomer();
        } catch (FileNotFoundException e) {
            System.err.println(this.fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
        }
        return adminList;
    }

    private void readCustomer() throws IOException {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String line = "";
        adminList = new AdminList();
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            AdminModels customer = new AdminModels(data[0].trim(),data[1].trim(),data[2].trim(),data[3].trim(),data[4].trim(),data[5].trim(),data[6].trim());
            customer.setImage(data[6].trim());
//            RegisterModel customer = new RegisterModel(data[0].trim(),data[1].trim(),data[2].trim(),data[3].trim(),data[4].trim(),data[5].trim()); // obj
//            customer.setImage(data[6].trim());
            adminList.addStudent(customer);
        }
        reader.close();
    }

    @Override
    public void writeData(AdminList adminList) {

    }

//    public void ReadFile() throws IOException {     // NOT USE ...for now //
//        String filePath = fileDirectoryName + File.separator + fileName;
//        File file = new File(filePath);
//        FileReader fileReader = null;
//        BufferedReader reader = new BufferedReader(fileReader);
//        String line = "";
//        while ((line = reader.readLine()) != null) {
//            String[] data = line.split(",");
//            RegisterModel customer = new RegisterModel(data[0].trim(), data[1].trim(), data[2].trim(), data[3].trim(), data[4], data[5]); // obj
//            customer.setImage(data[6].trim());
//            adminList.addStudent(customer);
//        }
//        try {
//            reader.close();
//        } catch (FileNotFoundException e) {
//            System.err.println(this.fileName + " not found");
//        } catch (IOException e) {
//            System.err.println("IOException from reading " + this.fileName);
//        }
//    }

    // NOT USE //
//    @Override
//    public RegisterList readData() {
//        RegisterList list = new RegisterList();
//        String filePath = fileDirectoryName + File.separator + fileName;
//        File file = new File(filePath);
//        FileReader reader = null;
//        BufferedReader buffer = null;
//
//        try {
//            reader = new FileReader(file);
//            buffer = new BufferedReader(reader);
//
//            String line = "";
//            while ((line = buffer.readLine()) != null){
//                String[] data = line.split(",");
//                RegisterModel card = new RegisterModel(
//                        data[0].trim(),
//                        data[1].trim(),
//                        data[2].trim(),
//                        data[3].trim(),
//                        data[4].trim(),
//                        data[5].trim()
//
//                );
//                list.addStudent(card);
//            }
//
//        } catch (FileNotFoundException e) {
//            System.err.println(this.fileName + " not found");
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            System.err.println("IOException from reading " + this.fileName);
//            throw new RuntimeException(e);
//        } finally {
//            try{
//                buffer.close();
//                reader.close();
//            } catch (IOException e){
//                throw new RuntimeException(e);
//            }
//        }
//
//
//        return list;
//    }

    public void writeData(RegisterList write) {

    }


//    public void setTime(){ //convert dd/mm/yyy --> yyyy/MM/dd HH:mm:ss (NOT WORKING ON IT YET)
//        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        Date date = new Date();
//        System.out.println(dateFormat.format(date));
//
//    }
// ดึงข้อมูลarrylist register --> แก้ไขลำดับ list -->สร้าง arrylist ใหม่ --> ส่งค่าไป --> แสดงค่า --> profit?? 'w'b
// OR ดึงข้อมูลarrylist register --> แก้ไขลำดับ list --> ส่งเข้า csv -->  ดึงข้อมูลarrylist register อีกรอบ --> ส่งค่าไป --> แสดงค่า --> profit?? 'w'b
    class sortCompare implements Comparator<ReportModel>
    {
        @Override
        public int compare(ReportModel o1, ReportModel o2) {
            return o2.getDateTime().compareTo(o1.getDateTime());
        }
    }
}
