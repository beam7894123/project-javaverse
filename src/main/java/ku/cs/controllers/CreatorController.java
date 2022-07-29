package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class CreatorController {
    @FXML
    private ImageView image;
    @FXML
    private ImageView image2;
    @FXML
    private ImageView image3;
    @FXML
    private ImageView image4;
    @FXML private Label nameLabel;
    @FXML private Label nameLabel2;
    @FXML private Label nameLabel3;
    @FXML private Label nameLabel4;
    @FXML private Label nicknameLabel;
    @FXML private Label nicknameLabel2;
    @FXML private Label nicknameLabel3;
    @FXML private Label nicknameLabel4;
    @FXML private Label idLabel;
    @FXML private Label idLabel2;
    @FXML private Label idLabel3;
    @FXML private Label idLabel4;

    @FXML
    public void initialize()
    {
        System.out.println("initialize Creator");
        image2.setImage(new Image(url2));
        nameLabel2.setText("Mahatthana Intaraklom");
        nicknameLabel2.setText("Euro");
        idLabel2.setText("6210450687");
    }
    String url2 = getClass().getResource("/images/euro.jpg").toExternalForm();
    @FXML
    public void handleBackButton(ActionEvent actionEvent) {try
    {
        com.github.saacsos.FXRouter.goTo("home");}
    catch (IOException e) {
        System.err.println("ไปที7หน้าhome ไม่ได้");System.err.println("ให้ตรวจสอบการกําหนดroute");}
    }
}
