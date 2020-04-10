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
            Util.error("Failed to load window!").show();
        }
    }

    /*
     * Event handler for mouse click on feedback card.
     */
    @FXML
    protected void handleFeedbackAction(MouseEvent event) {
        // TODO: It doesn't work????
        // Says "cannot find specified file" when using "start <urL>" on windows!!!
        String url = "https://github.com/quantomistro/ibia-app/issues/";
        String os = System.getProperty("os.name");
        String cmd = os.startsWith("Windows") ? "start" : os.startsWith("Linux") ? "xdg-open" : "open";

        try {
            System.out.println(cmd + " " + url);
            String[] cmdarray = {cmd, url};
            Runtime.getRuntime().exec(cmdarray);
        } catch (Exception e) {
            System.out.print(e.getMessage());
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
}
