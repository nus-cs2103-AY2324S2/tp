package seedu.realodex.ui;

import static seedu.realodex.logic.commands.AddCommand.MESSAGE_ADD_HELP;
import static seedu.realodex.logic.commands.ClearCommand.MESSAGE_CLEAR_HELP;
import static seedu.realodex.logic.commands.DeleteCommand.MESSAGE_DELETE_HELP;
import static seedu.realodex.logic.commands.EditCommand.MESSAGE_EDIT_HELP;
import static seedu.realodex.logic.commands.ExitCommand.MESSAGE_EXIT_HELP;
import static seedu.realodex.logic.commands.FilterCommand.MESSAGE_FILTER_HELP;
import static seedu.realodex.logic.commands.ListCommand.MESSAGE_LIST_HELP;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.realodex.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2324s2-cs2103t-w10-1.github.io/tp/";
    public static final String HELP_MESSAGE = "For more information, you may refer to the user guide: "
            + "\n" + USERGUIDE_URL;

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
        helpMessage.setText(MESSAGE_ADD_HELP + "\n"
                + MESSAGE_DELETE_HELP + "\n"
                + MESSAGE_CLEAR_HELP + "\n"
                + MESSAGE_EDIT_HELP + "\n"
                + MESSAGE_LIST_HELP + "\n"
                + MESSAGE_FILTER_HELP + "\n"
                + MESSAGE_EXIT_HELP + "\n"
                + HELP_MESSAGE);
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
}
