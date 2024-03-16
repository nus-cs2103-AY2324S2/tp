package seedu.address.ui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2324s2-cs2103t-t17-3.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "Available commands:\n"
            + "help \t- Shows this window.\n"
            + "add \t\t- Adds a client to FitBook.\n"
            + "list \t\t- Shows a list of all clients saved in FitBook.\n"
            + "edit \t\t- Edits an existing client in FitBook.\n"
            + "note \t- Adds a new note to a client.\n"
            + "find \t- Finds all persons whose specified attribute contains the specified keyword.\n"
            + "delete \t- Deletes the specified client from FitBook.\n"
            + "clear \t- Clears all entries from FitBook. USE WITH CAUTION.\n"
            + "exit \t\t- Exits FitBook.\n"
            + "\n"
            + "To view more information about a specific command, enter the command into the input box and press "
            + "<Enter>.\n"
            + "\n"
            + "Need more help? Refer to the user guide at " + USERGUIDE_URL;

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     *
     * @throws IllegalStateException
     *                               <ul>
     *                               <li>
     *                               if this method is called on a thread other than
     *                               the JavaFX Application Thread.
     *                               </li>
     *                               <li>
     *                               if this method is called during animation or
     *                               layout processing.
     *                               </li>
     *                               <li>
     *                               if this method is called on the primary stage.
     *                               </li>
     *                               <li>
     *                               if {@code dialogStage} is already showing.
     *                               </li>
     *                               </ul>
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Opens user guide URL in user's default browser.
     */
    @FXML
    private void openUrl() {
        try {
            Desktop.getDesktop().browse(new URI(USERGUIDE_URL));
        } catch (URISyntaxException | IOException e) {
            logger.warning("Something went wrong when opening user guide URL.");
        }
    }
}
