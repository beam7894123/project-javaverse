package ku.cs.services;


import ku.cs.controllers.ReportFormController;
import ku.cs.models.RegisterList;
import ku.cs.models.ReportList;
import ku.cs.models.ReportModel;

import java.io.*;

public class ReportWriteFile extends ReportFormController {
    private String fileDirectoryName;
    private String fileName;
    private ReportList reportList;

    public ReportWriteFile(String fileDirectoryName, String fileName) {
        this.fileDirectoryName = fileDirectoryName;
        this.fileName = fileName;
        checkFileIsExisted();
    }

    public ReportList readData(){
        ReportList reportList = new ReportList();
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader reader = null;
        BufferedReader buffer = null;

        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);
            String line = "";
            while ((line = buffer.readLine()) != null) {
                String[] data = line.split(",");
                ReportModel reportModel = new ReportModel(data[0], data[1], Integer.parseInt(data[2]) ,data[3] ,data[4]);
                ReportList.addReport(reportModel);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                buffer.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
            return reportList;
    }

    public void writeData(ReportList reportList){
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            for (ReportModel reportModel: ReportList.getReports()){
                String line = reportModel.getTopic() + ","
                        + reportModel.getDetail() + ","
                        + reportModel.getCategory() + ","
                        + reportModel.getDateTime() + ","
                        + reportModel.getVoteScore();
                writer.append(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Cannot write " + filePath);
        }
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

    //    @Override
//    public RegisterList readData() {
//        return null;
//    }

//    @Override
//    public ReportList readData1() {
//        ReportList reportList = new ReportList();
//        String filePath = fileDirectoryName + File.separator + fileName;
//        File file = new File(filePath);
//        FileReader reader = null;
//        BufferedReader buffer = null;
//        try {
//            reader = new FileReader(file);
//            buffer = new BufferedReader(reader);
//            String line = "";
//            while ((line = buffer.readLine()) != null) {
//                String[] data = line.split(",");
//                ReportModel reportModel = new ReportModel(data[0].trim(),data[1].trim(),Integer.parseInt(data[2].trim()),data[3].trim(),data[4]); // obj
//                ReportList.addReport(reportModel);
//            }
//        } catch (FileNotFoundException e) {
//            System.err.println(this.fileName + " not found");
//        } catch (IOException e) {
//            System.err.println("IOException from reading " + this.fileName);
//        }finally {
//            try {
//                buffer.close();
//                reader.close();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        return reportList;
//    }
//
//    @Override
//    public void writeData(RegisterList write) {
//
//    }

//    @Override
//    public void writeData1(ReportList reportList) {
//        String filePath = fileDirectoryName + File.separator + fileName;
//        File file = new File(filePath);
//        FileWriter writer = null;
//        BufferedWriter buffer = null;
//        try {
//            writer = new FileWriter(file,true);
//             buffer = new BufferedWriter(writer);
//            for (ReportModel reportModel : reportList.getReports()) {
//                String line = reportModel.getTopic() + ","
//                        + reportModel.getDetail() + ","
//                        + reportModel.getVoteScore() + ","
//                        + reportModel.getDateTime() + ","
//                        + reportModel.getCategory();
//                buffer.append(line);
//                buffer.newLine();
//            }
//        } catch (IOException e) {
//            System.err.println("Cannot write " + filePath);
//        }finally {
//            try {
//                buffer.close();
//                writer.close();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
}
