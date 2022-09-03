package ku.cs.services;

import ku.cs.models.RegisterList;
import ku.cs.models.ReportList;

public interface DataSource <T>{

    T readData();
    void writeData(T t);
}
