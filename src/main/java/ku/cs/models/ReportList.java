package ku.cs.models;

import java.util.ArrayList;

public class ReportList {
    private static ArrayList<ReportModel> reports;

    public ReportList() {
        reports = new ArrayList<>();
    }

    public static void addReport(ReportModel report){
        reports.add(report);
    }

    public static ArrayList<ReportModel> getReports(){
        return reports;
    }
}
