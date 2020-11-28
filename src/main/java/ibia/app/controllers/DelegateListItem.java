package ibia.app.controllers;

import java.io.IOException;
import java.io.InputStream;

import ibia.app.App;
import ibia.core.DbDriver;
import ibia.core.entities.Delegate;
import ibia.core.utils.Country;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class DelegateListItem {
    @FXML protected ImageView flag;
    @FXML protected TextField speeches;
    @FXML protected TextField pois;
    @FXML protected TextField amendments;
    @FXML protected TextField motions;
    @FXML protected Text id;
    @FXML protected Text delegation;

    private Delegate instance;
    private String instanceId;

    @FXML
    public void initialize() {
        // runLater must be used to ensure
        // that the instance id has been initialized
        // in CommitteeView#fillDelegatesList
        Platform.runLater(() -> {
            this.instance = DbDriver.fetchOne(Delegate.class, instanceId);

            delegation.setText(instance.getDelegation());
            id.setText("#" + instance.getId());

            // Update cells with delegates' data
            String a = Integer.toString(instance.getSpeeches());
            speeches.setText(a);
            String b = Integer.toString(instance.getPois());
            pois.setText(b);
            String c = Integer.toString(instance.getAmendments());
            amendments.setText(c);
            String d = Integer.toString(instance.getMotions());
            motions.setText(d);

            // Set listeners for updating the db when cell
            // values are changed.
            attachListeners();

            String code = Country.codeFromName(instance.getDelegation());
            if (code != null) {
                InputStream stream = Country.getFlag(code);
                Image img = new Image(stream);
                flag.setImage(img);
            }
        });
    }

    // Attach listeners to update the
    // db with the value of the cells
    // whenever the TextField goes out
    // of focus.
    private void attachListeners() {
        speeches.focusedProperty().addListener((observable, oldFocus, newFocus) -> {
            // Run code when node is out of focus
            if (!newFocus) {
                int n;
                try {
                    String value = speeches.getText();
                    String nonEmpty = value.isEmpty() ? "0" : value;
                    n = Integer.parseInt(nonEmpty);
                } catch (NumberFormatException e) {
                    n = instance.getSpeeches();
                    speeches.setText(Integer.toString(n));
                }
                instance.setSpeeches(n);
                DbDriver.updateOne(instance);
            }
        });

        pois.focusedProperty().addListener((observable, oldFocus, newFocus) -> {
            // Run code when node is out of focus
            if (!newFocus) {
                int n;
                try {
                    String value = pois.getText();
                    String nonEmpty = value.isEmpty() ? "0" : value;
                    n = Integer.parseInt(nonEmpty);
                } catch (NumberFormatException e) {
                    // If a non-integer is entered,
                    // revert back to the previous value.
                    n = instance.getPois();
                    pois.setText(Integer.toString(n));
                }
                instance.setPois(n);
                DbDriver.updateOne(instance);
            }
        });

        amendments.focusedProperty().addListener((observabe, oldFocus, newFocus) -> {
            // Run code when node is out of focus
            if (!newFocus) {
                int n;
                try {
                    String value = amendments.getText();
                    String nonEmpty = value.isEmpty() ? "0" : value;
                    n = Integer.parseInt(nonEmpty);
                } catch (NumberFormatException e) {
                    n = instance.getAmendments();
                    amendments.setText(Integer.toString(n));
                }
                instance.setAmendments(n);
                DbDriver.updateOne(instance);
            }
        });

        motions.focusedProperty().addListener((observable, oldFocus, newFocus) -> {
            // Run code when node is out of focus
            if (!newFocus) {
                int n;
                try {
                    String value = motions.getText();
                    String nonEmpty = value.isEmpty() ? "0" : value;
                    n = Integer.parseInt(nonEmpty);
                } catch (NumberFormatException e) {
                    n = instance.getMotions();
                    motions.setText(Integer.toString(n));
                }
                instance.setMotions(n);
                DbDriver.updateOne(instance);
            }
        });
    }

    @FXML
    protected void navigate(MouseEvent event) throws IOException {
        App.navigate(instance.getId());
    }

    @FXML
    protected void hoverEffectOn(MouseEvent event) {
        Text text = (Text)event.getTarget();
        text.setUnderline(true);
    }

    @FXML
    protected void hoverEffectOff(MouseEvent event) {
        Text text = (Text)event.getTarget();
        text.setUnderline(false);
    }

    public void setInstanceId(String delId) {
        this.instanceId = delId;
    }
}
