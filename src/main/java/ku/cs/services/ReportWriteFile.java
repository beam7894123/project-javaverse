package ku.cs.services;


import ku.cs.models.ReportList;
import ku.cs.models.ReportModel;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ReportWriteFile implements DataSource<ReportList> {
    private String fileDirectoryName;
    private String fileName;

    private ReportList temp;


    private String image;

    // TIME CONVERTER !!! DO NOT TOUCH !!! ห้ามลบ ถ้าเกิด Error บอก beam7894123 ก่อน ไม่งั้นต่อยนะ >:< //
    Locale locale = new Locale("en","en"); //SET LOCALE (if u sys is พศ. it will auto set to คศ. yay~ \^w^/ )
    SimpleDateFormat timeFormat1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", locale);
    private final Date defaultdateTime; {try {defaultdateTime = timeFormat1.parse("01/01/2000 00:00:00");} catch (
            ParseException e) {throw new RuntimeException(e);}}
    public Date setDateTime(String time) {

        try {
            return timeFormat1.parse(time);
        } catch (ParseException e) {
            System.err.println("\n!!TIME CONVERTER(v.2) ERROR!!");
            System.err.println("Time, Dr. Freeman?");
//            System.err.println("Look like user date " + "\"" + getName()+ " " + getSurname() + "\"" + " is mess up -w-");
//            System.err.println("Here input is: " + takeDateTime + "");
//          takeDateTime = "00-00-0000 00:00:00";
            return defaultdateTime;
        }
    }
// TIME CONVERTER // END // END // END // END // END // END // END // END // END // END // END // END // END

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
            reader = new FileReader(file, StandardCharsets.UTF_8);
            buffer = new BufferedReader(reader);
            String line = "";
            while ((line = buffer.readLine()) != null) {
                String[] data = line.split(",");
                ReportModel reportModel = new ReportModel(data[0].trim(),
                        data[1].trim(),Integer.parseInt(data[2]),
                        data[3].trim(),data[4].trim(),data[5].trim(),data[6].trim(),data[7].trim()); // obj
                reportModel.setRealDateTime(setDateTime(data[4]));
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
            writer = new FileWriter(file, StandardCharsets.UTF_8);
            buffer = new BufferedWriter(writer);
            ArrayList<ReportModel> reports = null;
            for (ReportModel reportModel : reportList.getReportList()) {
                String line = reportModel.getTopic() + ","
                        + reportModel.getDetail() + ","
                        + reportModel.getVoteScore() + ","
                        + reportModel.getCategory() + ","
                        + reportModel.getDateTime() + ","
                        + reportModel.getAuthorName() + ","
                        + reportModel.getSolveProblem() + ","
                        + reportModel.getStatus();
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