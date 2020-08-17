package ibia.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import ibia.app.templating.TemplateEngine;

// TODO: add proper documentation
// TODO: get delegate-placeholder.png image
// TODO: make scenes for committees + delegates too!
// TODO: add messages to ALL exceptions.
// TODO: see how to use CSS w/ JavaFX to add themes

public class App extends Application {
    /**
     * Icon for most windows created by ibia.
     */
    public static Image IBIA_ICON = new Image("/images/ibia-icon2.png");

    /**
     * Main application window
     */
    private static Stage window;

    /**
     * Indicates the current scene being displayed.
     * The value is always an entity ID or "Home" (for the
     * welcome screen).
     */
    private static String currentLocation;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        window = stage;

        window.setTitle("ibia");
        window.getIcons().add(IBIA_ICON);
        window.setMinHeight(650);
        window.setMinWidth(1050);
        window.show();

        try {
            App.navigate("Home");
        } catch (Exception e) {
            System.out.println("Error loading window!");
            window.close();
            System.exit(1);
        }
    }

    public static String getCurrentLocation() {
        return currentLocation;
    }

    private static void setCurrentLocation(String location) {
        currentLocation = location;
    }

    public static void navigate(String location) throws Exception {
        if (currentLocation.equals(location)) return;

        if (location.equals("Home")) {
            Scene home = TemplateEngine.loadHome();
            window.setScene(home);
        } else {
            String entity = location.substring(0, 3);
            switch (entity) {
                case "CON":
                    Scene conferenceScene = TemplateEngine.loadConference(location);
                    window.setScene(conferenceScene);
                    break;
                case "COM":
                    Scene committeeScene = TemplateEngine.loadCommittee(location);
                    window.setScene(committeeScene);
                    break;
                case "DEL":
                    Scene delegateScene = TemplateEngine.loadDelegate(location);
                    window.setScene(delegateScene);
                    break;
                default:
                    throw new Exception(
                        "Invalid location provided: " + location +
                        "\nLocation must be an entity ID or 'Home'."
                        );
            }
        }

        setCurrentLocation(location);
    }
}
