package ibia.app.controllers;

import java.util.Timer;
import java.util.TimerTask;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class SpeechTimer {
    @FXML protected Text minutes;
    @FXML protected Text seconds;
    @FXML protected Button toggle;

    private boolean on = false;
    private int s = 0;
    private int m = 0;

    private Timer timer = new Timer();

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
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (s >= 59) {
                    s = 0;
                    m += 1;
                    seconds.setText("00");

                    String mins = Integer.toString(m);
                    if (mins.length() == 1) mins = "0" + mins;
                    minutes.setText(mins);
                } else {
                    s += 1;

                    String secs = Integer.toString(s);
                    if (secs.length() == 1) secs = "0" + secs;
                    seconds.setText(secs);
                }
            }
        };

        timer.scheduleAtFixedRate(task, 1000L, 1000L);
    }

    protected void stopTimer() {
        timer.cancel();
    }
}
