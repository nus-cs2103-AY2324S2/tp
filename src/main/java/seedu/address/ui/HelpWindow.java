package seedu.address.ui;

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

    public static final String USERGUIDE_URL = "https://ay2324s2-cs2103t-f11-3.github.io/tp/UserGuide.html";
    public static final String COMMON_COMMANDS = "\n\nFor quick help, here are some common commands "
            + "(omit the [] when using!): "
            + "\n1. Quick add an applicant/interviewer: add_[applicant/interviewer] n/[name] p/[phone] e/[email] "
            + "\n2. Add an interview: add_interview desc/[description] date/[date] st/[starting time] et/[ending time] "
            + "a/[applicant phone] i/[interviewer phone]"
            + "\n3. Delete an applicant/interviewer: delete_person [phone]"
            + "\n4. Delete an interview: delete_interview [interview index]"
            + "\n5. List applicants/interviewers: list_persons"
            + "\n6. List interviews: list_interviews"
            + "\n7. Edit applicants/interviewers: edit [applicant/interviewer index] n/[newName]..."
            + "\n8. Find person(s) by email/name/phone: find_[email/name/phone] [keyword1] [keyword2]..."
            + "\n9. Filter interview(s) by date: filter_interviews_by_date [date in YYYY-MM-DD]";
    public static final String HELP_MESSAGE = "Refer to our user guide at " + USERGUIDE_URL + " for detailed info "
            + "on how to use Tether." + COMMON_COMMANDS;

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
}
