package ibia.app.templating;

import java.io.IOException;
import ibia.app.SceneUtil;

import ibia.core.DbDriver;
import ibia.core.entities.Committee;
import ibia.core.entities.Conference;
import ibia.core.entities.Delegate;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class DelegateTemplate {
    private String id;
    private Scene template;
    private Delegate instance;

    public DelegateTemplate(String id) throws IllegalArgumentException, IOException {
        if (!id.startsWith("DEL")) throw new IllegalArgumentException("Invalid ID provided.");

        this.id = id;
        this.template = SceneUtil.loadFXMLScene("DelegateTemplate");
        this.instance = DbDriver.fetchOne(Delegate.class, id);
    }

    public Scene fill() {
        setBreadcrumbsNode();
        setNameNode();
        setIdNode();

        return template;
    }

    private HBox getBreadcrumbsNode() {
        VBox container = (VBox)template.getRoot();
        return (HBox)container.getChildren().get(0);
    }

    private void setBreadcrumbsNode() {
        getBreadcrumbsNode();
        Committee com = DbDriver.fetchOne(Committee.class, instance.getCommitteeId());
        DbDriver.fetchOne(Conference.class, com.getConferenceId());
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
