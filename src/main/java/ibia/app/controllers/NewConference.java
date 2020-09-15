package ibia.app.controllers;

import ibia.app.SceneUtil;
import ibia.core.Log;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class NewConference {
    @FXML TextField confName;

    /**
     * Event handler for mouse click on createNewConf.
     * Creates a new conference and opens the conference dashboard.
     */
    @FXML
    protected void handleCreateNewConfAction(MouseEvent event) {        
        String name = confName.getText();

        // validate form data
        if (name.isEmpty()) {
            SceneUtil.error("The conference name is required!").show();
        }
        else if (name.length() > 50) {
            SceneUtil.error("The conference name must be between 1 and 50 characters!\nTry using an abbreviation.").show();
        }
        else {
            try {
                throw new Exception("Unimplemented");
            } catch (Exception e) {
                Log.error(e.getMessage());
                SceneUtil.error("Unimplemented!").show();
            }
        }
    }

    /**
     * Event handler for mouse click on cancelNewConf.
     * Simply closes the conference details form.
     */
    @FXML protected void handleCancelNewConfAction(MouseEvent event) {
        // cast source to Button so we can access window
        Button source = (Button)(event.getSource());
        // get window, cast it to Stage so we can close it
        Stage stage = (Stage)(source.getScene().getWindow());
        stage.close();
    }
}
