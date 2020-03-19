package ibia.app;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
 * Provides some common utility functions.
 */
public class Util {
    // Don't mind the wonky code, doing it this way helps
    // provide a better interface through the static methods
    // instead of having to create an instance everytime.
    private static Util instance = new Util();

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
     * Returns a Scene loaded from one of the fxml files in
     * resources/fxml based on the `name` parameter.
     * 
     * Example:
     * Scene welcomeScene = Util.loadFXMLScene("Welcome", 1000, 600);
     */
    public static Scene loadFXMLScene(String name, int width, int height) throws Exception {
        return instance._loadFXMLScene(name, width, height);
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
    public FXMLLoader _loadFXML(String name) {
        name = name.endsWith(".fxml") ? name : name + ".fxml";
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/" + name));
        return loader;
    }

    /*
     * Implementation for Util.loadFXMLScene
     */
    public Scene _loadFXMLScene(String name, int width, int height) throws Exception {
        FXMLLoader loader = loadFXML(name);
        Parent content = loader.load();
        return new Scene(content, width, height);
    }

    /*
     * Implementation for Util.error
     */
    public Stage _error(String msg) {
        try {
            Stage stage = new Stage();
            // Load fxml file
            Scene scene = _loadFXMLScene("Error", 300, 80);
            // Cast parent to pane, so that we can access the child node
            Pane pane = (Pane)scene.getRoot();
            // get child node and cast it to Text type
            Text text = (Text)(pane.getChildren().get(0));

            text.setText(msg);
            stage.setTitle("Error:");
            stage.getIcons().add(new Image("/error-icon.png"));
            stage.setScene(scene);
    
            stage.setResizable(false);
            return stage;
        } catch (Exception e) {
            System.out.println(e);
            System.exit(1);
            return null; // Apparently Java can't tell that this line is unreachable.
        }
    }
}
