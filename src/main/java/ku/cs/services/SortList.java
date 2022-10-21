package ku.cs.services;

import ku.cs.models.User;

import java.util.Comparator;

public class SortList {
    public static Comparator<User> ascendingDateTime(){
        Comparator<User> ascendingDateTimeComparator = new Comparator<User>(){
            @Override
            public int compare(User o1, User o2){ return o2.getDateTime().compareTo(o1.getDateTime());}
        };
        return ascendingDateTimeComparator;
    }
}
