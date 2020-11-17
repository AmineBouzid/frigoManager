module tse.crewmatse.frigomanager {
    requires javafx.controls;
    requires javafx.fxml;

    opens tse.crewmatse.frigomanager to javafx.fxml;
    exports tse.crewmatse.frigomanager;
    exports tse.crewmatse.frigomanager.controller;
    opens tse.crewmatse.frigomanager.controller to javafx.fxml;
}