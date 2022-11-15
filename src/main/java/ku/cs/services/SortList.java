package ku.cs.services;

import ku.cs.models.ReportModel;
import ku.cs.models.User;

import java.util.Comparator;

public class SortList {//รวมSorter อื่นๆ รวมในนี้ได้

//    //Template สำหรับ Sorter
//    public static Comparator<ชื่อModels> ชื่อตัวแปร(){
//        Comparator<ชื่อModels> ชื่อตัวแปรlocal = new Comparator<ชื่อModels>(){
//            @Override
//            public int compare(AdminModels o1, AdminModels o2){ return ตัวที่จะcompare }
//        };
//        return ชื่อตัวแปรlocal;
//    }

    public static Comparator<User> ascendingDateTime(){ //เรียงเวลา+วัน ของหน้า Admin
        Comparator<User> ascendingDateTimeComparator = new Comparator<User>(){
            @Override
            public int compare(User o1, User o2){ return o2.getDateTime().compareTo(o1.getDateTime());}
        };
        return ascendingDateTimeComparator;
    }

    //บอกแล้วให้รวม -w-
    public static Comparator<ReportModel> ascendingReporDateTime(){ //เรียงเวลา
        Comparator<ReportModel> ascendingReporDateTime = new Comparator<ReportModel>(){
            @Override
            public int compare(ReportModel o1, ReportModel o2){ return o2.getRealDateTime().compareTo(o1.getRealDateTime());}
        };
        return ascendingReporDateTime;
    }

    public static Comparator<ReportModel> descendingReportDateTime(){ //เรียงเวลา
        Comparator<ReportModel> descendingReportDateTime = new Comparator<ReportModel>(){
            @Override
            public int compare(ReportModel o1, ReportModel o2){ return o1.getRealDateTime().compareTo(o2.getRealDateTime());}
        };
        return descendingReportDateTime;
    }
}
