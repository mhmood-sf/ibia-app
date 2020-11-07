package ibia.app.controllers;

import java.io.IOException;
import java.util.ArrayList;

import ibia.app.App;
import ibia.app.SceneUtil;
import ibia.core.DbDriver;
import ibia.core.utils.Resolution;
import ibia.core.utils.Topic;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Resolutions {
    @FXML protected VBox list;

    @FXML
    protected void initialize() throws IOException {
        String comId = App.getLocation();

        ArrayList<Topic> topics = DbDriver.findAll(Topic.class, t -> t.getCommitteeId().equals(comId));
        if (topics == null) return;
        ArrayList<Integer> topicIds = new ArrayList<>();
        for (Topic t : topics) topicIds.add(t.getId());

        ArrayList<Resolution> resos = DbDriver.findAll(Resolution.class, r -> topicIds.contains(r.getTopicId()));
        if (resos == null) return;

        for (Resolution reso : resos) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ResolutionsListItem.fxml"));
            HBox root = loader.load();
            ResolutionsListItem controller = loader.getController();
            controller.setInstanceId(reso.getId());
            controller.setRefs(list, root);

            list.getChildren().add(root);
        }
    }
    
    @FXML
    protected void newResolution() throws IOException {
        Stage stage = SceneUtil.loadPopupStage("NewResolution", "Create a new resolution");
        stage.show();
        close();
    }

    private void close() {
        Stage stage = (Stage)list.getScene().getWindow();
        stage.close();
    }
}
