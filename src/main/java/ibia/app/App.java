package ibia.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import ibia.app.Util;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Stage window = stage;

        Scene welcomeScene = Util.loadFXMLScene("Welcome");

        window.setTitle("ibia");
        window.getIcons().add(Util.IBIA_ICON);
        window.setScene(welcomeScene);
        window.setMinHeight(650);
        window.setMinWidth(1050);
        window.show();
    }
}
