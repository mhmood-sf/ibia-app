package ibia.app.templating;

import java.io.IOException;

import ibia.app.SceneUtil;
import ibia.core.DbDriver;
import ibia.core.entities.Committee;
import ibia.core.entities.Conference;
import javafx.scene.Parent;

public class CommitteeTemplate {
    private Parent template;
    private Committee instance;

    public CommitteeTemplate(String id) throws IllegalArgumentException, IOException {
        if (!id.startsWith("COM")) throw new IllegalArgumentException("Invalid ID provided.");

        this.template = SceneUtil.loadFXML("CommitteeTemplate");
        this.instance = DbDriver.fetchOne(Committee.class, id);
    }

    public Parent fill() {
        fillBreadcrumbsNode();
        fillNameNode();
        fillIdNode();

        return template;
    }

    private void fillBreadcrumbsNode() {
        DbDriver.fetchOne(Conference.class, instance.getConferenceId());
        return;
    }

    private void fillNameNode() {
        return;
    }

    private void fillIdNode() {
        return;
    }
}
