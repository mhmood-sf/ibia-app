package ibia.app.controllers;

import java.io.IOException;

import ibia.app.App;
import ibia.app.SceneUtil;
import ibia.core.DbDriver;
import ibia.core.Log;
import ibia.core.entities.Conference;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConferenceView {
    @FXML protected Button statusButton;
    @FXML protected Text id;
    @FXML protected Text status;

    @FXML
    protected void handleNewCommitteeAction(MouseEvent event) throws IOException {
        try {
            Stage stage = new Stage();
            Scene scene = SceneUtil.loadFXMLScene("NewCommittee");

            stage.setTitle("Create new committee");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.getIcons().add(App.IBIA_ICON);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (Exception e) {
            SceneUtil.error("Failed to load window!").show();
        }
    }

    @FXML
    protected void handleStatusButtonAction(MouseEvent event) {
        String confId = App.getLocation();
        Conference instance = DbDriver.fetchOne(Conference.class, confId);
        if (instance.isOngoing()) {
            instance.setOngoing(false);
            status.setText("FINISHED");
            statusButton.setText("Re-open Conference");
        } else {
            instance.setOngoing(true);
            status.setText("ONGOING");
            statusButton.setText("Finish Conference");
        }
        DbDriver.updateOne(instance);
    }

    @FXML
    protected void handleEditAction(MouseEvent event) {
        SceneUtil.error("Unimplemented!").show();
    }

    @FXML
    protected void handleDeleteAction(MouseEvent event) {
        Stage stage = SceneUtil.confirm("Are you sure you wish to delete this conference? This action cannot be reversed.");
        Scene root = stage.getScene();
        Button cancel = (Button)root.lookup("#cancel");
        Button confirm = (Button)root.lookup("#confirm");

        cancel.setOnMouseClicked(evt -> {
            stage.close();
        });

        confirm.setOnMouseClicked(evt -> {
            String confId = App.getLocation();
            Conference instance = DbDriver.fetchOne(Conference.class, confId);
            DbDriver.deleteOne(instance);
            stage.close();
            try {
                App.navigate("Home");
            } catch (Exception e) {
                // If Home failed to load, this is a fatal error
                // and the application is exited.
                Log.error(e.getMessage());
                System.exit(1);
            }
        });
        stage.show();
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
     * Navigate back to home screen.
     */
    @FXML
    protected void navigateHome(MouseEvent event) throws IOException, IllegalArgumentException {
        App.navigate("Home");
    }
}
