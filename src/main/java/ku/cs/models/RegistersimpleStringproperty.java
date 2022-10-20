package ku.cs.models;

import javafx.beans.property.SimpleStringProperty;

import java.util.*;

public class RegistersimpleStringproperty {
    private SimpleStringProperty nameSimpleStringproperty;


    public RegistersimpleStringproperty(String name) {
        nameSimpleStringproperty = new SimpleStringProperty(name);
    }
    public String getNamesimpleStringproperty() {

        return nameSimpleStringproperty.get();
    }

    public void setNamesimpleStringproperty(String categoryDataName) {
        nameSimpleStringproperty = new SimpleStringProperty(categoryDataName);
    }
//    public void addData(SimpleStringProperty nameSimpleStringproperty){
//        ArrayList arrayList = new ArrayList<>();
//        arrayList.addAll(nameSimpleStringproperty);
//    }

}
