package ku.cs.services;


import ku.cs.models.RegisterList;
import ku.cs.models.ReportList;
import ku.cs.models.ReportModel;

import java.io.*;

public class ReportWriteFile implements DataSource {
    private String fileDirectoryName;
    private String fileName;
    private ReportList reportList;
    private ReportModel reportModel;

    private String image;

    public ReportWriteFile(String fileDirectoryName, String fileName) {
        this.fileDirectoryName = fileDirectoryName;
        this.fileName = fileName;
        checkFileIsExisted();
        reportList = new ReportList();
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
            ReportModel reportModel = new ReportModel(data[0].trim(),data[1].trim(),Integer.parseInt(data[2].trim()),data[3].trim(),data[4]); // obj
            ReportList.addReport(reportModel);
        }
        reader.close();
    }

    public ReportList Read() {
        try {
            readCustomer();
        } catch (FileNotFoundException e) {
            System.err.println(this.fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
        }
        return reportList;
    }

    @Override
    public RegisterList readData() {
        return null;
    }

    @Override
    public ReportList readData1() {
        try {
            readCustomer();
        } catch (FileNotFoundException e) {
            System.err.println(this.fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
        }
        return reportList;
    }

    @Override
    public void writeData(RegisterList write) {

    }

    @Override
    public void writeData1(ReportList write) {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file,true);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (ReportModel reportModel : write.getReports()) {
                String line = reportModel.getTopic() + ","
                        + reportModel.getDetail() + ","
                        + reportModel.getVoteScore() + ","
                        + reportModel.getDateTime() + ","
                        + reportModel.getCategory();
                writer.append(line);
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            System.err.println("Cannot write " + filePath);
        }
    }
}
