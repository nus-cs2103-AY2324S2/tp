package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;

/**
 * Controller for a help page
 */
public class SummaryStatsWindow extends UiPart<Stage> {

    public static final String SUMMARYSTATS_MESSAGE = "The Total Student Count is";

    private static final Logger logger = LogsCenter.getLogger(SummaryStatsWindow.class);
    private static final String FXML = "SummaryStatsWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label summaryMessage;

    private Logic logic;
    private int totalPerson;

    /**
     * Creates a new SummaryStatsWindow.
     *
     * @param root Stage to use as the root of the SummaryStatsWindow.
     */
    public SummaryStatsWindow(Stage root) {
        super(FXML, root);
    }

    /**
     * Creates a new SummaryStatsWindow.
     */
    public SummaryStatsWindow(Logic logic) {
        this(new Stage());
        this.logic = logic;
        totalPerson = logic.getTotalPersons();
        String output = SUMMARYSTATS_MESSAGE + " " + Integer.toString(totalPerson);
        summaryMessage.setText(output);
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
        logger.fine("Showing total Student Count about the application.");
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
        clipboard.setContent(url);
    }
}
