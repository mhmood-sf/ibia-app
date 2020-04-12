package ibia.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import ibia.app.Util;

public class Welcome {
    @FXML private Group newConf;
    @FXML private Group pastConf;
    @FXML private Group guides;
    @FXML private Group about;
    @FXML private Group feedback;
    @FXML private Group settings;

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
        // TODO: update url to point to project wiki once repo is public!
        String url = "https://github.com/quantomistro/ibia-app";

        try {
            openURL(url);
        } catch (Exception e) {
            Util.error("Failed to open URL! Open an issue at:\n" + url).show();
        }
    }

    /*
     * Event handler for mouse click on about card.
     */
    @FXML
    protected void handleAboutAction(MouseEvent event) {
        // TODO: add JDK/JRE versions to the scene
        try {
            Scene aboutScene = Util.loadFXMLScene("About");
            Stage aboutStage = new Stage();

            aboutStage.setTitle("About ibia");
            aboutStage.setScene(aboutScene);
            aboutStage.getIcons().add(Util.WINDOW_ICON);
            aboutStage.setResizable(false);
            aboutStage.show();
        } catch (Exception e) {
            Util.error("Failed to load window!").show();
        }
    }

    /*
     * Event handler for mouse click on feedback card.
     */
    @FXML
    protected void handleFeedbackAction(MouseEvent event) {
        String url = "https://github.com/quantomistro/ibia-app/issues/";

        try {
            openURL(url);
        } catch (Exception e) {
            Util.error("Failed to open URL! Open an issue at:\n" + url).show();
        }
    }


    /*
     * Event handler for mouse click on exit card.
     */
    @FXML
    protected void handleSettingsAction() {
        Util.error("Unimplemented!").show();
    }

    /*
     * Hover effect for buttons
     */
    @FXML
    protected void hoverEffectOn(MouseEvent event) {
        Group btn = (Group)event.getTarget();

        Rectangle rect = (Rectangle)btn.getChildren().get(0);
        rect.setStrokeWidth(2);
        rect.setStroke(Color.WHITE);
    }

    /*
     * Hover effect for buttons
     */
    @FXML
    protected void hoverEffectOff(MouseEvent event) {
        Group btn = (Group)event.getTarget();

        Rectangle rect = (Rectangle)btn.getChildren().get(0);
        rect.setStrokeWidth(0);
        rect.setStroke(null);
    }

    /*
     * Runs a shell command to open the given `url`
     * in the default web browser.
     */
    private void openURL(String url) throws Exception {
        // TODO: Make sure the commands for Linux and Mac actually work!
        String os = System.getProperty("os.name");
        String shellCmd = os.startsWith("Windows") ? "cmd /c start" : os.startsWith("Linux") ? "xterm -e xdg-open" : "bash open";
        String cmd = shellCmd + " " + url;

        Runtime.getRuntime().exec(cmd); 
    }
}
