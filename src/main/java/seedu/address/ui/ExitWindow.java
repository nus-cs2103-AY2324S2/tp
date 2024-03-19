package seedu.address.ui;

import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * An exit window that seeks confirmation for program exit.
 */
public class ExitWindow extends UiPart<Stage> {

    public static final String EXIT_MESSAGE = "Are you sure you want to exit? Press ENTER to confirm.";

    private static final Logger logger = LogsCenter.getLogger(ExitWindow.class);
    private static final String FXML = "ExitWindow.fxml";

    @FXML
    private Label exitMessage;

    @FXML
    private Button yesButton;

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
     * Creates a new ExitWindow.
     */
    public ExitWindow() {
        this(new Stage());
    }

    /**
     * Shows the exit window.
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
     * Exits from the program.
     */
    @FXML
    private void yesButton() {
        Platform.exit();
    }

    /**
     * Hides the exit window.
     */
    @FXML
    private void noButton() {
        getRoot().hide();
        yesButton.requestFocus();
    }
}
