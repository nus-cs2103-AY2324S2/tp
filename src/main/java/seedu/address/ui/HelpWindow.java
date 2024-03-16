package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://se-education.org/addressbook-level3/UserGuide.html";
    public static final String HELP_MESSAGE = "For more detail, refer to the user guide:\n" + USERGUIDE_URL
            + "\n\nThings to note on:"
            + "\n  • Items in square brackets are optional."
            + "\n  • Items with '...' after them can be used multiple times including zero times."
            + "\n  • Close this window by typing 'q' on keyboard.";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    @FXML
    private TableView<UserGuideItem> userGuideTable;

    @FXML
    private TableColumn<UserGuideItem, String> commandColumn;

    @FXML
    private TableColumn<UserGuideItem, String> usageColumn;

    private final ObservableList<UserGuideItem> guideItems = FXCollections.observableArrayList();


    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
        commandColumn.setCellValueFactory(new PropertyValueFactory<>("command"));
        usageColumn.setCellValueFactory(new PropertyValueFactory<>("usage"));
        fillCommandSummaryTable();
        userGuideTable.setItems(guideItems);

        Scene scene = getRoot().getScene();
        if (scene != null) {
            scene.addEventFilter(KeyEvent.KEY_PRESSED, this::handleKeyPressed);
        }
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
     * Fills the command summary table with all available commands.
     */
    private void fillCommandSummaryTable() {
        guideItems.add(new UserGuideItem("help", "Call this user guide.\n"
                + "Can also call by typing F1 on keyboard"));
        guideItems.add(new UserGuideItem("add", "Add a new contact.\n"
                + "add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…\n"
                + "e.g., add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/supplier "
                + "t/seafood"));
        guideItems.add(new UserGuideItem("list", "List all contacts.\n"));
        guideItems.add(new UserGuideItem("edit", "Edit a contact.\n"
                + "edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…\n"
                + "e.g., edit 1 p/91234567 e/johndoe@example.com"));
        guideItems.add(new UserGuideItem("find", "Find contacts with a keyword.\n"
                + "find KEYWORD [MORE_KEYWORDS]\n"
                + "e.g., find John"));
        guideItems.add(new UserGuideItem("filter", "Filter contacts with specified tags.\n"
                + "filter TAG [MORE_TAGS]\n"
                + "e.g., filter supplier"));
        guideItems.add(new UserGuideItem("delete", "Delete a contact.\n"
                + "delete INDEX\n"
                + "e.g., delete 1"));
        guideItems.add(new UserGuideItem("clear", "Clear every recorded contacts.\n"
                + "A confirmation message will be shown, type y to proceed with clearing "
                + "or otherwise to cancel clearing."));
        guideItems.add(new UserGuideItem("exit", "Close the address book."));
    }

    /**
     * Handles the key pressed event to close the help window when "q" is pressed.
     * @param event The key event.
     */
    private void handleKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.Q) {
            hide();
            event.consume();
        }
    }
}
