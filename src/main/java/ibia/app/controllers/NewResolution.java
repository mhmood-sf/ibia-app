package ibia.app.controllers;

import java.io.IOException;
import java.util.ArrayList;

import ibia.app.App;
import ibia.app.SceneUtil;
import ibia.core.DbDriver;
import ibia.core.entities.Delegate;
import ibia.core.utils.Resolution;
import ibia.core.utils.Topic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class NewResolution {
    @FXML protected TextField delegate;
    @FXML protected MenuButton delegateDropdown;
    @FXML protected TextField topic;
    @FXML protected MenuButton topicDropdown;

    @FXML
    protected void initialize() {
        String comId = App.getLocation();

        ArrayList<Delegate> dels = DbDriver.findAll(Delegate.class, d -> d.getCommitteeId().equals(comId));
        if (dels != null) {
            for (Delegate del : dels) {
                MenuItem item = new MenuItem(del.getDelegation());
                item.setText(del.getDelegation());
                item.setId(del.getId());
                item.setOnAction((ActionEvent event) -> {
                    delegate.setText(del.getDelegation());
                    delegate.setId(del.getId());
                });
                delegateDropdown.getItems().add(item);
            }
        }

        ArrayList<Topic> topics = DbDriver.findAll(Topic.class, t -> t.getCommitteeId().equals(comId));
        if (topics != null) {
            for (Topic t : topics) {
                MenuItem item = new MenuItem(t.getTopic());
                item.setText(t.getTopic());
                item.setId(Integer.toString(t.getId()));
                item.setOnAction((ActionEvent event) -> {
                    topic.setText(t.getTopic());
                    topic.setId(Integer.toString(t.getId()));
                });
                topicDropdown.getItems().add(item);
            }
        }


    }

    @FXML
    protected void create(MouseEvent event) throws IOException {
        SceneUtil.error("Unimplemented!");
        if (delegate.getText().isEmpty()) {
            SceneUtil.error("Please choose a delegate as the main submitter for this resolution!").show();
            return;
        }
        if (topic.getText().isEmpty()) {
            SceneUtil.error("Please choose a topic for this resolution!").show();
            return;
        }

        Resolution reso = new Resolution(delegate.getId(), Integer.parseInt(topic.getId()));
        DbDriver.insertOne(reso);
        // do this to update the resolutions list
        Stage stage = SceneUtil.loadPopupStage("Resolutions", "Resolutions");
        stage.show();
        close();
    }

    @FXML
    protected void cancel(MouseEvent event) {
        Stage stage = (Stage)delegate.getScene().getWindow();
        stage.close();
    }

    private void close() {
        Stage stage = (Stage)delegate.getScene().getWindow();
        stage.close();
    }
}
