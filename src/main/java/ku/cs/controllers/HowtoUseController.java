package ku.cs.controllers;

import javafx.event.ActionEvent;
import com.github.saacsos.FXRouter;
import javafx.fxml.FXML;

import java.io.IOException;

public class HowtoUseController {
    @FXML
    public void handleBackButton(ActionEvent actionEvent){
        try {
            FXRouter.setAnimationType("fade");
            FXRouter.goTo("main");
        } catch (IOException e) {
            System.err.println("ไปที่หน้าhome ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนดroute");
        }
    }
}
