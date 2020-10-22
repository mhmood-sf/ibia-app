package ibia.app.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class SpeechTimer {
    @FXML protected Text minutes;
    @FXML protected Text seconds;
    @FXML protected Button toggle;

    private boolean on = false;
    private IntegerProperty mins = new SimpleIntegerProperty(0);
    private IntegerProperty secs = new SimpleIntegerProperty(0);
    
    // JavaFX Timelines are mainly for animations,
    // but can also be used for scheduling tasks
    // on the same thread that handles the scenes
    // and displays.
    private Timeline timeline;

    @FXML
    public void initialize() {
        // Bind mins and secs to the Nodes' text properties
        // so that they update automatically.
        minutes.textProperty().bind(mins.asString());
        seconds.textProperty().bind(secs.asString());
    }

    @FXML
    protected void toggleTimer() {
        if (on) {
            stopTimer();
            toggle.setText("Start");
            on = false;
        } else {
            startTimer();
            toggle.setText("Stop");
            on = true;
        }
    }

    protected void startTimer() {
        KeyFrame keyframe = new KeyFrame(Duration.seconds(1), event -> {
            if (secs.greaterThanOrEqualTo(59).get()) {
                mins.set(mins.get() + 1);
                secs.set(0);
            } else {
                secs.set(secs.get() + 1);
            }
        });
        timeline = new Timeline(keyframe);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    protected void stopTimer() {
        timeline.stop();
    }
}
