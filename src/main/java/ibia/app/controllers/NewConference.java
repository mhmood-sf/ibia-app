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
        String name = confName.getText();
        String delegates = delsPerCommittee.getText();
        String committees = confCommittees.getText();

        // validate form data
        if (name.isEmpty()) {
            Util.error("The conference name is required!").show();
            return;
        }
        if (name.length() > 50) {
            Util.error("The conference name must be between 1 and 50 characters!\nTry using an abbreviation.").show();
            return;
        }
        
        if (!delegates.isEmpty()) {
            try {
                int dels = Integer.parseInt(delegates);
                if (dels > 150 || dels < 1) {
                    Util.error("The number of delegates per committee must be between 1 and 150!").show();
                    return;
                }
            } catch (NumberFormatException e) {
                Util.error("Invalid number given!").show();
                return;
            }
        }
        
        if (!committees.isEmpty()) {
            try {
                int coms = Integer.parseInt(committees);
                if (coms > 50 || coms < 1) {
                    Util.error("The number of committees in a conference must be between 1 and 50!").show();
                    return;
                }
            } catch (NumberFormatException e) {
                Util.error("Invalid number given!").show();
                return;
            }
        }
        
        // Use a default of 1 for both values
        int dels = delegates.isEmpty() ? 1 : Integer.parseInt(delegates);
        int coms = committees.isEmpty() ? 1 : Integer.parseInt(committees);

        Util.error("Unimplemented!").show();
        // Call core package and add conference to db.
        // Then redirect to conference dashboard
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
