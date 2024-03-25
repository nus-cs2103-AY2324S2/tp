package seedu.address.ui;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.person.Person;

/**
 * Controller for a help page
 */
public class MeetingsWindow extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(MeetingsWindow.class);
    private static final String FXML = "MeetingsWindow.fxml";
    private final Logic logic;

    @FXML
    private Label meetingsMessage;

    /**
     * Creates a new MeetingsWindow.
     *
     * @param root Stage to use as the root of the MeetingsWindow.
     */
    public MeetingsWindow(Stage root, Logic logic) {
        super(FXML, root);
        this.logic = logic;
    }

    /**
     * Creates a new MeetingsWindow with a new Stage.
     */
    public MeetingsWindow(Logic logic) {
        this(new Stage(), logic);
    }

    /**
     * Shows the meetings window and displays the list of meetings.
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
    public void displayMeetings() {
        logger.fine("Showing meetings window for the application.");
        getRoot().show();
        getRoot().centerOnScreen();

        List<Person> people = logic.getFilteredPersonList();
        people = people.stream()
                .filter(p -> p.getMeeting() != null)
                .collect(Collectors.toList());
        people.sort(Comparator.comparing(Person::getMeeting));
        StringBuilder sb = new StringBuilder();
        sb.append("Here are all the meetings in chronological order: \n");
        int count = 1;
        for (Person p : people) {
            sb.append(count).append("  |  ").append(p.getMeeting().toString())
                    .append(" with: ").append(p.getName()).append("\n");
            count++;
        }
        meetingsMessage.setText(sb.toString());
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
