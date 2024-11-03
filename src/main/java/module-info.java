module org.example.omooo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;

    opens org.example.LogInSystem.Controllers to javafx.fxml;
    opens org.example.LogInSystem to javafx.fxml;
    exports org.example.LogInSystem;
}