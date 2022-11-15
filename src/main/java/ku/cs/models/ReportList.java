package ku.cs.models;

import java.util.ArrayList;

public class ReportList extends ReportModel{
    private ArrayList<ReportModel> anything;

    public ReportList() {
        super();
        anything = new ArrayList<>();
    }

    public void addReport(ReportModel report){
        anything.add(report);
    }

    public ArrayList<ReportModel> getAnything(){
        return anything;
    }
    public ReportList findMyThing(String writer){ //use to polymorphism
        ReportList searchReportList = new ReportList();
        for(ReportModel a : anything) {
            if(writer.equals(a.getAuthorName())){
                searchReportList.addReport(a);
            }
        }
        return  searchReportList;
    }

    public ReportList findMyCategory(String category){
        ReportList searchReportList = new ReportList();
        for(ReportModel a : anything) {
            if(category.equals(a.getCategory())){
                searchReportList.addReport(a);
            }
        }
        return  searchReportList;
    }

}
