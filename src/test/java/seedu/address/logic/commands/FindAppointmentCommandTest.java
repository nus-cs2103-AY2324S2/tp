package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_APPOINTMENTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentList;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPOINTMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_APPOINTMENT;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.FindAppointmentPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindAppointmentCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalAppointmentList(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(),
            getTypicalAppointmentList(), new UserPrefs());

    @Test
    public void equals() {
        FindAppointmentPredicate firstPredicate =
                new FindAppointmentPredicate(INDEX_FIRST_PERSON.getOneBased(), INDEX_FIRST_APPOINTMENT.getOneBased());
        FindAppointmentPredicate secondPredicate =
                new FindAppointmentPredicate(INDEX_FIRST_PERSON.getOneBased(), INDEX_SECOND_APPOINTMENT.getOneBased());

        FindAppointmentCommand findFirstCommand = new FindAppointmentCommand(firstPredicate);
        FindAppointmentCommand findSecondCommand = new FindAppointmentCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindAppointmentCommand findFirstCommandCopy = new FindAppointmentCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_invalidIds_noAppointmentFound() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 0);
        FindAppointmentPredicate predicate = new FindAppointmentPredicate(-1, -1);
        FindAppointmentCommand command = new FindAppointmentCommand(predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAppointmentList());
    }

    @Test
    public void toStringMethod() {
        FindAppointmentPredicate predicate = new FindAppointmentPredicate(
                INDEX_FIRST_PERSON.getOneBased(), INDEX_FIRST_APPOINTMENT.getOneBased());
        FindAppointmentCommand findAppointmentCommand = new FindAppointmentCommand(predicate);
        String expected = FindAppointmentCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findAppointmentCommand.toString());
    }

}
