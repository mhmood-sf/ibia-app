package ibia.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import ibia.app.SceneUtil;

// TODO: add proper documentation
// TODO: get delegate-placeholder.png image
// TODO: make scenes for committees + delegates too!
// TODO: add messages to ALL exceptions ! ! !

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
     * The value is always an ID or "Home" (for the
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
            Scene home = SceneUtil.loadFXMLScene("Home");
            window.setScene(home);
        } else {
            String entity = location.substring(0, 3);
            switch (entity) {
                case "CON":
                    //Conference conference = DbDriver.fetchOne(path[0]);
                    Scene conferenceScene = Template.loadConference(/*conference*/);
                    window.setScene(conferenceScene);
                    break;
                case "COM":
                    //Conference conference = DbDriver.fetchOne(path[0]);
                    //Committee committee = DbDriver.fetchOne(path[1]);
                    Scene committeeScene = Template.loadCommittee(/*committee, conference*/);
                    window.setScene(committeeScene);
                    break;
                case "DEL":
                    //Conference conference = DbDriver.fetchOne(path[0]);
                    //Committee committee = DbDriver.fetchOne(path[1]);
                    //Delegate delegate = DbDriver.fetchOne(path[2]);
                    Scene delegateScene = Template.loadDelegate(/*delegate, committee, conference*/);
                    window.setScene(delegateScene);
                    break;
                default:
                    throw new Exception(
                        "Invalid location provided: " + location +
                        "\nLocation must be an ID or \"Home\"!"
                        );
            }
        }

        setCurrentLocation(location);
    }
}
