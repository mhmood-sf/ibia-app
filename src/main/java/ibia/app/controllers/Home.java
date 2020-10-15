package ibia.app.controllers;

import javafx.fxml.FXML;
import java.awt.Desktop;
import java.net.URI;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import javafx.stage.Modality;
import javafx.stage.Stage;

import ibia.app.App;
import ibia.app.SceneUtil;


public class Home {
    /**
     * Event handler for mouse click on resumeConf card.
     */
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
     * Hover effect for breadcrumbs
     */
    @FXML
    protected void crumbHoverEffectOn(MouseEvent event) {
        Text text = (Text)event.getTarget();
        text.setUnderline(true);
    }

    /**
     * Hover effect for breadcrumbs
     */
    @FXML
    protected void crumbHoverEffectOff(MouseEvent event) {
        Text text = (Text)event.getTarget();
        text.setUnderline(false);
    }

    /**
     * Runs a shell command to open the given `url`
     * in the default web browser.
     * @param url the url to open
     */
    private void openURL(String url) throws Exception {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            Desktop.getDesktop().browse(new URI(url));
        }
    }
}
