package ibia.app.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ibia.app.Util;

public class Welcome {
    @FXML private Group newConf;
    @FXML private Group pastConf;
    @FXML private Group guides;
    @FXML private Group about;
    @FXML private Group feedback;
    @FXML private Group exit;

    /*
     * Event handler for mouse click on newConf card.
     */
    @FXML
    protected void handleNewConfAction(MouseEvent event) {
        /*Stage err = Util.error("Unimplemented!");
        err.show();*/
        try {
            Scene newConfForm = Util.loadFXMLScene("NewConference");
            Stage formStage = new Stage();

            formStage.setTitle("Create new conference");
            formStage.setScene(newConfForm);
            formStage.getIcons().add(Util.WINDOW_ICON);
            formStage.show();
        } catch (Exception e) {
            Util.error("Failed to load window!");
        }
    }

    /*
     * Event handler for mouse click on pastConf card.
     */
    @FXML
    protected void handlePastConfAction(MouseEvent event) {
        Stage err = Util.error("Unimplemented!");
        err.show();
    }

    /*
     * Event handler for mouse click on guides card.
     */
    @FXML
    protected void handleGuidesAction(MouseEvent event) {
        Stage err = Util.error("Unimplemented!");
        err.show();
    }

    /*
     * Event handler for mouse click on about card.
     */
    @FXML
    protected void handleAboutAction(MouseEvent event) {
        Stage err = Util.error("Unimplemented!");
        err.show();
    }

    /*
     * Event handler for mouse click on feedback card.
     */
    @FXML
    protected void handleFeedbackAction(MouseEvent event) {
        Stage err = Util.error("Unimplemented!");
        err.show();
    }


    /*
     * Event handler for mouse click on exit card.
     */
    @FXML
    protected void handleExitAction() {
        Platform.exit();
        System.exit(0);
    }

    /*
     * Hover effect for buttons
     */
    @FXML
    protected void hoverEffectOn(MouseEvent event) {
        Group btn = (Group)event.getTarget();

        btn.setTranslateX(3);
        btn.setTranslateY(3);
    }

    /*
     * Hover effect for buttons
     */
    @FXML
    protected void hoverEffectOff(MouseEvent event) {
        Group btn = (Group)event.getTarget();

        btn.setTranslateX(0);
        btn.setTranslateY(0);
    }
}
