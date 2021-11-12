module com.mycompany.mavenproject1 {
    requires java.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires chesslib;
    
    opens com.mycompany.mavenproject1 to javafx.fxml;
    exports com.mycompany.mavenproject1;
    
}
