package ibia.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

import ibia.app.templating.TemplateEngine;
import ibia.core.Log;

/**
 * The main class for ibia.app. Controls
 * the main application stage, and handles
 * navigation logic.
 */
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
        Log.info("Initializing JavaFX stage.");
        launch(args);
    }

    /**
     * Loads main stage and starts application.
     */
    @Override
    public void start(Stage stage) {

        try {
            window = stage;
            window.setTitle("ibia");
            window.getIcons().add(IBIA_ICON);
            window.show();
            App.navigate("Home");
        } catch (Exception e) {
            Log.error("Failed to load main window: " + e.getMessage());
            window.close();
            System.exit(1);
        }
    }

    /**
     * Gets current location of the main application stage
     */
    public static String getCurrentLocation() {
        return currentLocation;
    }

    /**
     * Sets the internal stage location variable.
     * This is controlled by the App class. To change
     * the application's stage location, use App.navigate()
     * @param location
     */
    private static void setCurrentLocation(String location) {
        currentLocation = location;
    }

    /**
     * Changes the scene displayed on the main application
     * stage based on the location given. The location can
     * be an entity ID, in which case it will call the
     * TemplateEngine to load and fill in the entity
     * template with data for the given ID and display it.
     * The location may also be the string "Home", in which
     * case the Home screen template will be loaded.
     * @param location
     */
    public static void navigate(String location) throws IllegalArgumentException, IOException {
        // If navigating to the same location, no need to update the scsene.
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

    /**
     * Updates the scene on the application stage.
     * @param scene the scene to display.
     */
    private static void updateScene(Scene scene) {
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
