package ku.cs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import com.github.saacsos.FXRouter;

public class ProjectApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXRouter.bind(this, stage);
        configRoute();
        FXRouter.goTo("signIn");
    }

    private static void configRoute() {
        String packageStr = "ku/cs/";
        FXRouter.when("home", packageStr + "home.fxml");
        FXRouter.when("creator", packageStr + "creator.fxml","Javaverse | Creator",542,759);
        FXRouter.when("register",packageStr+ "register.fxml","Javaverse | Register");
        FXRouter.when("signIn",packageStr+"signIn.fxml","Javaverse | Login");
        FXRouter.when("main",packageStr+"main.fxml","Javaverse | main",1024,746);
        FXRouter.when("addreport",packageStr+"report_form.fxml","Javavaerse | Report",800,670);
        FXRouter.when("profile",packageStr+"profile.fxml","Javaverse | Profile");

        //ADMIN
        FXRouter.when("admin",packageStr+"admin.fxml","Javaverse | ADMIN ONLY | Main");
        FXRouter.when("AdminStaffEdit",packageStr+"AdminStaffEdit.fxml","Javaverse | ADMIN ONLY | Edit Staff");
        FXRouter.when("AdminStaffList",packageStr+"AdminStaffList.fxml","Javaverse | ADMIN ONLY | Staff List");
        FXRouter.when("AdminStudentEdit",packageStr+"AdminStudentEdit.fxml","Javaverse | ADMIN ONLY | Edit Student");
        FXRouter.when("AdminStudentList",packageStr+"AdminStudentList.fxml", "Javaverse | ADMIN ONLY | Student List");
        FXRouter.when("AdminChangePassword",packageStr+"AdminChangePassword.fxml", "Javaverse | ADMIN ONLY | Change Password");

    }

    public static void main(String[] args) {
        launch();
    }
}
