package seedu.address.ui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2324s2-cs2103t-t17-3.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "Available commands:\n"
            + "help \t- Shows this window.\n"
            + "add \t- Adds a person to FitBook.\n"
            + "list \t\t- Shows a list of all persons saved in FitBook.\n"
            + "edit \t- Edits an existing person in FitBook.\n"
            + "find \t- Finds persons whose names contain any of the given keywords.\n"
            + "delete \t- Deletes the specified person from FitBook.\n"
            + "clear \t- Clears all entries from FitBook. USE WITH CAUTION.\n"
            + "exit \t- Exits FitBook.\n"
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
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
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
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
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
