package ibia.app.controllers;

import ibia.core.DbDriver;
import ibia.core.entities.Delegate;
import ibia.core.utils.Resolution;
import ibia.core.utils.Topic;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ResolutionsListItem {
    @FXML protected Text delegate;
    @FXML protected Text topic;

    private Resolution instance;
    private int instanceId;
    private VBox listRef;
    private HBox itemRef;

    @FXML
    protected void initialize() {
        Platform.runLater(() -> {
            this.instance = DbDriver.fetchOne(Resolution.class, instanceId);
            Delegate del = DbDriver.fetchOne(Delegate.class, instance.getMainSubmitter());
            delegate.setText(del.getDelegation());

            Topic t = DbDriver.fetchOne(Topic.class, instance.getTopicId());
            topic.setText(t.getTopic());
        });
    }

    @FXML
    protected void deleteReso(MouseEvent event) {
        DbDriver.deleteById(Resolution.class, instanceId);
        listRef.getChildren().remove(itemRef);
    }

    public void setInstanceId(int id) {
        this.instanceId = id;
    }

    // Get reference to the parent popup's VBox list
    // and this item's node, so that it can be
    // deleted directly from here when the Delete
    // button is pressed
    public void setRefs(VBox list, HBox item) {
        this.listRef = list;
        this.itemRef = item;
    }
}
