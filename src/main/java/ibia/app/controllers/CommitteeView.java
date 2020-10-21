package ibia.app.controllers;

import java.io.IOException;
import java.util.ArrayList;

import ibia.app.App;
import ibia.app.SceneUtil;
import ibia.core.DbDriver;
import ibia.core.Log;
import ibia.core.entities.Committee;
import ibia.core.entities.Conference;
import ibia.core.entities.Delegate;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
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
            DbDriver.deleteOne(instance);
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
    protected void handleEditAction(MouseEvent event) {
        SceneUtil.error("Unimplemented!").show();
    }

    @FXML
    protected void openTimer(MouseEvent event) {
        SceneUtil.error("Unimplemented!").show();
    }

    @FXML
    protected void addNewDelegate(MouseEvent event) {
        SceneUtil.error("Unimplemented!").show();
    }

    @FXML
    protected void openStats(MouseEvent event) {
        SceneUtil.error("Unimplemented!").show();
    }

    @FXML
    protected void openTopics(MouseEvent event) {
        SceneUtil.error("Unimplemented!").show();
    }

    @FXML
    protected void openResolutions(MouseEvent event) {
        SceneUtil.error("Unimplemented!").show();
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
        String id = instance.getId();
        ArrayList<Delegate> dels = DbDriver.findAll(Delegate.class, d -> d.getCommitteeId().equals(id));
        if (dels == null) return;
        ObservableList<Node> list = delegatesList.getChildren();
        for (Delegate del : dels) {
            HBox item = (HBox)SceneUtil.loadFXML("DelegateListItem", false);
            Text delegation = (Text)item.getChildren().get(1);
            Text delId = (Text)item.getChildren().get(2);
            delegation.setText(del.getDelegation());
            delId.setText("#" + del.getId());
            list.add(item);
        }
    }
}
