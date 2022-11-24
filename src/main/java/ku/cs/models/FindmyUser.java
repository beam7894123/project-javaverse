package ku.cs.models;

import java.util.ArrayList;

public class FindmyUser implements Findable<User>{


    @Override
    public String getAnything(User anything) {
        return anything.getUsername();
    }
}
