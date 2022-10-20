package ku.cs.services;

import ku.cs.models.User;

import java.util.Comparator;

public class SortList {//รวมSorter อื่นๆ รวมในนี้ได้

    public static Comparator<User> ascendingDateTime(){ //เรียงเวลา+วัน ของหน้า Admin
        Comparator<User> ascendingDateTimeComparator = new Comparator<User>(){
            @Override
            public int compare(User o1, User o2){ return o2.getDateTime().compareTo(o1.getDateTime());}
        };
        return ascendingDateTimeComparator;
    }
}
