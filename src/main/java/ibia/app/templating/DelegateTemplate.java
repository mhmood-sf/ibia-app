package ibia.app.templating;

import java.io.IOException;
import ibia.app.SceneUtil;

import ibia.core.DbDriver;
import ibia.core.entities.Committee;
import ibia.core.entities.Conference;
import ibia.core.entities.Delegate;
import javafx.scene.Parent;

public class DelegateTemplate {
    private Parent template;
    private Delegate instance;

    public DelegateTemplate(String id) throws IllegalArgumentException, IOException {
        if (!id.startsWith("DEL")) throw new IllegalArgumentException("Invalid ID provided.");

        this.template = SceneUtil.loadFXML("DelegateTemplate");
        this.instance = DbDriver.fetchOne(Delegate.class, id);
    }

    public Parent fill() {
        fillBreadcrumbsNode();
        fillNameNode();
        fillIdNode();

        return template;
    }

    private void fillBreadcrumbsNode() {
        Committee com = DbDriver.fetchOne(Committee.class, instance.getCommitteeId());
        DbDriver.fetchOne(Conference.class, com.getConferenceId());
        return;
    }

    private void fillNameNode() {
        return;
    }

    private void fillIdNode() {
        return;
    }
}
