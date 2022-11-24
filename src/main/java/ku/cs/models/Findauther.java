package ku.cs.models;

import java.util.ArrayList;

public class Findauther implements Findable<ReportModel> {
    private ArrayList<ReportModel> anything;

//    @Override
//    public ReportModel find(String user) {
//        ReportList searchReportList = new ReportList();
//        for (ReportModel a : anything) {
//            if (user.equals(a.getAuthorName())) {
////                 anything.add(a);
//                searchReportList.addReport(a);
//            }
//        }
//        return searchReportList;
//    }

    @Override
    public String getAnything(ReportModel anything) {
       return anything.getAuthorName();
    }
}

