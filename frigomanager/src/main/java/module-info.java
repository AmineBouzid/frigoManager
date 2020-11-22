module tse.crewmatse.frigomanager {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.base;
	requires java.sql;

    opens tse.crewmatse.frigomanager to javafx.fxml;
    exports tse.crewmatse.frigomanager;
    exports tse.crewmatse.frigomanager.controller;
    opens tse.crewmatse.frigomanager.controller to javafx.fxml;
    exports tse.crewmatse.frigomanager.util;
    opens tse.crewmatse.frigomanager.util to javafx.fxml;
}