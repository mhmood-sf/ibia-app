package ibia.app.templating;

import java.io.IOException;

import javafx.scene.Parent;

/**
 * Load scenes from the fxml templates.
 */
public class TemplateEngine {
    /**
     * Loads the Home fxml file as a template, and updates
     * the Recent Conferences button with latest data from
     * the db.
     * @return the updated scene.
     */
    public static Parent loadHome() throws IOException {
        return new HomeTemplate().fill();
    }

    /**
     * Loads the ConferenceTemplate, and updates
     * relevant nodes with data from the db for
     * the given ID.
     * @return the updated scene.
     */
    public static Parent loadConference(String id) throws IllegalArgumentException, IOException {
        return new ConferenceTemplate(id).fill();
    }

    /**
     * Loads the CommitteeTemplate, and updates
     * relevant nodes with data from the db for
     * the given ID.
     * @return the updated scene.
     */
    public static Parent loadCommittee(String id) throws IllegalArgumentException, IOException {
        return new CommitteeTemplate(id).fill();
    }

    /**
     * Loads the DelegateTemplate, and updates
     * relevant nodes with data from the db for
     * the given ID.
     * @return the updated scene.
     */
    public static Parent loadDelegate(String id) throws IllegalArgumentException, IOException {
        return new DelegateTemplate(id).fill();
    }
}
