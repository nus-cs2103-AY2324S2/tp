package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class MeetingsWindow extends UiPart<Stage> {
    public static final String REMIND_MESSAGE = "Here are your upcoming meetings: ";
    private static final Logger logger = LogsCenter.getLogger(MeetingsWindow.class);
    private static final String FXML = "MeetingsWindow.fxml";

    @FXML
    private Label meetingsMessage;

    /**
     * Creates a new MeetingsWindow.
     *
     * @param root Stage to use as the root of the MeetingsWindow.
     */
    public MeetingsWindow(Stage root) {
        super(FXML, root);
        meetingsMessage.setText(REMIND_MESSAGE);
    }

    /**
     * Creates a new MeetingsWindow with a new Stage.
     */
    public MeetingsWindow() {
        this(new Stage());
    }

    /**
     * Shows the meetings window.
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
        logger.fine("Showing meetings window for the application.");
        getRoot().show();
        getRoot().centerOnScreen();
        closeOnEsc();
    }

    /**
     * Returns true if the meetings window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the meetings window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the meetings window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Closes the window when user presses "Esc" key.
     */
    public void closeOnEsc() {
        getRoot().addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                hide();
            }
        });
    }
}
