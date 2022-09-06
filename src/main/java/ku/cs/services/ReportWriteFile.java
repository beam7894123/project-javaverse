package ku.cs.services;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ku.cs.models.RegisterList;
import ku.cs.models.ReportList;
import ku.cs.models.ReportModel;

import java.io.*;

public class ReportWriteFile implements DataSource<ReportList> {
    private String fileDirectoryName;
    private String fileName;

    private ReportList temp;


    private String image;

    public ReportWriteFile(String fileDirectoryName, String fileName) {
        this.fileDirectoryName = fileDirectoryName;
        this.fileName = fileName;
        checkFileIsExisted();
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




    @Override
    public ReportList readData() {
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
                ReportModel reportModel = new ReportModel(data[0].trim(),
                        data[1].trim(),Integer.parseInt(data[2].trim()),
                        data[3].trim(),data[4].trim(),data[5]); // obj
                reportList.addReport(reportModel);
            }
        } catch (FileNotFoundException e) {
            System.err.println(this.fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
        }finally {
            try {
                buffer.close();
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return reportList;
    }


    @Override
    public void writeData(ReportList reportList) {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter writer = null;
        BufferedWriter buffer = null;
        try {
            writer = new FileWriter(file,true);
            buffer = new BufferedWriter(writer);
            for (ReportModel reportModel : reportList.getReports()) {
                String line = reportModel.getTopic() + ","
                        + reportModel.getDetail() + ","
                        + reportModel.getVoteScore() + ","
                        + reportModel.getDateTime() + ","
                        + reportModel.getCategory() + ","
                        + reportModel.getAuthorName();
                buffer.append(line);
                buffer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Cannot write " + filePath);
        }finally {
            try {
                buffer.close();
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


}