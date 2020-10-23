package ibia.app.controllers;

import java.io.IOException;

import ibia.app.App;
import ibia.app.SceneUtil;
import ibia.core.DbDriver;
import ibia.core.Log;
import ibia.core.entities.Committee;
import ibia.core.entities.Delegate;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DelegateView {
    @FXML protected Text delegation;
    @FXML protected Text id;

    private Delegate instance;

    @FXML
    public void initialize() {
        this.instance = DbDriver.fetchOne(Delegate.class, App.getLocation());
    }

    @FXML
    protected void handleEditAction(MouseEvent event) {
        SceneUtil.error("Unimplemented!").show();
    }

    @FXML
    protected void handleDeleteAction(MouseEvent event) {
        Stage stage = SceneUtil.confirm("Are you sure you wish to delete this delegate? This action cannot be reversed.");
        Scene root = stage.getScene();
        Button cancel = (Button)root.lookup("#cancel");
        Button confirm = (Button)root.lookup("#confirm");

        cancel.setOnMouseClicked(evt -> {
            stage.close();
        });

        confirm.setOnMouseClicked(evt -> {
            DbDriver.deleteOne(instance);
            try {
                App.navigate("Home");
                stage.close();
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
    protected void navigateHome(MouseEvent event) throws IOException {
        App.navigate("Home");
    }

    /**
     * Navigate back to parent committee
     */
    @FXML
    protected void navigateCommittee(MouseEvent event) throws IOException {
        String id = instance.getCommitteeId();
        App.navigate(id);
    }

    /**
     * Navigate back to parent conference
     */
    @FXML
    protected void navigateConference(MouseEvent event) throws IOException {
        String comId = instance.getCommitteeId();
        Committee com = DbDriver.fetchOne(Committee.class, comId);
        String conId = com.getConferenceId();
        App.navigate(conId);
    }
}
