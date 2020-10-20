package ibia.app.controllers;

import java.io.IOException;

import ibia.app.App;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class CommitteeView {
    /**
     * Navigate back to home screen.
     */
    @FXML
    protected void navigateHome(MouseEvent event) throws IOException, IllegalArgumentException {
        App.navigate("Home");
    }
}
