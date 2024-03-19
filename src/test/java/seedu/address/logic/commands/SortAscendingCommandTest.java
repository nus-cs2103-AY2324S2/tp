package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;


/**
     * Contains integration tests (interaction with the Model) for {@code SortAscendingCommand}.
 */
public class SortAscendingCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_listIsNotSorted_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_sortAscending_success() {
        SortAscendingCommand sortAscendingCommand = new SortAscendingCommand();
        String expectedMessage = SortAscendingCommand.MESSAGE_SUCCESS;
        CommandResult commandResult = sortAscendingCommand.execute(model);
        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        ObservableList<Person> lastShownList = model.getCorrectPersonList();
        assertTrue(isSortedByStarsAscending(lastShownList));
    }

    /**
     * Helper method to check if the list of persons is sorted by stars in ascending order.
     */
    private boolean isSortedByStarsAscending(ObservableList<Person> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            int currentStars = list.get(i).getStarCount();
            int nextStars = list.get(i + 1).getStarCount();
            if (currentStars > nextStars) {
                return false;
            }
        }
        return true;
    }
}
