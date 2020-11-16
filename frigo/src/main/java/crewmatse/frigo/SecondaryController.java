package crewmatse.frigo;

import java.io.IOException;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToHome() throws IOException {
        App.setRoot("secondary");
    }
}