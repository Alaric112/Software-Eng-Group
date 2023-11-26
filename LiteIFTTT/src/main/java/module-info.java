module group2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires javafx.media;    
    
    opens group2 to javafx.fxml;
    exports group2;
}
