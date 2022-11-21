package ku.cs.models;

public class Staff extends User{
    private String category;

    public Staff(String name, String surname, String username, String password,String fileNameImage, String date, String time, String category) {
        super(name, surname, username, password,fileNameImage, date, time);
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
