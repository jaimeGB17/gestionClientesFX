module application.gestionclientesfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;

    opens application.gestionclientesfx to javafx.fxml;
    exports application.gestionclientesfx;
    exports application.controller;
    opens application.controller to javafx.fxml;

}