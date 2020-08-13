package ibia.app;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Load scenes from the fxml templates.
 */
public class Template {
    public static Scene loadConference(/*Conference con*/) throws Exception {
        Scene templateCopy = SceneUtil.loadFXMLScene("ConferenceTemplate");
        fillNameNode(templateCopy);
        String id = "";
        fillIdNode(templateCopy, id);
        fillBreadcrumbs(templateCopy);
        return templateCopy;
    }

    public static Scene loadCommittee(/*Committee com, Conference con*/) throws Exception {
        Scene templateCopy = SceneUtil.loadFXMLScene("CommitteeTemplate");
        fillNameNode(templateCopy);
        String id = "";
        fillIdNode(templateCopy, id);
        fillBreadcrumbs(templateCopy);
        return templateCopy;
    }

    public static Scene loadDelegate(/*Delegate del, Committee com, Conference con*/) throws Exception {
        Scene templateCopy = SceneUtil.loadFXMLScene("DelegateTemplate");
        fillNameNode(templateCopy);
        String id = "";
        fillIdNode(templateCopy, id);
        fillBreadcrumbs(templateCopy);
        return templateCopy;
    }

    private static HBox getBreadcrumbs(Scene template) {
        VBox container = (VBox)template.getRoot();
        return (HBox)container.getChildren().get(0);
    }

    private static void fillBreadcrumbs(Scene template) {
        getBreadcrumbs(template);
        return;
    }

    private static Text getNameNode(Scene template) {
        return null;
    }

    private static void fillNameNode(Scene template) {
        getNameNode(template);
        return;
    }

    private static Text getIdNode(Scene template) {
        return null;
    }

    private static void fillIdNode(Scene template, String id) {
        getIdNode(template);
        return;
    }
}
