module cs.ku {
    requires javafx.controls;
    requires javafx.fxml;
    opens ku.cs to javafx.fxml;
    exports ku.cs;
    exports ku.cs.controllers;
    opens ku.cs.controllers to javafx.fxml, javafx.base;
    opens ku.cs.models to javafx.base;
    opens ku.cs.services to javafx.base;
}