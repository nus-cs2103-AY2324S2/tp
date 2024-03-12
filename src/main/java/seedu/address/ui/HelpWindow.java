package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddPersonCommand;
import seedu.address.logic.commands.DeletePersonCommand;
import seedu.address.logic.commands.EditPersonCommand;
import seedu.address.logic.commands.FindPersonCommand;
import seedu.address.logic.commands.HelpCommand;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2324s2-cs2103t-f13-1.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE_LINK = "For more details, refer to the user guide: " + USERGUIDE_URL;
    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;
    @FXML
    private Label helpMessageLink;
    @FXML
    private VBox helpMessageContainer;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessageLink.setText(HELP_MESSAGE_LINK);

        helpMessageContainer.getChildren().addAll(
                new HelpWindowCommandCard(
                        AddPersonCommand.COMMAND_WORD,
                        AddPersonCommand.MESSAGE_USAGE
                ).getRoot(),
                new HelpWindowCommandCard(
                        DeletePersonCommand.COMMAND_WORD,
                        DeletePersonCommand.MESSAGE_USAGE
                ).getRoot(),
                new HelpWindowCommandCard(
                        EditPersonCommand.COMMAND_WORD,
                        EditPersonCommand.MESSAGE_USAGE
                ).getRoot(),
                new HelpWindowCommandCard(
                        FindPersonCommand.COMMAND_WORD,
                        FindPersonCommand.MESSAGE_USAGE
                ).getRoot(),
                new HelpWindowCommandCard(
                        HelpCommand.COMMAND_WORD,
                        HelpCommand.MESSAGE_USAGE
                ).getRoot()
        );
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
