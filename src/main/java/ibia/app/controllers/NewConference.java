package ibia.app.controllers;

import ibia.app.Util;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class NewConference {
    @FXML TextField confName;
    @FXML TextField confCommittees;
    @FXML TextField delsPerCommittee;
    @FXML Button cancelNewConf;
    @FXML Button createNewConf;

    /*
     * Event handler for mouse click on createNewConf.
     * Creates a new conference and opens the conference dashboard.
     */
    @FXML
    protected void handleCreateNewConfAction(MouseEvent event) {
        Stage err = Util.error("Unimplemented!");
        err.show();
        /*
        if (validateForm()) {
            Conference newConf = ibia.core.Core.createNewConf(formDetails);
            openConferenceDashboard(newConf);
        }
        */
    }

    /*
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
