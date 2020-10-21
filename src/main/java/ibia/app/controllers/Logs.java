package ibia.app.controllers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class Logs {
    @FXML protected TextArea textArea;

    @FXML
    public void initialize() throws FileNotFoundException, IOException {
        FileReader fr = new FileReader("data/ibia.log");
        // Use BufferedReader for fast reading
        BufferedReader br = new BufferedReader(fr);
        String contents = "";

        while (true) {
            String line = br.readLine();
            if (line == null) break;
            contents += line + "\n";
        }

        br.close();
        textArea.setText(contents);
    }
}
