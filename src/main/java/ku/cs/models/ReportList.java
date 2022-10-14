package ku.cs.models;

import java.util.ArrayList;

public class ReportList {
    private ArrayList<ReportModel> reports;

    public ReportList() {
        reports = new ArrayList<>();
    }

    public void addReport(ReportModel report){
        reports.add(report);
    }

    public ArrayList<ReportModel> getReports(){
        return reports;
    }

}
