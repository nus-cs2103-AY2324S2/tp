package seedu.address.ui;

import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class ExitWindow extends UiPart<Stage> {

    public static final String EXIT_MESSAGE = "Are you sure you want to exit? Data will be saved.";

    private static final Logger logger = LogsCenter.getLogger(ExitWindow.class);
    private static final String FXML = "ExitWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label exitMessage;

    /**
     * Creates a new ExitWindow.
     *
     * @param root Stage to use as the root of the ExitWindow.
     */
    public ExitWindow(Stage root) {
        super(FXML, root);
        exitMessage.setText(EXIT_MESSAGE);
    }

    /**
     * Creates a new HelpWindow.
     */
    public ExitWindow() {
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
        logger.fine("Showing exit confirmation.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the exit window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the exit window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the exit window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void yesButton() {
        Platform.exit();
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private boolean noButton() {
        getRoot().hide();
        return false;
    }
}
