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
        FXRouter.bind(this, stage, "Student ID", 800, 600);
        configRoute();
        FXRouter.goTo("home");
    }

    private static void configRoute() {
        String packageStr = "ku/cs/";
        FXRouter.when("home", packageStr + "home.fxml");
        FXRouter.when("creator", packageStr + "creator.fxml");
    }

    public static void main(String[] args) {
        launch();
    }
}
