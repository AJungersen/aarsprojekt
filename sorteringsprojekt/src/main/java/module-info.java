module com.mycompany.mavenproject1 {
    requires java.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires chesslib;
    requires org.json;
    requires json.simple;
    opens com.mycompany.mavenproject1 to javafx.fxml;
    exports com.mycompany.mavenproject1;
    

}
