package ibia.app.controllers;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import ibia.core.DbDriver;
import ibia.core.Log;
import ibia.core.entities.Conference;

import ibia.app.App;
import ibia.app.SceneUtil;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class EditConference {
    @FXML TextField name;

    @FXML
    protected void update(MouseEvent event) {        
        String confName = name.getText();

        // validate form data
        if (confName.isEmpty()) {
            SceneUtil.error("The conference name is required!").show();
        }
        else if (confName.length() > 30) {
            SceneUtil.error("The conference name must be between 1 and 30 characters!\nTry using an abbreviation.").show();
        }
        else {
            try {
                String confId = App.getLocation();
                Conference conf = DbDriver.fetchOne(Conference.class, confId);
                conf.setName(confName);
                DbDriver.updateOne(conf);
                App.refresh();
                closeStage(event);
            } catch (Exception e) {
                Log.error(e.getMessage());
                e.printStackTrace();
                SceneUtil.error(e.getMessage()).show();
            }
        }
    }

    @FXML protected void cancel(MouseEvent event) {
        closeStage(event);
    }

    private void closeStage(MouseEvent event) {
        // cast source to Button so we can access window
        Button source = (Button)(event.getSource());
        // get window, cast it to Stage so we can close it
        Stage stage = (Stage)(source.getScene().getWindow());
        stage.close();
    }
}
