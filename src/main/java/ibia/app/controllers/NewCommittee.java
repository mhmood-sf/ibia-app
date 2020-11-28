package ibia.app.controllers;

import ibia.app.App;
import ibia.app.SceneUtil;
import ibia.core.Client;
import ibia.core.Log;
import ibia.core.entities.Committee;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class NewCommittee {
    @FXML protected TextField name;

    @FXML
    protected void handleCreateAction(MouseEvent event) {        
        String comName = name.getText();

        // validate data
        if (comName.isEmpty()) {
            SceneUtil.error("The committee name is required!").show();
        }
        else if (comName.length() > 30) {
            SceneUtil.error("The committee name must be between 1 and 30 characters!\nTry using an abbreviation.").show();
        }
        else {
            try {
                Committee com = Client.addNewCommittee(comName, App.getLocation());
                App.navigate(com.getId());
                closeStage(event);
            } catch (Exception e) {
                Log.error(e.getMessage());
                e.printStackTrace();
                SceneUtil.error(e.getMessage()).show();
            }
        }
    }

    @FXML protected void handleCancelAction(MouseEvent event) {
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
