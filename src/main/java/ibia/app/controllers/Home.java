package ibia.app.controllers;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import ibia.app.SceneUtil;
import ibia.core.DbDriver;
import ibia.core.entities.Conference;

public class Home {
    @FXML protected Text resumeMsg;

    @FXML
    public void initialize() throws IOException {
        ArrayList<Conference> confs = DbDriver.findAll(Conference.class, c -> c.isOngoing());
        if (confs != null && confs.size() > 0) {
            resumeMsg.setText("Click to view ongoing conferences");
        }
    }
    /**
     * Event handler for mouse click on resumeConf card.
     */
    @FXML
    protected void handleResumeConfAction() throws IOException {
        Stage stage = SceneUtil.loadPopupStage("OngoingConferences", "Choose an ongoing Conference");
        stage.show();
    }

    /**
     * Event handler for mouse click on newConf card.
     */
    @FXML
    protected void handleNewConfAction() throws IOException {
        Stage stage = SceneUtil.loadPopupStage("NewConference", "Create new Conference");
        stage.show();
    }

    /**
     * Event handler for mouse click on pastConf card.
     */
    @FXML
    protected void handlePastConfAction() throws IOException {
        Stage stage = SceneUtil.loadPopupStage("PastConferences", "Choose a finished Conference");
        stage.show();
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
    protected void handleAboutAction() throws IOException {
        Stage stage = SceneUtil.loadPopupStage("About", "About ibia");
        stage.show();
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
    protected void handleViewLogsAction() throws IOException {
        Stage stage = SceneUtil.loadPopupStage("Logs", "Logs");
        stage.show();
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
