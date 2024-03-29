package ibia.app.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ibia.app.App;
import ibia.app.SceneUtil;
import ibia.core.Client;
import ibia.core.DbDriver;
import ibia.core.Log;
import ibia.core.entities.Committee;
import ibia.core.entities.Conference;
import ibia.core.entities.Delegate;
import ibia.core.utils.Resolution;

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

public class ConferenceView {
    @FXML protected Button statusButton;
    @FXML protected Text id;
    @FXML protected Text status;
    @FXML protected Text conferenceCrumb;
    @FXML protected Text name;
    @FXML protected Text created;
    @FXML protected Text committees;
    @FXML protected Text delegates;
    @FXML protected Text resolutions;
    @FXML protected VBox committeesList;

    private Conference instance;

    @FXML
    public void initialize() throws IOException {
        this.instance = DbDriver.fetchOne(Conference.class, App.getLocation());

        fillBreadcrumbs();
        fillName();
        fillId();
        fillDetails();
        fillStatusButton();
        fillCommitteesList();
    }

    /*********************/
    /*** FXML Controls ***/
    /*********************/

    /**
     * @param event the MouseEvent instance
     * @throws IOException - if loading FXML fails
     */
    @FXML
    protected void handleNewCommitteeAction(MouseEvent event) throws IOException {
        try {
            Stage stage = SceneUtil.loadPopupStage("NewCommittee", "Create new Committee");
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
    protected void handleEditAction(MouseEvent event) throws IOException {
        Stage stage = SceneUtil.loadPopupStage("EditConference", "Edit Conference");
        stage.show();
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
            Client.deleteConference(instance.getId());
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

    @FXML
    protected void crumbHoverEffectOn(MouseEvent event) {
        Text text = (Text)event.getTarget();
        text.setUnderline(true);
    }

    @FXML
    protected void crumbHoverEffectOff(MouseEvent event) {
        Text text = (Text)event.getTarget();
        text.setUnderline(false);
    }

    @FXML
    protected void navigateHome(MouseEvent event) throws IOException, IllegalArgumentException {
        App.navigate("Home");
    }

    /******************/
    /*** Templating ***/
    /******************/

    private void fillBreadcrumbs() {
        conferenceCrumb.setText(instance.getName());
    }

    private void fillName() {
        name.setText(instance.getName());
    }

    private void fillId() {
        id.setText(instance.getId());
    }

    private void fillDetails() {
        // Sets OPENED date
        Date date = instance.getCreated();
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        created.setText(fmt.format(date));

        // Sets status to ONGOING or FINISHED
        String currentStatus = instance.isOngoing() ? "ONGOING" : "FINISHED";
        status.setText(currentStatus);

        // Sets number of committees
        ArrayList<Committee> coms = Client.getConferenceCommittees(instance.getId());
        int i = coms != null ? coms.size() : 0;
        committees.setText(Integer.toString(i));

        // Sets number of delegates
        ArrayList<Delegate> dels = new ArrayList<>();
        if (coms != null) {
            for (Committee com : coms) {
                ArrayList<Delegate> fetched = Client.getCommitteeDelegates(com.getId());
                if (fetched != null) {
                    dels.addAll(fetched);
                }
            }
        }
        delegates.setText(Integer.toString(dels.size()));

        // Sets number of resolutions
        ArrayList<Resolution> resos = DbDriver.findAll(Resolution.class, r -> r.getPassed());
        int totalResos = 0;
        if (resos != null) {
            for (Resolution reso : resos) {
                for (Delegate del : dels) {
                    if (del.getId().equals(reso.getMainSubmitter())) {
                        totalResos += 1;
                    }
                }
            }
        }
        resolutions.setText(Integer.toString(totalResos));
    }

    private void fillStatusButton() {
        if (instance.isOngoing()) {
            statusButton.setText("Finish Conference");
        } else {
            statusButton.setText("Re-open Conference");
        }
    }

    private void fillCommitteesList() throws IOException {
        ArrayList<Committee> coms = DbDriver.findAll(Committee.class, c -> c.getConferenceId().equals(instance.getId()));
        if (coms == null) return;
        ObservableList<Node> list = committeesList.getChildren();
        for (Committee com : coms) {
            HBox hbox = (HBox)SceneUtil.loadFXML("CommitteeListItem", false);
            Text name = (Text)hbox.getChildren().get(0);
            name.setText(com.getName());
            Text comId = (Text)hbox.getChildren().get(1);
            comId.setText("#" + com.getId());
            list.add(hbox);
        }
    }
}
