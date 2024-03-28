package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_APPOINTMENTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_1;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_2;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_3;
import static seedu.address.testutil.TypicalAppointments.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.AppointmentContainsDoctorPredicate;

public class QueryDoctorAppointmentCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        AppointmentContainsDoctorPredicate firstPredicate =
                new AppointmentContainsDoctorPredicate(Collections.singletonList("first"));
        AppointmentContainsDoctorPredicate secondPredicate =
                new AppointmentContainsDoctorPredicate(Collections.singletonList("second"));

        QueryDoctorAppointmentCommand findFirstCommand = new QueryDoctorAppointmentCommand(firstPredicate);
        QueryDoctorAppointmentCommand findSecondCommand = new QueryDoctorAppointmentCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        QueryDoctorAppointmentCommand findFirstCommandCopy = new QueryDoctorAppointmentCommand(firstPredicate);
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
        AppointmentContainsDoctorPredicate predicate = preparePredicate(" ");
        QueryDoctorAppointmentCommand command = new QueryDoctorAppointmentCommand(predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAppointmentList());
    }

    @Test
    public void execute_singleKeyword_singleAppointmentFound() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 3);
        AppointmentContainsDoctorPredicate predicate = preparePredicate("S2378593A");
        QueryDoctorAppointmentCommand command = new QueryDoctorAppointmentCommand(predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(APPOINTMENT_1, APPOINTMENT_2, APPOINTMENT_3), model.getFilteredAppointmentList());
    }

    @Test
    public void execute_noMatch_noPatientFound() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 0);
        AppointmentContainsDoctorPredicate predicate = preparePredicate("Zorro");
        QueryDoctorAppointmentCommand command = new QueryDoctorAppointmentCommand(predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAppointmentList());
    }

    @Test
    public void toStringMethod() {
        AppointmentContainsDoctorPredicate predicate = new AppointmentContainsDoctorPredicate(
                Arrays.asList("keyword"));
        QueryDoctorAppointmentCommand findCommand = new QueryDoctorAppointmentCommand(predicate);
        String expected = QueryDoctorAppointmentCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code AppointmentContainsDoctorPredicate}.
     */
    private AppointmentContainsDoctorPredicate preparePredicate(String userInput) {
        return new AppointmentContainsDoctorPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
