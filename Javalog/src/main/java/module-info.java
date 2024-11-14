module com.example.javalog {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.javalog to javafx.fxml;
    exports com.example.javalog;
}