package ku.cs.models;

import javafx.fxml.FXML;

public class RegisterModel {
    private String name;
    private String surname;
    private String username;
    private String password;
    private String confirmPassword;
    private String image;




    public RegisterModel(String name,String surname,String username,String password,String confirmPassword,String fileName){
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.image =  fileName;
    }
    public RegisterModel(String name,String surname,String username,String password){
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.image =getClass().getResource("/ku/cs/images/default1.png").toExternalForm();
        System.out.println(image);
    }
    public RegisterModel(String name,String surname,String username,String password,String fileName){
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.image = fileName;
        System.out.println(image);
    }
    public RegisterModel(String username,String password){
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "RegisterModel{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
