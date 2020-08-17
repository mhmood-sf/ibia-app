package ibia.app.templating;

import ibia.core.entities.Committee;
import ibia.core.entities.Conference;
import ibia.core.entities.Delegate;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ConferenceTemplate {
    public ConferenceTemplate(String id) throws IllegalArgumentException {
        if (!id.startsWith("CON")) throw new IllegalArgumentException("Invalid ID provided.");
    }

    public Scene fill() {
        return null;
    }

    private static HBox getBreadcrumbs(Scene template) {
        VBox container = (VBox)template.getRoot();
        return (HBox)container.getChildren().get(0);
    }

    private void fillBreadcrumbs(Scene template, Delegate del, Committee com, Conference con) {
        getBreadcrumbs(template);
        return;
    }

    private void fillBreadcrumbs(Scene template, Committee com, Conference con) {
        getBreadcrumbs(template);
        return;
    }

    private void fillBreadcrumbs(Scene template, Conference con) {
        getBreadcrumbs(template);
        return;
    }

    private Text getNameNode(Scene template) {
        return null;
    }

    private void fillNameNode(Scene template) {
        getNameNode(template);
        return;
    }

    private Text getIdNode(Scene template) {
        return null;
    }

    private void fillIdNode(Scene template, String id) {
        getIdNode(template);
        return;
    }
}
