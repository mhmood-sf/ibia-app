package ibia.app.templating;

import java.io.IOException;
import ibia.app.SceneUtil;

import ibia.core.DbDriver;
import ibia.core.entities.Conference;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ConferenceTemplate {
    private String id;
    private Scene template;
    private Parent root;
    private Conference instance;

    public ConferenceTemplate(String id) throws IllegalArgumentException, IOException {
        if (!id.startsWith("CON")) throw new IllegalArgumentException("Invalid ID provided.");

        this.id = id;
        this.template = SceneUtil.loadFXMLScene("ConferenceTemplate");
        this.root = template.getRoot();
        this.instance = DbDriver.fetchOne(Conference.class, id);
    }

    public Scene fill() {
        setBreadcrumbsNode();
        setNameNode();
        setIdNode();

        return template;
    }

    private HBox getBreadcrumbs() {
        VBox container = (VBox)template.getRoot();
        return (HBox)container.getChildren().get(0);
    }

    private void setBreadcrumbsNode() {
        getBreadcrumbs();
        return;
    }

    private Text getNameNode() {
        return null;
    }

    private void setNameNode() {
        getNameNode();
        return;
    }

    private Text getIdNode() {
        return null;
    }

    private void setIdNode() {
        getIdNode();
        return;
    }
}
