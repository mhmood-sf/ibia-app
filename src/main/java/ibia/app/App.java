package ibia.app;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

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
     * The scene being displayed on the main stage
     */
    private static Scene scene;

    /**
     * Indicates the current scene being displayed.
     * The value is always an entity ID or "Home" (for the
     * welcome screen).
     */
    private static String location;

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
            // First, set a scene to load initially
            Pane root = new Pane();
            root.setMinWidth(1000);
            root.setMinHeight(600);
            scene = new Scene(root);

            // Load that scene and show the window
            window = stage;
            window.setTitle("ibia");
            window.getIcons().add(IBIA_ICON);
            window.setMinWidth(1000);
            window.setMinHeight(600 + 39); // Accomodate for title bar because JavaFX doesn't
            window.setScene(scene);
            window.show();

            // Then navigate to the Home screen, and start the application.
            App.navigate("Home");
        } catch (Exception e) {
            Log.error("Failed to load main window: " + e.getMessage());
            e.printStackTrace();
            window.close();
            System.exit(1);
        }
    }

    /**
     * Updates the scene on the application stage.
     * @param scene the scene to display.
     */
    private static void updateScene(Parent content) {
        scene.setRoot(content);
    }

    /**
     * Gets current location of the main application stage
     */
    public static String getLocation() {
        return location;
    }

    /**
     * Sets the internal stage location variable.
     * This is controlled by the App class. To change
     * the application's stage location, use App.navigate()
     * @param newLocation
     */
    private static void setLocation(String newLocation) {
        location = newLocation;
    }

    /**
     * Changes the scene displayed on the main application
     * stage based on the id given. The id can
     * be an entity ID, in which case the ID's corresponding
     * view will be loaded. The id may also be the string
     * "Home", in which case the Home scene will be loaded.
     * @param id
     */
    public static void navigate(String id) throws IllegalArgumentException, IOException {
        // If navigating to the same location, no need to update the scsene.
        if (location != null && location.equals(id)) return;
        // Otherwise, update App.location
        setLocation(id);
        if (id.equals("Home")) {
            Parent home = SceneUtil.loadFXML("Home", true);
            updateScene(home);
        } else {
            String entity = id.substring(0, 3);
            switch (entity) {
                case "CON":
                    Parent conferenceScene = SceneUtil.loadFXML("ConferenceView", true);
                    updateScene(conferenceScene);
                    break;
                case "COM":
                    Parent committeeScene = SceneUtil.loadFXML("CommitteeView", true);
                    updateScene(committeeScene);
                    break;
                case "DEL":
                    Parent delegateScene = SceneUtil.loadFXML("DelegateView", true);
                    updateScene(delegateScene);
                    break;
                default:
                    throw new IllegalArgumentException(
                        "Invalid location provided: " + id +
                        "\nLocation must be an entity ID or 'Home'."
                        );
            }
        }
    }
}
