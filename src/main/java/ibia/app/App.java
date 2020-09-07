package ibia.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import ibia.app.templating.TemplateEngine;

// TODO: add proper documentation
// TODO: get delegate-placeholder.png image
// TODO: make scenes for committees + delegates too!
// TODO: add messages to ALL exceptions.
// TODO: see how to use CSS w/ JavaFX to add themes
// TODO: better naming for methods in controllers please!

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
        window.show();

        try {
            App.navigate("Home");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error loading window!");
            window.close();
            System.exit(1);
        }
    }

    public static String getCurrentLocation() {
        return currentLocation;
    }

    // Keep method private to prevent external classes
    // from changing currentLocation. The only way to
    // change it should be by using App.navigate(location)
    private static void setCurrentLocation(String location) {
        currentLocation = location;
    }

    public static void navigate(String location) throws Exception {
        // If it's navigating to the same location,
        // return early.
        if (currentLocation != null && currentLocation.equals(location)) return;

        if (location.equals("Home")) {
            Scene home = TemplateEngine.loadHome();
            updateScene(home);
        } else {
            String entity = location.substring(0, 3);
            switch (entity) {
                case "CON":
                    Scene conferenceScene = TemplateEngine.loadConference(location);
                    updateScene(conferenceScene);
                    break;
                case "COM":
                    Scene committeeScene = TemplateEngine.loadCommittee(location);
                    updateScene(committeeScene);
                    break;
                case "DEL":
                    Scene delegateScene = TemplateEngine.loadDelegate(location);
                    updateScene(delegateScene);
                    break;
                default:
                    throw new IllegalArgumentException(
                        "Invalid location provided: " + location +
                        "\nLocation must be an entity ID or 'Home'."
                        );
            }
        }

        setCurrentLocation(location);
    }

    private static void updateScene(Scene scene) {
        // TODO: fix this
        // Hacky workaround for the resizing problem
        // - setScene first - the Scene MUST be responsive
        // update min height/width - automatically resizes scene
        // the min height/width is taken from the scene and
        // then 50 is added to both, to account for the extra
        // space taken up by (i think) the native window bar at the top.
        VBox container = (VBox)scene.getRoot();
        window.setScene(scene);
        window.setMinHeight(container.getMinHeight() + 40);
        window.setMinWidth(container.getMinWidth() + 40);
    }
}
