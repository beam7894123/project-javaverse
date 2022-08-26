package ku.cs.models;

public class AdminModels {

    private String username = "Admin";
    private String password;
    private String Inputpassword;
    private String Newpassword;

    public AdminModels(String username, String password, String Inputpassword, String Newpassword){
        this.username = username;
        this.password = password;
        this.Inputpassword = Inputpassword;
        this.Newpassword = Newpassword;

    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

//    public class SetPassword{
//        if(password = password){
//            return 0;
//        }
//        else{
//            return 1;
//        }
//
//    }

}
