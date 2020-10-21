package ibia.app.controllers;

import java.io.IOException;

import ibia.app.App;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ConferenceListItem {
    @FXML protected HBox container;
    @FXML protected Text id;

    @FXML
    protected void hoverItemEffectOn(MouseEvent event) {
        BackgroundFill bgFill = new BackgroundFill(Paint.valueOf("#363648"), null, null);
        Background bg = new Background(bgFill);
        container.setBackground(bg);
    }

    @FXML
    protected void hoverItemEffectOff(MouseEvent event) {
        BackgroundFill bgFill = new BackgroundFill(null, null, null);
        Background bg = new Background(bgFill);
        container.setBackground(bg);
    }

    @FXML
    protected void navigate(MouseEvent event) throws IOException, IllegalArgumentException {
        String confId = id.getText().substring(1);
        App.navigate(confId);
        Stage stage = (Stage)container.getScene().getWindow();
        stage.close();
    }
}
