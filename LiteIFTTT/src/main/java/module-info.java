module group2 {
    exports group2.Controller;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires javafx.media;    
    
    opens group2 to javafx.fxml;
    opens group2.Controller to javafx.fxml;
    opens group2.Model.Rule to javafx.base;
    opens group2.Model.Action to javafx.base;
    opens group2.Model.Trigger to javafx.base;

    exports group2;
}
