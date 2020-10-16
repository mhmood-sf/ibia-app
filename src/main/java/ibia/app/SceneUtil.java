package ibia.app;

import java.io.IOException;
import java.util.HashMap;

import ibia.core.Log;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Utility class for loading scenes, specifically
 * from fxml files.
 * 
 */
public final class SceneUtil {

    /**
     * A private instance so as to provide
     * an easier API with static methods.
     */
    private static SceneUtil instance = new SceneUtil();

    /**
     * Private constructor, to reject instantiation since
     * this class acts as a singleton.
     */
    private SceneUtil() {};

    /**
     * Cache for holding loaded FXML files.
     */
    private HashMap<String, Parent> cache = new HashMap<>();

    /**
     * Loads and returns the content defined by
     * the specified fxml file, located in
     * resources/fxml
     * 
     * Example:
     * Parent welcomeScene = Util.loadFXML("Welcome");
     * 
     * @param name - The name of the fxml file (without the extension)
     * @return The scene loaded from the fxml file
     */
    public static Parent loadFXML(String name) throws IOException {
        return instance._loadFXML(name);
    }

    /**
     * Loads FXML content into a Scene
     */
    public static Scene loadFXMLScene(String name) throws IOException {
        return instance._loadFXMLScene(name);
    }

    /**
     * Returns a Stage for showing an error window.
     * 
     * Example:
     * Stage error = SceneUtil.error("My error message");
     * error.show();
     */
    public static Stage error(String msg) {
        return instance._error(msg);
    }

    /**
     * Returns a stage showing a confirmation message,
     * along with Confirm and Cancel buttons.
     * @param msg
     * @return
     */
    public static Stage confirm(String msg) {
        return instance._confirm(msg);
    }

    /**
     * Implementation for Util.loadFXML
     */
    private Parent _loadFXML(String name) throws IOException {
        if (cache.containsKey(name)) return cache.get(name);

        name = name.endsWith(".fxml") ? name : name + ".fxml";
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/" + name));
        Parent content = loader.load();
        
        cache.put(name, content);
        return content;
    }

    /**
     * Implementation for Util.loadFXMLScene
     */
    private Scene _loadFXMLScene(String name) throws IOException {
        Parent root = _loadFXML(name);
        return new Scene(root);
    }

    /**
     * Implementation for SceneUtil.error
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
            stage.getIcons().add(new Image("/images/red-circle.png"));
            stage.setScene(scene);
            stage.setResizable(false);

            // Makes it so that error popup must be dealt
            // with before being able to interact with the
            // rest of the app
            stage.initModality(Modality.APPLICATION_MODAL);

            return stage;
        } catch (Exception e) {
            // If there is an error, log it and stop the application.
            Log.error(e.getMessage());
            System.exit(1);
            return null;
        }
    }

    /**
     * Implementation for SceneUtil.confirm
     */
    public Stage _confirm(String msg) {
        try {
            Stage stage = new Stage();
            // Load fxml file
            Scene scene = _loadFXMLScene("Confirm");
            // Cast parent to pane, so that we can access the child node
            Pane pane = (Pane)scene.getRoot();
            // get child node and cast it to Vbox
            VBox vbox = (VBox)(pane.getChildren().get(0));
            // repeat to get to Text
            Text text = (Text)(vbox.getChildren().get(0));

            text.setText(msg);
            stage.setTitle("Confirm:");
            stage.getIcons().add(new Image("/images/yellow-circle.png"));
            stage.setScene(scene);
            stage.setResizable(false);

            // Makes it so that error popup must be dealt
            // with before being able to interact with the
            // rest of the app
            stage.initModality(Modality.APPLICATION_MODAL);

            return stage;
        } catch (Exception e) {
            // If there is an error, log it and stop the application.
            Log.error(e.getMessage());
            System.exit(1);
            return null;
        }
    }
}
