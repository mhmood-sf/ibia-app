package ibia.app.controllers;

import java.io.IOException;

import ibia.app.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ConferenceView {
    @FXML protected Button newCommittee;
    @FXML protected Button statusButton;
    @FXML protected Button edit;
    @FXML protected Button delete;
    @FXML protected VBox committeesList;

    /**
     * Hover effect for breadcrumbs
     */
    @FXML
    protected void crumbHoverEffectOn(MouseEvent event) {
        Text text = (Text)event.getTarget();
        text.setUnderline(true);
    }

    /**
     * Hover effect for breadcrumbs
     */
    @FXML
    protected void crumbHoverEffectOff(MouseEvent event) {
        Text text = (Text)event.getTarget();
        text.setUnderline(false);
    }

    @FXML
    protected void navigateHome(MouseEvent event) throws IOException, IllegalArgumentException {
        App.navigate("Home");
    }
}
