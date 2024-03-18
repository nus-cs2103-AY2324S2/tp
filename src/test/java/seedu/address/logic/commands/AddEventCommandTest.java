package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PATIENT;
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

public class AddEventCommandTest {
    private final Event validDate = new Event("Test", "20-02-2022");
    private final Event secondValidDate = new Event("SomethingElse", "20-02-2022");
    private final Event thirdValidDate = new Event("Test", "20-02-2023");

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddEventCommand(null, validDate));
    }

    @Test
    public void constructor_nullEventDateTimeStr_throwsNullPointerException() {
        Index index = INDEX_FIRST_PATIENT;
        assertThrows(NullPointerException.class, () -> new AddEventCommand(index, null));
    }

    @Test
    public void constructor_nullIndexAndEventDateTimeStr_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddEventCommand(null, null));
    }

    @Test
    public void execute_addValidEventToInvalidPatient_throwsCommandException() {
        Index invalidIndex = Index.fromOneBased(model.getFilteredPatientList().size() + 1);
        AddEventCommand addEventCommand = new AddEventCommand(invalidIndex, validDate);

        CommandException exception = assertThrows(CommandException.class, () -> addEventCommand.execute(model));
        assertEquals(MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX, exception.getMessage());
    }

    @Test
    public void execute_addValidEvent_success() throws CommandException {
        Index validIndex = Index.fromZeroBased(1);
        AddEventCommand addEventCommand = new AddEventCommand(validIndex, validDate);
        CommandResult result = addEventCommand.execute(model);

        Patient editedPatient = model.getFilteredPatientList().get(validIndex.getZeroBased());
        String expected = String.format(AddEventCommand.MESSAGE_SUCCESS, validDate.name,
                editedPatient.getName(), validIndex.getOneBased(), validDate.date);
        assertEquals(expected, result.getFeedbackToUser());

        Set<Event> expectedEvents = new HashSet<>(editedPatient.getEvents());
        assertTrue(editedPatient.getEvents().equals(expectedEvents));
    }

    @Test
    public void equalsTest() {
        AddEventCommand addEventCommandFirst = new AddEventCommand(
                INDEX_FIRST_PATIENT, validDate);
        AddEventCommand addEventCommandSecond = new AddEventCommand(
                INDEX_SECOND_PATIENT, validDate);
        AddEventCommand addEventCommandThird = new AddEventCommand(
                INDEX_FIRST_PATIENT, secondValidDate);
        AddEventCommand addEventCommandFourth = new AddEventCommand(
                INDEX_FIRST_PATIENT, thirdValidDate);


        assertFalse(addEventCommandFirst.equals(addEventCommandSecond));
        assertFalse(addEventCommandFirst.equals(addEventCommandThird));
        assertFalse(addEventCommandFirst.equals(addEventCommandFourth));

        assertTrue(addEventCommandFirst.equals(addEventCommandFirst));
    }

    @Test
    public void toStringTest() {
        AddEventCommand addEventCommand = new AddEventCommand(
                INDEX_FIRST_PATIENT, validDate);

        String expected = AddEventCommand.class.getCanonicalName() + "{index=" + INDEX_FIRST_PATIENT
                + ", event=" + validDate + "}";

        assertTrue(addEventCommand.toString().equals(expected));
    }
}
