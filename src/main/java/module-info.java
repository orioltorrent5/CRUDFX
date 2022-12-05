module com.example.crudfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.crudfx to javafx.fxml;
    exports com.example.crudfx;
    exports com.example.crudfx.Controllers;
    opens com.example.crudfx.Controllers to javafx.fxml;
    exports com.example.crudfx.Model.Alumne;
    opens com.example.crudfx.Model.Alumne to javafx.fxml;
    exports com.example.crudfx.Model.Professor;
    opens com.example.crudfx.Model.Professor to javafx.fxml;
    //opens com.example.crudfx.Model.Professor to javafx.fxml;
}