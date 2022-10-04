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
        image2.setImage(new Image(url2));
        nameLabel2.setText("Mahatthana Intaraklom");
        nicknameLabel2.setText("Euro");
        idLabel2.setText("6210450687");

        image.setImage(new Image(url));
        nameLabel.setText("Tananya Piyaleartluck");
        nicknameLabel.setText("Joy");
        idLabel.setText("6210451217");

        image3.setImage(new Image(url3));
        nameLabel3.setText("Chitipat Changjeraja");
        nicknameLabel3.setText("Boss");
        idLabel3.setText("6210451136");

        image4.setImage(new Image(url4));
        nameLabel4.setText("Susit Tamamoon");
        nicknameLabel4.setText("Beam");
        idLabel4.setText("6410451504");
    }
    String url2 = getClass().getResource("/images/euro.jpg").toExternalForm();
    String url = getClass().getResource("/images/joy.jpg").toExternalForm();

    String url3 = getClass().getResource("/images/boss.jpg").toExternalForm();
    String url4 = getClass().getResource("/images/beam.jpg").toExternalForm();
    @FXML
    public void handleBackButton(ActionEvent actionEvent) {try
    {
        com.github.saacsos.FXRouter.goTo("signIn");}
    catch (IOException e) {
        System.err.println("ไปที่หน้าhome ไม่ได้");System.err.println("ให้ตรวจสอบการกําหนดroute");}
    }

}
