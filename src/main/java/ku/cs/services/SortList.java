package ku.cs.services;

import ku.cs.models.AdminModels;
import ku.cs.models.RegisterModel;

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

    public static Comparator<RegisterModel> ascendingDateTime(){ //เรียงเวลา+วัน ของหน้า Admin
        Comparator<RegisterModel> ascendingDateTimeComparator = new Comparator<RegisterModel>(){
            @Override
            public int compare(RegisterModel o1, RegisterModel o2){ return o2.getDateTime().compareTo(o1.getDateTime());}
        };
        return ascendingDateTimeComparator;
    }
}
