package ku.cs.models;
import ku.cs.models.RegisterModel;

public class AdminModels extends RegisterModel{
    @Override
    public String toString() {
        return getName() + " " + getSurname();
    }


}
