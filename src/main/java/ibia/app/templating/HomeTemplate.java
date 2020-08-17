package ibia.app.templating;

import javafx.scene.Scene;
import java.io.IOException;

import ibia.app.SceneUtil;

public class HomeTemplate {
    public Scene fill() throws IOException {
        return SceneUtil.loadFXMLScene("Home");
    }
}
