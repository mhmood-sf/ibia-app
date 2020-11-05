package ibia.app.controllers;

import ibia.core.DbDriver;
import ibia.core.utils.Topic;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TopicsListItem {
    @FXML protected TextField topic;

    private Topic instance;
    private int instanceId;

    @FXML
    protected void initialize() {
        // runLater must be used to ensure
        // that the instance id has been initialized
        // from the Topics class.
        Platform.runLater(() -> {
            this.instance = DbDriver.fetchOne(Topic.class, instanceId);
            topic.setText(instance.getTopic());

            attachListener();
        });
    }

    private void attachListener() {
        topic.focusedProperty().addListener((observable, oldFocus, newFocus) -> {
            // Run code when node is out of focus
            if (!newFocus) {
                String value = topic.getText();

                if (value.isEmpty()) {
                    DbDriver.deleteById(Topic.class, instance.getId());
                    HBox container = (HBox)topic.getParent();
                    VBox list = (VBox)container.getParent();
                    list.getChildren().remove(container);
                } else {
                    instance.setTopic(value);
                    DbDriver.updateOne(instance);
                }
            }
        });
    }
    
    public void setInstanceId(int topicId) {
        this.instanceId = topicId;
    }
}
