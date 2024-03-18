package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX;
import static seedu.address.logic.Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX;
import static seedu.address.logic.commands.DeleteEventCommand.MESSAGE_DELETE_EVENT_SUCCESS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PATIENT;
import static seedu.address.testutil.TypicalPatients.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.patient.Event;
import seedu.address.model.patient.Patient;

public class DeleteEventCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullPatientIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteEventCommand(null,
                INDEX_FIRST_EVENT));
    }
    @Test
    public void constructor_nullEventIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteEventCommand(INDEX_FIRST_PATIENT,
                null));
    }

    @Test
    public void constructor_nullPatientAndEventIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteEventCommand(null,
                null));
    }

    @Test
    public void execute_deleteEventToInvalidPatient_throwsCommandException() {
        Index invalidIndex = Index.fromOneBased(model.getFilteredPatientList().size() + 1);
        DeleteEventCommand deleteEventCommand = new DeleteEventCommand(invalidIndex,
                INDEX_FIRST_EVENT);

        CommandException exception = assertThrows(CommandException.class, () -> deleteEventCommand
                .execute(model));
        assertEquals(MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX, exception.getMessage());
    }

    @Test
    public void execute_deleteEventWithInvalidEventId_throwsCommandException() {
        Index invalidIndex = Index.fromOneBased(model.getFilteredPatientList().get(1)
                .getEvents().size() + 1);
        DeleteEventCommand deleteEventCommand = new DeleteEventCommand(INDEX_FIRST_PATIENT,
                invalidIndex);

        CommandException exception = assertThrows(CommandException.class, () -> deleteEventCommand
                .execute(model));
        assertEquals(MESSAGE_INVALID_EVENT_DISPLAYED_INDEX, exception.getMessage());
    }

    @Test
    public void execute_deleteValidEventIndexFromValidPatient_success() throws CommandException {
        Index validIndex = Index.fromZeroBased(1);
        DeleteEventCommand deleteEventCommand = new DeleteEventCommand(validIndex,
                INDEX_FIRST_EVENT);
        CommandResult result = deleteEventCommand.execute(model);

        Patient editedPatient = model.getFilteredPatientList().get(validIndex.getZeroBased());
        List<Event> currEventList = new ArrayList<>(editedPatient.getEvents());
        Event eventToDelete = currEventList.get(INDEX_FIRST_EVENT.getZeroBased());
        String expected = String.format(MESSAGE_DELETE_EVENT_SUCCESS,
                Messages.format(editedPatient),
                eventToDelete);
        assertEquals(expected, result.getFeedbackToUser());

        Set<Event> expectedEvents = new HashSet<>(editedPatient.getEvents());
        assertTrue(editedPatient.getEvents().equals(expectedEvents));
    }

    @Test
    public void equalsTest() {
        DeleteEventCommand deleteEventCommandFirst = new DeleteEventCommand(
                INDEX_FIRST_PATIENT, INDEX_FIRST_EVENT);
        DeleteEventCommand deleteEventCommandSecond = new DeleteEventCommand(
                INDEX_SECOND_PATIENT, INDEX_FIRST_EVENT);
        DeleteEventCommand deleteEventCommandThird = new DeleteEventCommand(
                INDEX_FIRST_PATIENT, INDEX_SECOND_EVENT);
        DeleteEventCommand deleteEventCommandFourth = new DeleteEventCommand(
                INDEX_SECOND_PATIENT, INDEX_SECOND_EVENT);
        ListCommand listCommand = new ListCommand();

        assertFalse(deleteEventCommandFirst.equals(deleteEventCommandSecond));
        assertFalse(deleteEventCommandFirst.equals(deleteEventCommandThird));
        assertFalse(deleteEventCommandFirst.equals(deleteEventCommandFourth));

        assertFalse(deleteEventCommandFirst.equals(listCommand));

        assertTrue(deleteEventCommandFirst.equals(deleteEventCommandFirst));

    }

    @Test
    public void toStringTest() {
        DeleteEventCommand deleteEventCommand = new DeleteEventCommand(
                INDEX_FIRST_PATIENT, INDEX_FIRST_EVENT);

        String expected = DeleteEventCommand.class.getCanonicalName() + "{targetPatientIndex="
                + INDEX_FIRST_PATIENT
                + ", targetEventIndex=" + INDEX_FIRST_EVENT + "}";

        assertTrue(deleteEventCommand.toString().equals(expected));
    }
}
