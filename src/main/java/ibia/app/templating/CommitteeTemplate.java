package ibia.app.templating;

import java.io.IOException;

import ibia.app.SceneUtil;
import ibia.core.DbDriver;
import ibia.core.entities.Committee;
import ibia.core.entities.Conference;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CommitteeTemplate {
    private Scene template;
    private Parent root;
    private Committee instance;

    public CommitteeTemplate(String id) throws IllegalArgumentException, IOException {
        if (!id.startsWith("COM")) throw new IllegalArgumentException("Invalid ID provided.");

        this.template = SceneUtil.loadFXMLScene("CommitteeTemplate");
        this.root = template.getRoot();
        this.instance = DbDriver.fetchOne(Committee.class, id);
    }

    public Scene fill() {
        fillBreadcrumbsNode();
        fillNameNode();
        fillIdNode();

        return template;
    }

    private HBox getBreadcrumbsNode() {
        VBox container = (VBox)template.getRoot();
        return (HBox)container.getChildren().get(0);
    }

    private void fillBreadcrumbsNode() {
        getBreadcrumbsNode();
        DbDriver.fetchOne(Conference.class, instance.getConferenceId());
        return;
    }

    private Text getNameNode() {
        return null;
    }

    private void fillNameNode() {
        getNameNode();
        return;
    }

    private Text getIdNode() {
        return null;
    }

    private void fillIdNode() {
        getIdNode();
        return;
    }
}
