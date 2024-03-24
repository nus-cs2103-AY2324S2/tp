package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.LastContactCommand.SORT_COMPARATOR;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Comparator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.HasLastContactedPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for LastContactCommand.
 */
public class LastContactCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_lastContactListIsNotFiltered_showsSameList() {
        assertCommandSuccess(new LastContactCommand(), model,
                LastContactCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_lastContactListIsFiltered_showsSortedAndFiltered() {
        model.updateFilteredPersonList(new HasLastContactedPredicate());
        model.sortFilteredPersonList(SORT_COMPARATOR);
        expectedModel.updateFilteredPersonList(new HasLastContactedPredicate());
        expectedModel.sortFilteredPersonList(SORT_COMPARATOR);

        assertCommandSuccess(new LastContactCommand(), model,
                LastContactCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void compare_personsWithNullLastContact_datesHandledCorrectly() {
        // Person with a LastContact instance that has a null dateTime
        Person personWithNullDateTime = new PersonBuilder().withLastContact("").build();
        Person personWithValidLastContact = new PersonBuilder().withLastContact("24-03-2024 0935").build();

        // Comparator from LastContactCommand
        Comparator<Person> sortComparator = LastContactCommand.SORT_COMPARATOR;

        // Apply and test the comparator logic
        int comparisonResultWithNullDateTimeFirst = sortComparator.compare(personWithNullDateTime,
                personWithValidLastContact);
        int comparisonResultWithValidLastContactFirst = sortComparator.compare(personWithValidLastContact,
                personWithNullDateTime);

        // Expect personWithNullDateTime to be considered "greater" due to null dateTime, so it's sorted to the end
        assertEquals(1, comparisonResultWithNullDateTimeFirst);
        assertEquals(-1, comparisonResultWithValidLastContactFirst);
    }

    @Test
    public void equals() {

        LastContactCommand lastContactFirstCommand = new LastContactCommand();

        // same object -> returns true
        assertTrue(lastContactFirstCommand.equals(lastContactFirstCommand));

        // same values -> returns true
        LastContactCommand lastContactFirstCommandCopy = new LastContactCommand();
        assertTrue(lastContactFirstCommand.equals(lastContactFirstCommandCopy));

        // different types -> returns false
        assertFalse(lastContactFirstCommand.equals(1));

        // null -> returns false
        assertFalse(lastContactFirstCommand.equals(null));

    }
}
