package ku.cs.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import com.github.saacsos.FXRouter;
import ku.cs.models.RegisterList;
import ku.cs.models.RegisterModel;
import ku.cs.services.StaffHardCodeDataSource;

import java.io.IOException;

public class AdminStaffListController{
    @FXML private ListView<RegisterModel> cardsListView; //
    @FXML private Label nameLabel;
    @FXML private Label surnameLabel;
    @FXML private Label usernameLabel;
    @FXML private Label ptLabel;
    private StaffHardCodeDataSource dataSource;
    private RegisterList model;
    @FXML
    public void initialize() {
        dataSource = new StaffHardCodeDataSource(); // สร้าง obj เเพื่อใช้ดึงข้อมูล
        model = dataSource.getCardList();
        showListView();
        System.out.println(2);
//        clearSelectedMemberCard();
        handleSelectedListView();
    }
    private void showListView() {
        System.out.println(1);
        cardsListView.getItems().addAll(model.getAllCards());
        cardsListView.refresh();
//        System.out.println(cardsListView);
//        cardsListView.getItems().addAll(cardList.getAllCards()); // ได้ item ทั้งหมดจาก listview เป็นรับ obj ของ collection
//        cardsListView.refresh(); // refresh ของ listview
    }
    private void handleSelectedListView() {  // ขึ้นว่าเรากดคลิกอะไร
        System.out.println(3);
        cardsListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<RegisterModel>() {
            @Override
            public void changed(ObservableValue<? extends RegisterModel> observableValue, RegisterModel model, RegisterModel t1) {
                System.out.println(t1+"is selected");
                System.out.println(4);
                showSelectedRegister(t1);

            }
        });

    }
    public  void showSelectedRegister(RegisterModel registerModel) {
        nameLabel.setText(registerModel.getName());
        surnameLabel.setText(registerModel.getSurname());
        usernameLabel.setText(registerModel.getUsername());
        ptLabel.setText(registerModel.getTime());
    }

   @FXML public void handleBackButtonClick() {
       try {
           FXRouter.goTo("admin");
       } catch (IOException e) {
           System.err.println("ไปที่หน้าregister ไม่ได้");
           System.err.println("ให้ตรวจสอบการกําหนดroute");
       }
   }

}
