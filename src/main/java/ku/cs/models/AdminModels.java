package ku.cs.models;

public class AdminModels extends RegisterModel{

    private String ban;

    public AdminModels(String name, String surname, String username, String password, String confirmPassword, String date, String time, String fileName, String ban) {
        super(name, surname, username, password, confirmPassword, date, time, fileName);
        this.ban = ban;
    }

    public AdminModels(String name, String surname, String username, String password, String date, String time, String ban) {
        super(name, surname, username, password, date, time);
        this.ban = ban;
    }

    public String getBan() {
        return ban;
    }

    public void setBan(String ban) {
        this.ban = ban;
    }


    @Override
    public String toString() {
        return "RegisterModel{" +
                "name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                ", username='" + getUsername() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", Date ='" + getTime() + '\'' +
                ", time='" + getImage() + '\'' +
                '}';
    }


}
