package ku.cs.services;

import ku.cs.models.RegisterList;

public interface DataSource <T>{
    RegisterList readData();
    void writeData(RegisterList write);
}
