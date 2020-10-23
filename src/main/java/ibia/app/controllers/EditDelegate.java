package ibia.app.controllers;

import java.util.ArrayList;

import ibia.app.App;
import ibia.app.SceneUtil;
import ibia.core.DbDriver;
import ibia.core.Log;
import ibia.core.entities.Delegate;
import ibia.core.utils.Country;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EditDelegate {
    @FXML protected TextField name;
    @FXML protected TextField delegation;
    @FXML protected MenuButton choose;

    @FXML
    public void initialize() {
        ArrayList<String> countries = Country.listOfNames();
        if (countries == null) return;

        for (String country : countries) {
            MenuItem choice = new MenuItem();
            choice.setText(country);
            choice.setOnAction((ActionEvent event) -> {
                delegation.setText(country);
            });

            choose.getItems().add(choice);
        }
    }

    @FXML
    protected void update(MouseEvent event) {
        String delName = name.getText();
        // validate form data
        if (delName.isEmpty()) {
            SceneUtil.error("The delegate name is required!").show();
        }
        else if (delName.length() > 120) {
            SceneUtil.error("The delegate name must be between 1 and 120 characters!").show();
        }
        else {
            try {
                String delId = App.getLocation();
                Delegate del = DbDriver.fetchOne(Delegate.class, delId);
                del.setName(name.getText());
                del.setDelegation(delegation.getText());
                DbDriver.updateOne(del);
                App.refresh();
                closeStage(event);
            } catch (Exception e) {
                Log.error(e.getMessage());
                e.printStackTrace();
                SceneUtil.error(e.getMessage()).show();
            }
        }
    }

    @FXML
    protected void cancel(MouseEvent event) {
        closeStage(event);
    }

    private void closeStage(MouseEvent event) {
        // cast source to Button so we can access window
        Button source = (Button)(event.getSource());
        // get window, cast it to Stage so we can close it
        Stage stage = (Stage)(source.getScene().getWindow());
        stage.close();
    }
}
