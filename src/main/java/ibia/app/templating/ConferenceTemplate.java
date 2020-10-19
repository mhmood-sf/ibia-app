package ibia.app.templating;

import java.io.IOException;
import ibia.app.SceneUtil;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

import ibia.core.Client;
import ibia.core.DbDriver;
import ibia.core.entities.Committee;
import ibia.core.entities.Conference;
import ibia.core.entities.Delegate;
import ibia.core.utils.Resolution;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ConferenceTemplate {
    private Parent template;
    private Conference instance;

    public ConferenceTemplate(String id) throws IllegalArgumentException, IOException {
        if (!id.startsWith("CON")) throw new IllegalArgumentException("Invalid ID provided.");

        this.template = SceneUtil.loadFXML("ConferenceTemplate", true);
        this.instance = DbDriver.fetchOne(Conference.class, id);
    }

    public Parent fill() throws IOException {
        fillBreadcrumbsNode();
        fillNameNode();
        fillIdNode();
        fillDetails();
        fillStatusButton();
        fillCommitteesList();

        return template;
    }

    private void fillBreadcrumbsNode() {
        Text crumb = (Text)template.lookup("#conferenceCrumb");
        crumb.setText(instance.getName());
    }

    private void fillNameNode() {
        Text name = (Text)template.lookup("#name");
        name.setText(instance.getName());
    }

    private void fillIdNode() {
        Text id = (Text)template.lookup("#id");
        id.setText(instance.getId());
    }

    private void fillDetails() {
        // Sets OPENED date
        Text created = (Text)template.lookup("#created");
        Date date = instance.getCreated();
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        created.setText(fmt.format(date));

        // Sets status to ONGOING or FINISHED
        Text status = (Text)template.lookup("#status");
        String currentStatus = instance.isOngoing() ? "ONGOING" : "FINISHED";
        status.setText(currentStatus);

        // Sets number of committees
        Text committees = (Text)template.lookup("#committees");
        ArrayList<Committee> coms = Client.getConferenceCommittees(instance.getId());
        int i = coms != null ? coms.size() : 0;
        committees.setText(Integer.toString(i));

        // Sets number of delegates
        Text delegates = (Text)template.lookup("#delegates");
        ArrayList<Delegate> dels = new ArrayList<>();
        if (coms != null) {
            for (Committee com : coms) {
                ArrayList<Delegate> fetched = Client.getCommitteeDelegates(com.getId());
                if (fetched != null) {
                    dels.addAll(fetched);
                }
            }
        }
        delegates.setText(Integer.toString(dels.size()));

        // Sets number of resolutions
        Text resolutions = (Text)template.lookup("#resolutions");
        ArrayList<Resolution> resos = DbDriver.findAll(Resolution.class, r -> r.getPassed());
        int totalResos = 0;
        if (resos != null) {
            for (Resolution reso : resos) {
                for (Delegate del : dels) {
                    if (del.getId().equals(reso.getMainSubmitter())) {
                        totalResos += 1;
                    }
                }
            }
        }
        resolutions.setText(Integer.toString(totalResos));
    }

    private void fillStatusButton() {
        Button btn = (Button)template.lookup("#statusButton");
        if (instance.isOngoing()) {
            btn.setText("Finish Conference");
        } else {
            btn.setText("Re-open Conference");
        }
    }

    private void fillCommitteesList() throws IOException {
        ArrayList<Committee> coms = DbDriver.findAll(Committee.class, c -> c.getConferenceId().equals(instance.getId()));
        if (coms == null) return;
        VBox vbox = (VBox)template.lookup("#committeesList");
        ObservableList<Node> list = vbox.getChildren();
        for (Committee com : coms) {
            Scene item = SceneUtil.loadFXMLScene("CommitteeListItem", false);
            HBox hbox = (HBox)item.getRoot();
            Text name = (Text)hbox.getChildren().get(0);
            name.setText(com.getName() + "#" + com.getId());
            list.add(name);
        }
    }
}
