package ibia.app.templating;

import javafx.scene.Parent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;

import ibia.app.SceneUtil;
import ibia.core.DbDriver;
import ibia.core.entities.Conference;

public class HomeTemplate {
    public Parent fill() throws IOException {
        Parent root = SceneUtil.loadFXML("Home", true);
        ArrayList<Conference> confs = DbDriver.findAll(Conference.class, c -> c.isOngoing());
        if (confs != null && confs.size() > 0) {
            Text msg = (Text)root.lookup("#resumeMsg");
            msg.setText("Click to view ongoing conferences");
        }
        return root;
    }
}
