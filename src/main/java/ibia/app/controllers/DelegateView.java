package ibia.app.controllers;

import java.io.IOException;
import java.io.InputStream;

import ibia.app.App;
import ibia.app.SceneUtil;
import ibia.core.Client;
import ibia.core.DbDriver;
import ibia.core.Log;
import ibia.core.entities.Committee;
import ibia.core.entities.Conference;
import ibia.core.entities.Delegate;
import ibia.core.utils.Country;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DelegateView {
    @FXML protected Text delegation;
    @FXML protected Text id;
    @FXML protected Text delName;
    @FXML protected Text comName;
    @FXML protected Text speeches;
    @FXML protected Text pois;
    @FXML protected Text amendments;
    @FXML protected Text motions;
    @FXML protected Text resos;
    @FXML protected Text committeeCrumb;
    @FXML protected Text delegateCrumb;
    @FXML protected Text conferenceCrumb;
    @FXML protected ImageView flag;

    private Delegate instance;

    @FXML
    public void initialize() {
        this.instance = DbDriver.fetchOne(Delegate.class, App.getLocation());


        fillBreadcrumbs();
        fillDelegation();
        fillId();
        fillDetails();
        fillFlag();
    }

    /*********************/
    /*** FXML Controls ***/
    /*********************/

    @FXML
    protected void handleEditAction(MouseEvent event) throws IOException {
        Stage stage = SceneUtil.loadPopupStage("EditDelegate", "Edit Delegate");
        stage.show();
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
            Client.deleteDelegate(instance.getId());
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

    /******************/
    /*** Templating ***/
    /******************/

    private void fillBreadcrumbs() {
        delegateCrumb.setText(instance.getName());
        
        String comId = instance.getCommitteeId();
        Committee com = DbDriver.fetchOne(Committee.class, comId);
        committeeCrumb.setText(com.getName());
        Conference conf = DbDriver.fetchOne(Conference.class, com.getConferenceId());
        conferenceCrumb.setText(conf.getName());
    }

    private void fillDelegation() {
        delegation.setText(instance.getDelegation());
    }

    private void fillId() {
        id.setText(instance.getId());
    }

    private void fillDetails() {
        delName.setText(instance.getName());
        comName.setText(committeeCrumb.getText());
        String a = Integer.toString(instance.getSpeeches());
        speeches.setText(a);
        String b = Integer.toString(instance.getPois());
        pois.setText(b);
        String c = Integer.toString(instance.getAmendments());
        amendments.setText(c);
        String d = Integer.toString(instance.getMotions());
        motions.setText(d);
    }

    private void fillFlag() {
        String code = Country.codeFromName(instance.getDelegation());
        InputStream stream = Country.getFlag(code);
        if (stream != null) {
            Image img = new Image(stream);
            flag.setImage(img);
        }
    }

}
