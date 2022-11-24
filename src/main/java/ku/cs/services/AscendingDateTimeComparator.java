package ku.cs.services;

import ku.cs.models.User;

import java.util.Comparator;

public class AscendingDateTimeComparator implements Comparator<User> {
    @Override
    public int compare(User o1, User o2){
        return o2.getDateTime().compareTo(o1.getDateTime());}
}
