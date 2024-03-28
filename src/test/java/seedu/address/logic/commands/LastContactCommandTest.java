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
        // Create two Person instances with null last contacts
        Person firstPersonWithNullLastContact = new PersonBuilder().withLastContact("").build();
        Person secondPersonWithNullLastContact = new PersonBuilder().withLastContact("").build();
        Person thirdPersonWithValidLastContact = new PersonBuilder().withLastContact("24-03-2024 0935").build();
        boolean isNullLastContact = firstPersonWithNullLastContact.getLastcontact() == null;
        boolean isNotNullLastContact = thirdPersonWithValidLastContact.getLastcontact() != null;

        // Comparator from LastContactCommand
        Comparator<Person> sortComparator = LastContactCommand.SORT_COMPARATOR;

        // Applying the comparator
        int comparisonResult = sortComparator.compare(firstPersonWithNullLastContact, secondPersonWithNullLastContact);
        int comparisonResult2 = sortComparator.compare(firstPersonWithNullLastContact, thirdPersonWithValidLastContact);
        int comparisonResult3 = sortComparator.compare(thirdPersonWithValidLastContact, firstPersonWithNullLastContact);

        // Since both last contact dates are null, we expect the comparator to consider them equal
        assertEquals(0, comparisonResult);
        // Since only first person has Null last contact, we should expect 1
        assertEquals(1, comparisonResult2);
        // Since only second person has Null last contact, we should expect -1
        assertEquals(-1, comparisonResult3);
        // Test for null and non-null LastContact
        assertFalse(isNullLastContact);
        assertTrue(isNotNullLastContact);
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
