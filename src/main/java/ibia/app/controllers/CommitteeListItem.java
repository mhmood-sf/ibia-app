package ibia.app.controllers;

import java.io.IOException;

import ibia.app.App;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class CommitteeListItem {
    @FXML protected Text committee;

    @FXML
    protected void navigate(MouseEvent event) throws IOException, IllegalArgumentException {
        String str = committee.getText();
        String id = str.split("#")[1];
        App.navigate(id);
    }

    /**
     * Hover effect
     */
    @FXML
    protected void hoverEffectOn(MouseEvent event) {
        Text text = (Text)event.getTarget();
        text.setUnderline(true);
    }

    /**
     * Hover effect
     */
    @FXML
    protected void hoverEffectOff(MouseEvent event) {
        Text text = (Text)event.getTarget();
        text.setUnderline(false);
    }
}
