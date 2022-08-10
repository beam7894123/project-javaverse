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
        FXRouter.goTo("admin");
    }

    private static void configRoute() {
        String packageStr = "ku/cs/";
        FXRouter.when("home", packageStr + "home.fxml");
        FXRouter.when("creator", packageStr + "creator.fxml");
        FXRouter.when("register",packageStr+ "register.fxml");
        FXRouter.when("signIn",packageStr+"signIn.fxml");
        FXRouter.when("creator", packageStr + "creator.fxml","Javaverse | Creator",542,759);
        FXRouter.when("register",packageStr+ "register.fxml","Javaverse | Register");
        FXRouter.when("signIn",packageStr+"signIn.fxml","Javaverse | Login");
        FXRouter.when("main",packageStr+"main.fxml","Javaverse | main",1024,746);
        FXRouter.when("ChangePassword",packageStr+"ChangePassword.fxml");
        FXRouter.when("admin",packageStr+"admin.fxml");
        FXRouter.when("AdminStaffEdit",packageStr+"AdminStaffEdit.fxml");
        FXRouter.when("AdminStaffList",packageStr+"AdminStaffList.fxml");
        FXRouter.when("AdminStudentEdit",packageStr+"AdminStudentEdit.fxml");
        FXRouter.when("AdminStudentList",packageStr+"AdminStudentList.fxml");
        FXRouter.when("AdminChangePassword",packageStr+"AdminChangePassword.fxml");

    }

    public static void main(String[] args) {
        launch();
    }
}
