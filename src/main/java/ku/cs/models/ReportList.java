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
    public ReportList findMyReport(String writer){
        ReportList searchReportList = new ReportList();
        for(ReportModel a : reports) {
            if(writer.equals(a.getAuthorName())){
                searchReportList.addReport(a);
            }
        }
        return  searchReportList;
    }

}
