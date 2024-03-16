package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.Messages.MESSAGE_INVALID_IMPORTANT_DATE_DISPLAYED_INDEX;
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
import seedu.address.model.patient.ImportantDate;
import seedu.address.model.patient.Patient;

public class EditImportantDateCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final ImportantDate validDate = new ImportantDate("Test", "20-02-2022");
    private final ImportantDate secondValidDate = new ImportantDate("SomethingElse", "20-02-2022");
    private final ImportantDate thirdValidDate = new ImportantDate("Test", "20-02-2023");

    @Test
    public void constructor_nullPatientIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditImportantDateCommand(
                null, INDEX_FIRST_EVENT, validDate));
    }

    @Test
    public void constructor_nullEventIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditImportantDateCommand(
                INDEX_FIRST_PATIENT, null, validDate));
    }

    @Test
    public void constructor_nullImportantDate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditImportantDateCommand(
                INDEX_FIRST_PATIENT, INDEX_FIRST_EVENT, null));
    }

    @Test
    public void constructor_nullPatientIndexEventIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditImportantDateCommand(
                null, null, validDate));
    }

    @Test
    public void constructor_nullPatientIndexImportantDate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditImportantDateCommand(
                null, INDEX_FIRST_EVENT, null));
    }

    @Test
    public void constructor_nullEventIndexImportantDate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditImportantDateCommand(
                INDEX_FIRST_PATIENT, null, null));
    }

    @Test
    public void constructor_nullPatientIndexEventIndexImportantDate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditImportantDateCommand(
                null, null, null));
    }

    @Test
    public void execute_editImportantDate_success() throws CommandException {
        EditImportantDateCommand editImportantDateCommand = new EditImportantDateCommand(INDEX_SECOND_PATIENT,
                INDEX_FIRST_EVENT, validDate);
        CommandResult result = editImportantDateCommand.execute(model);
        Patient editedPatient = model.getFilteredPatientList().get(INDEX_SECOND_PATIENT.getZeroBased());

        String expected = String.format(EditImportantDateCommand.MESSAGE_SUCCESS, validDate.name,
                INDEX_FIRST_EVENT.getOneBased(), validDate.importantDate, editedPatient.getName(),
                INDEX_SECOND_PATIENT.getOneBased());

        assertEquals(expected, result.getFeedbackToUser());

        Set<ImportantDate> expectedImportantDate = new HashSet<>(editedPatient.getImportantDates());

        assertEquals(editedPatient.getImportantDates(), expectedImportantDate);
    }

    @Test
    public void execute_editImportantDateForInvalidPatientIndex_throwsCommandException() {
        Index invalidPatientIndex = Index.fromOneBased(model.getFilteredPatientList().size() + 1);
        EditImportantDateCommand editImportantDateCommand = new EditImportantDateCommand(
                invalidPatientIndex, INDEX_FIRST_EVENT, validDate);
        CommandException exception = assertThrows(CommandException.class, () ->
                editImportantDateCommand.execute(model));

        assertEquals(MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX, exception.getMessage());
    }

    @Test
    public void execute_editImportantDateWithInvalidEventIndex_throwsCommandException() {
        Index invalidEventIndex = Index.fromOneBased(model.getFilteredPatientList().get(1)
                .getImportantDates().size() + 1);
        EditImportantDateCommand editImportantDateCommand = new EditImportantDateCommand(
                INDEX_FIRST_PATIENT, invalidEventIndex, validDate);
        CommandException exception = assertThrows(CommandException.class, () ->
                editImportantDateCommand.execute(model));

        assertEquals(MESSAGE_INVALID_IMPORTANT_DATE_DISPLAYED_INDEX, exception.getMessage());
    }

    @Test
    public void equals_differentEvent_returnFalse() {
        EditImportantDateCommand editImportantDateCommandFirst = new EditImportantDateCommand(
                INDEX_FIRST_PATIENT, INDEX_FIRST_EVENT, validDate);
        EditImportantDateCommand editImportantDateCommandSecond = new EditImportantDateCommand(
                INDEX_FIRST_PATIENT, INDEX_SECOND_EVENT, validDate);

        // Test same patient, same important date but different event
        assertNotEquals(editImportantDateCommandFirst, editImportantDateCommandSecond);
    }

    @Test
    public void equals_differentPatient_returnFalse() {
        EditImportantDateCommand editImportantDateCommandFirst = new EditImportantDateCommand(
                INDEX_FIRST_PATIENT, INDEX_THIRD_EVENT, validDate);
        EditImportantDateCommand editImportantDateCommandSecond = new EditImportantDateCommand(
                INDEX_SECOND_PATIENT, INDEX_THIRD_EVENT, validDate);

        // Test same event, same important date but different patient
        assertNotEquals(editImportantDateCommandFirst, editImportantDateCommandSecond);
    }

    @Test
    public void equals_differentImportantDate_returnFalse() {
        EditImportantDateCommand editImportantDateCommandFirst = new EditImportantDateCommand(
                INDEX_SECOND_PATIENT, INDEX_SECOND_EVENT, validDate);
        EditImportantDateCommand editImportantDateCommandSecond = new EditImportantDateCommand(
                INDEX_SECOND_PATIENT, INDEX_SECOND_EVENT, secondValidDate);
        EditImportantDateCommand editImportantDateCommandThird = new EditImportantDateCommand(
                INDEX_SECOND_PATIENT, INDEX_SECOND_EVENT, thirdValidDate);

        // Test same patient, same event, same date but different important date name
        assertNotEquals(editImportantDateCommandFirst, editImportantDateCommandSecond);
        // Test same patient, same event, same important date name but different date
        assertNotEquals(editImportantDateCommandFirst, editImportantDateCommandThird);
    }

    @Test
    public void equals_sameValues_returnTrue() {
        EditImportantDateCommand editImportantDateCommandFirst = new EditImportantDateCommand(
                INDEX_THIRD_PATIENT, INDEX_THIRD_EVENT, thirdValidDate);
        EditImportantDateCommand editImportantDateCommandSecond = new EditImportantDateCommand(
                INDEX_THIRD_PATIENT, INDEX_THIRD_EVENT, thirdValidDate);

        // Test with the same object
        assertEquals(editImportantDateCommandFirst, editImportantDateCommandFirst);
        // Test with different object but same values
        assertEquals(editImportantDateCommandFirst, editImportantDateCommandSecond);
    }

    @Test
    public void toStringTest() {
        EditImportantDateCommand editImportantDateCommand =
                new EditImportantDateCommand(INDEX_FIRST_PATIENT, INDEX_FIRST_EVENT, validDate);

        String expected = EditImportantDateCommand.class.getCanonicalName()
                + "{patientIndex=" + INDEX_FIRST_PATIENT
                + ", eventIndex=" + INDEX_FIRST_EVENT
                + ", importantDate=" + validDate + "}";

        assertEquals(editImportantDateCommand.toString(), expected);
    }

}
