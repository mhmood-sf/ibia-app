package ibia.app.templating;

import java.io.IOException;

import javafx.scene.Scene;

/**
 * Load scenes from the fxml templates.
 */
public class TemplateEngine {
    /**
     * 
     * @return The updated Home scene.
     * @throws Exception
     */
    public static Scene loadHome() throws Exception {
        return new HomeTemplate().fill();
    }

    public static Scene loadConference(String id) throws IllegalArgumentException, IOException {
        return new ConferenceTemplate(id).fill();
    }

    public static Scene loadCommittee(String id) throws IllegalArgumentException, IOException {
        return new CommitteeTemplate(id).fill();
    }

    public static Scene loadDelegate(String id) throws IllegalArgumentException, IOException {
        return new DelegateTemplate(id).fill();
    }
}
