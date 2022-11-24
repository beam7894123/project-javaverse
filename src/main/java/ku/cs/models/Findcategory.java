package ku.cs.models;

import java.util.ArrayList;

public class Findcategory implements Findable<ReportModel>{
    @Override
    public String getAnything(ReportModel anything) {
        return anything.getCategory();
    }
//    private ArrayList<ReportModel> anything;
//    @Override
//    public ReportModel find(String user) {
//        ReportList searchReportList = new ReportList();
//        for(ReportModel a : anything) {
//            if(user.equals(a.getCategory())){
//                searchReportList.addReport(a);
//            }
//        }
//        return searchReportList;
//    }
}
