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
        try {
            Scene newConfScene = Util.loadFXMLScene("NewConference");
            Stage newConfStage = new Stage();

            newConfStage.setTitle("Create new conference");
            newConfStage.setScene(newConfScene);
            newConfStage.getIcons().add(Util.WINDOW_ICON);
            newConfStage.setMinHeight(350);
            newConfStage.setMinWidth(350);
            newConfStage.show();
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
        try {
            Scene aboutScene = Util.loadFXMLScene("About");
            Stage aboutStage = new Stage();

            aboutStage.setTitle("About ibia");
            aboutStage.setScene(aboutScene);
            aboutStage.setResizable(false);
            aboutStage.show();
        } catch (Exception e) {
            Util.error("Failed to load window!");
        }
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

        btn.setTranslateX(1);
        btn.setTranslateY(1);
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
