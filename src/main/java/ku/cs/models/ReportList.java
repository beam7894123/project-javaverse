package ku.cs.models;

import java.util.ArrayList;
import java.util.List;

public class ReportList extends ReportModel{
    private ArrayList<ReportModel> reportList;

    public ReportList() {
        super();
        reportList = new ArrayList<>();
    }

    public void addReport(ReportModel report){
        reportList.add(report);
    }

    public ArrayList<ReportModel> getReportList(){
        return reportList;
    }

    public List<ReportModel> findMyThing(String thing, Findable<ReportModel> findableType){
        List<ReportModel> searchReportList= new ArrayList<>();
        for(ReportModel a : reportList) {
            if(thing.equals(findableType.getAnything(a))){ // polymorphism
                searchReportList.add(a);
            }
        }
        return  searchReportList;
    }

//    public ReportList findMyCategory(String category){
//        ReportList searchReportList = new ReportList();
//        for(ReportModel a : anything) {
//            if(category.equals(a.getCategory())){
//                searchReportList.addReport(a);
//            }
//        }
//        return  searchReportList;
//    }

}
