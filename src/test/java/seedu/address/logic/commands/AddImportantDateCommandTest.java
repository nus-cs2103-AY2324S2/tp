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
import seedu.address.model.patient.ImportantDate;
import seedu.address.model.patient.Patient;

public class AddImportantDateCommandTest {
    private final ImportantDate validDate = new ImportantDate("Test", "20-02-2022");
    private final ImportantDate secondValidDate = new ImportantDate("SomethingElse", "20-02-2022");
    private final ImportantDate thirdValidDate = new ImportantDate("Test", "20-02-2023");

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddImportantDateCommand(null, validDate));
    }

    @Test
    public void constructor_nullImportantDate_throwsNullPointerException() {
        Index index = INDEX_FIRST_PATIENT;
        assertThrows(NullPointerException.class, () -> new AddImportantDateCommand(index, null));
    }

    @Test
    public void constructor_nullIndexAndImportantDate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddImportantDateCommand(null, null));
    }

    @Test
    public void execute_addImportantDateToInvalidPatient_throwsCommandException() {
        Index invalidIndex = Index.fromOneBased(model.getFilteredPatientList().size() + 1);
        AddImportantDateCommand addImportantDateCommand = new AddImportantDateCommand(invalidIndex, validDate);

        CommandException exception = assertThrows(CommandException.class, () -> addImportantDateCommand.execute(model));
        assertEquals(MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX, exception.getMessage());
    }

    @Test
    public void execute_addValidImportantDate_success() throws CommandException {
        Index validIndex = Index.fromZeroBased(1);
        AddImportantDateCommand addImportantDateCommand = new AddImportantDateCommand(validIndex, validDate);
        CommandResult result = addImportantDateCommand.execute(model);

        Patient editedPatient = model.getFilteredPatientList().get(validIndex.getZeroBased());
        String expected = String.format(AddImportantDateCommand.MESSAGE_SUCCESS, validDate.name,
                editedPatient.getName(), validIndex, validDate.importantDate);
        assertEquals(expected, result.getFeedbackToUser());

        Set<ImportantDate> expectedImportantDates = new HashSet<>(editedPatient.getImportantDates());
        assertTrue(editedPatient.getImportantDates().equals(expectedImportantDates));
    }

    @Test
    public void equalsTest() {
        AddImportantDateCommand addImportantDateCommandFirst = new AddImportantDateCommand(
                INDEX_FIRST_PATIENT, validDate);
        AddImportantDateCommand addImportantDateCommandSecond = new AddImportantDateCommand(
                INDEX_SECOND_PATIENT, validDate);
        AddImportantDateCommand addImportantDateCommandThird = new AddImportantDateCommand(
                INDEX_FIRST_PATIENT, secondValidDate);
        AddImportantDateCommand addImportantDateCommandFourth = new AddImportantDateCommand(
                INDEX_FIRST_PATIENT, thirdValidDate);


        assertFalse(addImportantDateCommandFirst.equals(addImportantDateCommandSecond));
        assertFalse(addImportantDateCommandFirst.equals(addImportantDateCommandThird));
        assertFalse(addImportantDateCommandFirst.equals(addImportantDateCommandFourth));

        assertTrue(addImportantDateCommandFirst.equals(addImportantDateCommandFirst));
    }

    @Test
    public void toStringTest() {
        AddImportantDateCommand addImportantDateCommand = new AddImportantDateCommand(
                INDEX_FIRST_PATIENT, validDate);

        String expected = AddImportantDateCommand.class.getCanonicalName() + "{index=" + INDEX_FIRST_PATIENT
                + ", importantDate=" + validDate + "}";

        assertTrue(addImportantDateCommand.toString().equals(expected));
    }
}
