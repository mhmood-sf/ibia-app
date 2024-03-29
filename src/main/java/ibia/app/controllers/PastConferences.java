package ibia.app.controllers;

import java.io.IOException;
import java.util.ArrayList;

import ibia.app.SceneUtil;
import ibia.core.DbDriver;
import ibia.core.entities.Conference;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PastConferences  {
    @FXML protected VBox pastList;

    @FXML
    public void initialize() throws IOException {
        ArrayList<Conference> confs = DbDriver.findAll(Conference.class, c -> !c.isOngoing());
        if (confs != null) {
            for (Conference conf : confs) {
                HBox item = (HBox)SceneUtil.loadFXML("ConferenceListItem", false);
                Text name = (Text)item.lookup("#name");
                name.setText(conf.getName());
                Text id = (Text)item.lookup("#id");
                id.setText("#" + conf.getId());
                pastList.getChildren().add(item);
            }
        }
    }
}
