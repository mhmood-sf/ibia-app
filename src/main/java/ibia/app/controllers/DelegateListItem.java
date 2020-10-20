package ibia.app.controllers;

import java.io.IOException;

import ibia.app.App;
import ibia.core.DbDriver;
import ibia.core.entities.Delegate;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class DelegateListItem {
    @FXML protected TextField speeches;
    @FXML protected TextField pois;
    @FXML protected TextField amendments;
    @FXML protected TextField motions;
    @FXML protected Text id;

    private Delegate instance;

    @FXML
    public void initialize() {
        String delId = id.getText().substring(1);
        this.instance = DbDriver.fetchOne(Delegate.class, delId);

        String a = Integer.toString(instance.getSpeeches());
        speeches.setText(a);
        String b = Integer.toString(instance.getPois());
        pois.setText(b);
        String c = Integer.toString(instance.getAmendments());
        amendments.setText(c);
        String d = Integer.toString(instance.getMotions());
        motions.setText(d);
    }

    @FXML
    protected void updateSpeeches(InputMethodEvent event) {
        int n = Integer.parseInt(speeches.getText());
        instance.setSpeeches(n);
        DbDriver.updateOne(instance);
    }

    @FXML
    protected void updatePois(InputMethodEvent event) {
        int n = Integer.parseInt(pois.getText());
        instance.setPois(n);
        DbDriver.updateOne(instance);
    }

    @FXML
    protected void updateAmendments(InputMethodEvent event) {
        int n = Integer.parseInt(amendments.getText());
        instance.setAmendments(n);
        DbDriver.updateOne(instance);
    }

    @FXML
    protected void updateMotions(InputMethodEvent event) {
        int n = Integer.parseInt(motions.getText());
        instance.setMotions(n);
        DbDriver.updateOne(instance);
    }

    @FXML
    protected void navigate(MouseEvent event) throws IOException {
        App.navigate(instance.getId());
    }

    /**
     * Hover effect
     */
    @FXML
    protected void hoverEffectOn(MouseEvent event) {
        Text text = (Text)event.getTarget();
        text.setUnderline(true);
    }

    /**
     * Hover effect
     */
    @FXML
    protected void hoverEffectOff(MouseEvent event) {
        Text text = (Text)event.getTarget();
        text.setUnderline(false);
    }
}
