package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX;
import static seedu.address.logic.Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PATIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PATIENT;
import static seedu.address.testutil.TypicalPatients.getTypicalAddressBook;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.patient.Event;
import seedu.address.model.patient.Patient;

public class EditEventCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Event validEvent = new Event("Test", "20-02-2022");
    private final Event secondValidEvent = new Event("SomethingElse", "20-02-2022");
    private final Event thirdValidEvent = new Event("Test", "20-02-2023");

    @Test
    public void constructor_nullPatientIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditEventCommand(
                null, INDEX_FIRST_EVENT, validEvent));
    }

    @Test
    public void constructor_nullEventIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditEventCommand(
                INDEX_FIRST_PATIENT, null, validEvent));
    }

    @Test
    public void constructor_nullDate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditEventCommand(
                INDEX_FIRST_PATIENT, INDEX_FIRST_EVENT, null));
    }

    @Test
    public void constructor_nullPatientIndexEventIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditEventCommand(
                null, null, validEvent));
    }

    @Test
    public void constructor_nullPatientIndexDate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditEventCommand(
                null, INDEX_FIRST_EVENT, null));
    }

    @Test
    public void constructor_nullEventIndexDate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditEventCommand(
                INDEX_FIRST_PATIENT, null, null));
    }

    @Test
    public void constructor_nullPatientIndexEventIndexDate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditEventCommand(
                null, null, null));
    }

    @Test
    public void execute_editEvent_success() throws CommandException {
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_SECOND_PATIENT,
                INDEX_FIRST_EVENT, validEvent);
        CommandResult result = editEventCommand.execute(model);
        Patient editedPatient = model.getFilteredPatientList().get(INDEX_SECOND_PATIENT.getZeroBased());

        String expected = String.format(EditEventCommand.MESSAGE_SUCCESS, validEvent.name,
                INDEX_FIRST_EVENT.getOneBased(), validEvent.date, editedPatient.getName(),
                INDEX_SECOND_PATIENT.getOneBased());

        assertEquals(expected, result.getFeedbackToUser());

        Set<Event> expectedEvents = new HashSet<>(editedPatient.getEvents());

        assertEquals(editedPatient.getEvents(), expectedEvents);
    }

    @Test
    public void execute_editEventWithInvalidPatientIndex_throwsCommandException() {
        Index invalidPatientIndex = Index.fromOneBased(model.getFilteredPatientList().size() + 1);
        EditEventCommand editEventCommand = new EditEventCommand(
                invalidPatientIndex, INDEX_FIRST_EVENT, validEvent);
        CommandException exception = assertThrows(CommandException.class, () ->
                editEventCommand.execute(model));

        assertEquals(MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX, exception.getMessage());
    }

    @Test
    public void execute_editEventWithInvalidEventIndex_throwsCommandException() {
        Index invalidEventIndex = Index.fromOneBased(model.getFilteredPatientList().get(1)
                .getEvents().size() + 1);
        EditEventCommand editEventCommand = new EditEventCommand(
                INDEX_FIRST_PATIENT, invalidEventIndex, validEvent);
        CommandException exception = assertThrows(CommandException.class, () ->
                editEventCommand.execute(model));

        assertEquals(MESSAGE_INVALID_EVENT_DISPLAYED_INDEX, exception.getMessage());
    }

    @Test
    public void equals_differentEventIndex_returnFalse() {
        EditEventCommand editEventCommandFirst = new EditEventCommand(
                INDEX_FIRST_PATIENT, INDEX_FIRST_EVENT, validEvent);
        EditEventCommand editEventCommandSecond = new EditEventCommand(
                INDEX_FIRST_PATIENT, INDEX_SECOND_EVENT, validEvent);

        // Test same patient index, same event but different event index
        assertNotEquals(editEventCommandFirst, editEventCommandSecond);
    }

    @Test
    public void equals_differentPatientIndex_returnFalse() {
        EditEventCommand editEventCommandFirst = new EditEventCommand(
                INDEX_FIRST_PATIENT, INDEX_THIRD_EVENT, validEvent);
        EditEventCommand editEventCommandSecond = new EditEventCommand(
                INDEX_SECOND_PATIENT, INDEX_THIRD_EVENT, validEvent);

        // Test same event index, same event but different patient index
        assertNotEquals(editEventCommandFirst, editEventCommandSecond);
    }

    @Test
    public void equals_differentEvent_returnFalse() {
        EditEventCommand editEventCommandFirst = new EditEventCommand(
                INDEX_SECOND_PATIENT, INDEX_SECOND_EVENT, validEvent);
        EditEventCommand editEventCommandSecond = new EditEventCommand(
                INDEX_SECOND_PATIENT, INDEX_SECOND_EVENT, secondValidEvent);
        EditEventCommand editEventCommandThird = new EditEventCommand(
                INDEX_SECOND_PATIENT, INDEX_SECOND_EVENT, thirdValidEvent);

        // Test same patient index, same event index, same event date but different event name
        assertNotEquals(editEventCommandFirst, editEventCommandSecond);
        // Test same patient index, same event index, same event name but different event date
        assertNotEquals(editEventCommandFirst, editEventCommandThird);
    }

    @Test
    public void equals_differentInstance_returnFalse() {
        EditEventCommand editEventCommand = new EditEventCommand(
                INDEX_FIRST_PATIENT, INDEX_FIRST_EVENT, validEvent);
        AddEventCommand addEventCommand = new AddEventCommand(
                INDEX_FIRST_PATIENT, validEvent);
        assertNotEquals(editEventCommand, addEventCommand);
    }

    @Test
    public void equals_sameValues_returnTrue() {
        EditEventCommand editEventCommandFirst = new EditEventCommand(
                INDEX_THIRD_PATIENT, INDEX_THIRD_EVENT, thirdValidEvent);
        EditEventCommand editEventCommandSecond = new EditEventCommand(
                INDEX_THIRD_PATIENT, INDEX_THIRD_EVENT, thirdValidEvent);

        // Test with the same object
        assertEquals(editEventCommandFirst, editEventCommandFirst);
        // Test with different object but same values
        assertEquals(editEventCommandFirst, editEventCommandSecond);
    }

    @Test
    public void toStringTest() {
        EditEventCommand editEventCommand =
                new EditEventCommand(INDEX_FIRST_PATIENT, INDEX_FIRST_EVENT, validEvent);

        String expected = EditEventCommand.class.getCanonicalName()
                + "{patientIndex=" + INDEX_FIRST_PATIENT
                + ", eventIndex=" + INDEX_FIRST_EVENT
                + ", eventToUpdate=" + validEvent + "}";

        assertEquals(editEventCommand.toString(), expected);
    }

}
