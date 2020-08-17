package ibia.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ibia.app.App;
import ibia.app.SceneUtil;

public class Home {
    @FXML private Group newConf;
    @FXML private Group pastConf;
    @FXML private Group guides;
    @FXML private Group about;
    @FXML private Group feedback;
    @FXML private Group settings;
    @FXML private HBox breadcrumbs;
    @FXML private HBox main;
    @FXML private Group resumeConf;

    @FXML
    protected void handleResumeConfAction() {
        SceneUtil.error("Unimplemented!").show();
    }

    /**
     * Event handler for mouse click on newConf card.
     */
    @FXML
    protected void handleNewConfAction() {
        try {
            Scene newConfScene = SceneUtil.loadFXMLScene("NewConference");
            Stage newConfStage = new Stage();

            newConfStage.setTitle("Create new conference");
            newConfStage.setScene(newConfScene);
            newConfStage.getIcons().add(App.IBIA_ICON);
            newConfStage.setMinHeight(135);
            newConfStage.setMinWidth(350);
            newConfStage.initModality(Modality.APPLICATION_MODAL);
            newConfStage.show();
        } catch (Exception e) {
            SceneUtil.error("Failed to load window!").show();
        }
    }

    /**
     * Event handler for mouse click on pastConf card.
     */
    @FXML
    protected void handlePastConfAction() {
        SceneUtil.error("Unimplemented!").show();
    }

    /**
     * Event handler for mouse click on guides card.
     */
    @FXML
    protected void handleGuidesAction() {
        String url = "https://github.com/quantomistro/ibia-app";

        try {
            openURL(url);
        } catch (Exception e) {
            SceneUtil.error("Failed to open URL!").show();
        }
    }

    /**
     * Event handler for mouse click on about card.
     */
    @FXML
    protected void handleAboutAction() {
        // TODO: add JDK/JRE versions to the scene
        try {
            Scene aboutScene = SceneUtil.loadFXMLScene("About");
            Stage aboutStage = new Stage();

            aboutStage.setTitle("About ibia");
            aboutStage.setScene(aboutScene);
            aboutStage.getIcons().add(App.IBIA_ICON);
            aboutStage.setResizable(false);
            aboutStage.initModality(Modality.APPLICATION_MODAL);
            aboutStage.show();
        } catch (Exception e) {
            SceneUtil.error("Failed to load window!").show();
        }
    }

    /**
     * Event handler for mouse click on feedback card.
     */
    @FXML
    protected void handleFeedbackAction() {
        String url = "https://github.com/quantomistro/ibia-app/issues/";

        try {
            openURL(url);
        } catch (Exception e) {
            SceneUtil.error("Failed to open URL!").show();
        }
    }


    /**
     * Event handler for mouse click on exit card.
     */
    @FXML
    protected void handleSettingsAction() {
        SceneUtil.error("Unimplemented!").show();
    }

    /**
     * Hover effect for buttons
     */
    @FXML
    protected void hoverEffectOn(MouseEvent event) {
        Group btn = (Group)event.getTarget();

        Rectangle rect = (Rectangle)btn.getChildren().get(0);
        rect.setStrokeWidth(2);
        rect.setStroke(Color.WHITE);
    }

    /**
     * Hover effect for buttons
     */
    @FXML
    protected void hoverEffectOff(MouseEvent event) {
        Group btn = (Group)event.getTarget();

        Rectangle rect = (Rectangle)btn.getChildren().get(0);
        rect.setStrokeWidth(0);
        rect.setStroke(null);
    }

    /**
     * Runs a shell command to open the given `url`
     * in the default web browser.
     * TODO: find a better way to do this :P
     */
    private void openURL(String url) throws Exception {
        // TODO: Make sure the commands for Linux and Mac actually work!
        String os = System.getProperty("os.name");
        String shellCmd = os.startsWith("Windows") ? "cmd /c start" : os.startsWith("Linux") ? "xterm -e xdg-open" : "bash open";
        String cmd = shellCmd + " " + url;

        Runtime.getRuntime().exec(cmd); 
    }
}