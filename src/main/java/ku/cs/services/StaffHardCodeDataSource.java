package ku.cs.services;

import ku.cs.models.RegisterList;
import ku.cs.models.RegisterModel;

public class StaffHardCodeDataSource {
    private RegisterList cardList;
    public StaffHardCodeDataSource() {
        cardList = new RegisterList();
        readData();
    }
    public void readData() {
        RegisterModel john = new RegisterModel("John Smith", "1","2","3",null,null);
        RegisterModel anna = new RegisterModel("Anna Frost", "1","2","3",null,null);
        RegisterModel harry = new RegisterModel("Harry Potter", "1","2","3",null,null);
        RegisterModel joy = new RegisterModel("joy tananya", "1","2","3",null,null);
        RegisterModel kung = new RegisterModel("punpun kung","1","2","3",null,null);
        RegisterModel mint = new RegisterModel("mint sung", "1","2","3",null,null);



        cardList.addStudent(john);
        cardList.addStudent(anna);
        cardList.addStudent(harry);
        cardList.addStudent(joy);
        cardList.addStudent(kung);
        cardList.addStudent(mint);

        //นิสิตสามารถเพิ'มข้อมูลCard ได้เองให้เพิ'มอีก5 cards
        // โดยมี1 ใน5 cards กําหนดชื'อนิสิตเองและกําหนดเบอร์โทรเป็นรหัสนิสิตของนิสิต
        // และกําหนดstamp ตามที'นิสิตต้องการ
    }
    public RegisterList getCardList() {return cardList;} // return obj cardlist
}
