package ibia.app;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/*
 * Provides some common utility functions.
 * This class may not be instantiated.
 * All utility methods and properties are static.
 * 
 */
public final class Util {

    /*
     * Icon for most windows created by ibia.
     */
    public static Image IBIA_ICON = new Image("/images/ibia-icon2.png");

    // Keep a private instance so as to provide a static API
    private static Util instance = new Util();

    // Prevent instantiation of this class.
    private Util() {};

    /*
     * Returns an instance of FXMLLoader, with the location set
     * to one of the files from resources/fxml/ based on the
     * `name` parameter. The file extension '.fxml' in the `name`
     * is optional.
     * 
     * Example:
     * FXMLLoader loader = Util.loadFXML("Welcome");
     * FXMLLoader anotherLoader = Util.loadFXML("NewConference.fxml");
     */
    public static FXMLLoader loadFXML(String name) {
        return instance._loadFXML(name);
    }

    /*
     * Allows to easily and quickly load one of the fxml files in
     * resources/fxml, based on the `name` parameter.
     * 
     * Example:
     * Scene welcomeScene = Util.loadFXMLScene("Welcome", 1000, 600);
     */
    public static Scene loadFXMLScene(String name) throws Exception {
        return instance._loadFXMLScene(name);
    }

    /*
     * Returns a Stage for showing an error window.
     * 
     * Example:
     * Stage error = Util.error("My error message");
     * error.show();
     */
    public static Stage error(String msg) {
        return instance._error(msg);
    }

    /*
     * Implementation for Util.loadFXML
     */
    private FXMLLoader _loadFXML(String name) {
        name = name.endsWith(".fxml") ? name : name + ".fxml";
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/" + name));
        return loader;
    }

    /*
     * Implementation for Util.loadFXMLScene
     */
    private Scene _loadFXMLScene(String name) throws Exception {
        FXMLLoader loader = loadFXML(name);
        Parent content = loader.load();
        return new Scene(content);
    }

    /*
     * Implementation for Util.error
     */
    private Stage _error(String msg) {
        try {
            Stage stage = new Stage();
            // Load fxml file
            Scene scene = _loadFXMLScene("Error");
            // Cast parent to pane, so that we can access the child node
            Pane pane = (Pane)scene.getRoot();
            // get child node and cast it to Vbox
            VBox vbox = (VBox)(pane.getChildren().get(0));
            // repeat to get to Text
            Text text = (Text)(vbox.getChildren().get(0));


            text.setText(msg);
            stage.setTitle("Error:");
            stage.getIcons().add(new Image("/images/error-icon.png"));
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);

            return stage;
        } catch (Exception e) {
            // Just crash if there is an error in error handling...
            System.out.println(e);
            System.exit(1);
            return null; // Apparently Java can't tell that this line is unreachable.
        }
    }
}
