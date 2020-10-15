package ibia.app.templating;

import java.io.IOException;
import ibia.app.SceneUtil;

import ibia.core.DbDriver;
import ibia.core.entities.Committee;
import ibia.core.entities.Conference;
import ibia.core.entities.Delegate;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class DelegateTemplate {
    private Scene template;
    private Parent root;
    private Delegate instance;

    public DelegateTemplate(String id) throws IllegalArgumentException, IOException {
        if (!id.startsWith("DEL")) throw new IllegalArgumentException("Invalid ID provided.");

        this.template = SceneUtil.loadFXMLScene("DelegateTemplate");
        this.root = template.getRoot();
        this.instance = DbDriver.fetchOne(Delegate.class, id);
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
        Committee com = DbDriver.fetchOne(Committee.class, instance.getCommitteeId());
        DbDriver.fetchOne(Conference.class, com.getConferenceId());
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
