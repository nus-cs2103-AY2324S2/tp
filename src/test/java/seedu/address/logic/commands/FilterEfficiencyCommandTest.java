package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.PersonLessThanEfficiencyPredicate;


/**
 * Contains integration tests (interaction with the Model) for {@code FilterEfficiencyCommand}.
 */
public class FilterEfficiencyCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_validPredicate_personsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        PersonLessThanEfficiencyPredicate predicate = preparePredicate("20");
        FilterEfficiencyCommand command = new FilterEfficiencyCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, commandHistory, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FIONA, GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        PersonLessThanEfficiencyPredicate predicate = new PersonLessThanEfficiencyPredicate(Integer.parseInt("20"));
        FilterEfficiencyCommand filterEfficiencyCommand = new FilterEfficiencyCommand(predicate);
        String expected = FilterEfficiencyCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, filterEfficiencyCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code PersonLessThanEfficiencyPredicate}.
     */
    private PersonLessThanEfficiencyPredicate preparePredicate(String userInput) {
        int threshold = Integer.parseInt(userInput);
        return new PersonLessThanEfficiencyPredicate(threshold);
    }
}
