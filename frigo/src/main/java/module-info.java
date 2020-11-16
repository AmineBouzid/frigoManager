module crewmatse.frigo {
    requires javafx.controls;
    requires javafx.fxml;

    opens crewmatse.frigo to javafx.fxml;
    exports crewmatse.frigo;
}