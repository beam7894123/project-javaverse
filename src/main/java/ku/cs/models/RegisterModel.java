package ku.cs.models;

public class RegisterModel {
    private String name;
    private String sername;
    private String username;
    private String password;
    private String confirmPassword;
    private String image;


    public RegisterModel(String name,String sername,String username,String password,String confirmPassword){
        this(username,password);
        this.name = name;
        this.sername = sername;
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
    public RegisterModel(String name,String sername,String username,String password,String confirmPassword,String image){
        this(name,sername,username,password,confirmPassword);
        this.image = image;
    }
    public RegisterModel(String username,String password){
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSername() {
        return sername;
    }

    public void setSername(String sername) {
        this.sername = sername;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
