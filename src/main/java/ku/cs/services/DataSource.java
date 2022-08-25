package ku.cs.services;

import ku.cs.models.RegisterList;
import ku.cs.models.ReportList;

public interface DataSource <T>{

    RegisterList readData();
    ReportList readData1();
    void writeData(RegisterList write);
    void writeData1(ReportList write);
}
