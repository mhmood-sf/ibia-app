package ibia.app.templating;

import javafx.scene.Parent;
import java.io.IOException;

import ibia.app.SceneUtil;

public class HomeTemplate {
    public Parent fill() throws IOException {
        return SceneUtil.loadFXML("Home");
    }
}
