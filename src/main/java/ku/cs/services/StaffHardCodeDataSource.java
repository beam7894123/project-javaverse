package ku.cs.services;

import javafx.scene.control.TableView;
import ku.cs.models.RegisterList;
import ku.cs.models.RegisterModel;
import ku.cs.models.RegistersimpleStringproperty;

public class StaffHardCodeDataSource {
    private RegisterList cardList;
    private RegistersimpleStringproperty tableView;
    public StaffHardCodeDataSource() {
        cardList = new RegisterList();
        readData(tableView);
    }
    public void readData(RegistersimpleStringproperty tableView) {
//        RegisterModel john = new RegisterModel("John Smith", "1","2","3",null,null);
//        RegisterModel anna = new RegisterModel("Anna Frost", "1","2","3",null,null);
//        RegisterModel harry = new RegisterModel("Harry Potter", "1","2","3",null,null);
//        RegisterModel joy = new RegisterModel("joy tananya", "1","2","3",null,null);
//        RegisterModel kung = new RegisterModel("punpun kung","1","2","3",null,null);
//        RegisterModel mint = new RegisterModel("mint sung", "1","2","3",null,null);



//        cardList.addStudent(john);
//        cardList.addStudent(anna);
//        cardList.addStudent(harry);
//        cardList.addStudent(joy);
//        cardList.addStudent(kung);
//        cardList.addStudent(mint);

//        tableView.

    }
    public RegisterList getCardList() {return cardList;} // return obj cardlist

    @Override
    public String toString() {
        return "StaffHardCodeDataSource{" +
                "cardList=" + cardList +
                '}';
    }

}
