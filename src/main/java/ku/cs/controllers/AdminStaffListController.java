package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.github.saacsos.FXRouter;
import java.io.IOException;

public class AdminStaffListController {
    @FXML public void handleBackButtonClick(ActionEvent actionEvent){
        try {
           FXRouter.goTo("admin");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า admin.fxml ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }

    // image //////////////////////////////////////////////////////////////////////////////
    @FXML
    private ImageView image;
    String url = getClass().getResource("/images/default1.png").toExternalForm();
    @FXML public void initialize(){
        image.setImage(new Image(url));
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private Label nameLable, surnameLable, usernameLable, lastloginLable, departmentLable;
}
