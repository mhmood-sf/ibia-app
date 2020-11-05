package ibia.app.controllers;

import java.io.IOException;
import java.util.ArrayList;

import ibia.app.App;
import ibia.core.DbDriver;
import ibia.core.utils.Topic;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class Topics {
    @FXML protected VBox list;

    @FXML
    protected void initialize() throws IOException {
        String comId = App.getLocation();
        ArrayList<Topic> topics = DbDriver.findAll(Topic.class, t -> t.getCommitteeId().equals(comId));
        if (topics == null) return;

        for (Topic topic : topics) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TopicsListItem.fxml"));
            Parent root = loader.load();
            TopicsListItem controller = loader.getController();
            controller.setInstanceId(topic.getId());
            list.getChildren().add(root);
        }
    }

    @FXML
    protected void newTopic(MouseEvent event) throws IOException {
        Topic topic = new Topic(App.getLocation(), "");
        DbDriver.insertOne(topic);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TopicsListItem.fxml"));
        Parent root = loader.load();
        TopicsListItem controller = loader.getController();
        controller.setInstanceId(topic.getId());
        list.getChildren().add(root);
    }
}
