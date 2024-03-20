package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_APPOINTMENTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_4;
import static seedu.address.testutil.TypicalAppointments.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.AppointmentContainsPatientPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

public class QueryPatientAppointmentCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        AppointmentContainsPatientPredicate firstPredicate =
                new AppointmentContainsPatientPredicate(Collections.singletonList("first"));
        AppointmentContainsPatientPredicate secondPredicate =
                new AppointmentContainsPatientPredicate(Collections.singletonList("second"));

        QueryPatientAppointmentCommand findFirstCommand = new QueryPatientAppointmentCommand(firstPredicate);
        QueryPatientAppointmentCommand findSecondCommand = new QueryPatientAppointmentCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        QueryPatientAppointmentCommand findFirstCommandCopy = new QueryPatientAppointmentCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noAppointmentsFound() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 0);
        AppointmentContainsPatientPredicate predicate = preparePredicate(" ");
        QueryPatientAppointmentCommand command = new QueryPatientAppointmentCommand(predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAppointmentList());
    }

    @Test
    public void execute_singleKeyword_singleAppointmentFound() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 1);
        AppointmentContainsPatientPredicate predicate = preparePredicate("S8734985A");
        QueryPatientAppointmentCommand command = new QueryPatientAppointmentCommand(predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(APPOINTMENT_4), model.getFilteredAppointmentList());
    }

    @Test
    public void execute_noMatch_noPatientFound() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 0);
        AppointmentContainsPatientPredicate predicate = preparePredicate("Zorro");
        QueryPatientAppointmentCommand command = new QueryPatientAppointmentCommand(predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAppointmentList());
    }

    @Test
    public void toStringMethod() {
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindCommand findCommand = new FindCommand(predicate);
        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code AppointmentContainsPatientPredicate}.
     */
    private AppointmentContainsPatientPredicate preparePredicate(String userInput) {
        return new AppointmentContainsPatientPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
