module group2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.base;
    
    opens group2 to javafx.fxml;
    exports group2;
}
