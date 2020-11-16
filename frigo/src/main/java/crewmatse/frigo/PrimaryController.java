package crewmatse.frigo;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToProfile() throws IOException {
        App.setRoot("primary");
    }
}
