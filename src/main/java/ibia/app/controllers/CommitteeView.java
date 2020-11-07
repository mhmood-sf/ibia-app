package ibia.app.controllers;

import java.io.IOException;
import java.util.ArrayList;

import ibia.app.App;
import ibia.app.SceneUtil;
import ibia.core.Client;
import ibia.core.DbDriver;
import ibia.core.Log;
import ibia.core.entities.Committee;
import ibia.core.entities.Conference;
import ibia.core.entities.Delegate;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CommitteeView {
    @FXML protected Text conferenceCrumb;
    @FXML protected Text committeeCrumb;
    @FXML protected Text name;
    @FXML protected Text id;
    @FXML protected VBox delegatesList;

    private Committee instance;

    @FXML
    public void initialize() throws IOException {
        this.instance = DbDriver.fetchOne(Committee.class, App.getLocation());

        fillBreadcrumbs();
        fillName();
        fillId();
        fillDelegatesList();
    }

    /*********************/
    /*** FXML Controls ***/
    /*********************/

    @FXML
    protected void handleDeleteAction(MouseEvent event) {
        Stage stage = SceneUtil.confirm("Are you sure you wish to delete this committee? This action cannot be reversed.");
        Scene root = stage.getScene();
        Button cancel = (Button)root.lookup("#cancel");
        Button confirm = (Button)root.lookup("#confirm");

        cancel.setOnMouseClicked(evt -> {
            stage.close();
        });

        confirm.setOnMouseClicked(evt -> {
            Client.deleteCommittee(instance.getId());
            try {
                App.navigate(instance.getConferenceId());
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

    @FXML
    protected void handleEditAction(MouseEvent event) throws IOException {
        Stage stage = SceneUtil.loadPopupStage("EditCommittee", "Edit Committee");
        stage.show();
    }

    @FXML
    protected void openTimer(MouseEvent event) throws IOException {
        Stage stage = SceneUtil.loadPopupStage("SpeechTimer", "Timer");
        stage.show();
    }

    @FXML
    protected void addNewDelegate(MouseEvent event) throws IOException {
        Stage stage = SceneUtil.loadPopupStage("NewDelegate", "Create new Delegate");
        stage.show();
    }

    @FXML
    protected void openStats(MouseEvent event) {
        SceneUtil.error("Unimplemented!").show();
    }

    @FXML
    protected void openTopics(MouseEvent event) throws IOException {
        Stage stage = SceneUtil.loadPopupStage("Topics", "Topics");
        stage.show();
    }

    @FXML
    protected void openResolutions(MouseEvent event) throws IOException {
        Stage stage = SceneUtil.loadPopupStage("Resolutions", "Resolutions");
        stage.show();
    }

    /**
     * Navigate back to home screen.
     */
    @FXML
    protected void navigateHome(MouseEvent event) throws IOException, IllegalArgumentException {
        App.navigate("Home");
    }

    @FXML
    protected void navigateConference(MouseEvent event) throws IOException, IllegalArgumentException {
        String id = instance.getConferenceId();
        App.navigate(id);
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

    /******************/
    /*** Templating ***/
    /******************/

    private void fillBreadcrumbs() {
        Conference conf = DbDriver.fetchOne(Conference.class, instance.getConferenceId());
        conferenceCrumb.setText(conf.getName());
        committeeCrumb.setText(instance.getName());
    }

    private void fillName() {
        name.setText(instance.getName());
    }

    private void fillId() {
        id.setText(instance.getId());
    }

    private void fillDelegatesList() throws IOException {
        String comId = instance.getId();
        ArrayList<Delegate> dels = DbDriver.findAll(Delegate.class, d -> d.getCommitteeId().equals(comId));
        if (dels == null) return;

        ObservableList<Node> list = delegatesList.getChildren();
        for (Delegate del : dels) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DelegateListItem.fxml"));
            Parent root = loader.load();
            DelegateListItem controller = loader.getController();
            controller.setInstanceId(del.getId());

            list.add(root);
        }
    }
}
