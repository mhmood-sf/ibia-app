package ibia.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import ibia.app.Util;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene welcomeScene = Util.loadFXMLScene("Welcome", 1000, 600);

        stage.setTitle("ibia");
        stage.getIcons().add(new Image("/ibia-icon2.png"));
        stage.setScene(welcomeScene);
        stage.setResizable(false);
        stage.show();
    }
}
