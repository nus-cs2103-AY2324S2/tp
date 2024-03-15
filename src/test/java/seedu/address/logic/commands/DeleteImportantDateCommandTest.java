package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX;
import static seedu.address.logic.commands.DeleteImportantDateCommand.MESSAGE_DELETE_IMPORTANT_DATE_SUCCESS;
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
import seedu.address.model.patient.ImportantDate;
import seedu.address.model.patient.Patient;

public class DeleteImportantDateCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullPatientIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteImportantDateCommand(null,
                INDEX_FIRST_EVENT));
    }
    @Test
    public void constructor_nullEventIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteImportantDateCommand(INDEX_FIRST_PATIENT,
                null));
    }

    @Test
    public void constructor_nullPatientAndEventIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteImportantDateCommand(null,
                null));
    }

    @Test
    public void execute_deleteImportantDateToInvalidPatient_throwsCommandException() {
        Index invalidIndex = Index.fromOneBased(model.getFilteredPatientList().size() + 1);
        DeleteImportantDateCommand deleteImportantDateCommand = new DeleteImportantDateCommand(invalidIndex,
                INDEX_FIRST_EVENT);

        CommandException exception = assertThrows(CommandException.class, () -> deleteImportantDateCommand
                .execute(model));
        assertEquals(MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX, exception.getMessage());
    }

    @Test
    public void execute_deleteValidEventIndexFromValidPatient_success() throws CommandException {
        Index validIndex = Index.fromZeroBased(1);
        DeleteImportantDateCommand deleteImportantDateCommand = new DeleteImportantDateCommand(validIndex,
                INDEX_FIRST_EVENT);
        CommandResult result = deleteImportantDateCommand.execute(model);

        Patient editedPatient = model.getFilteredPatientList().get(validIndex.getZeroBased());
        List<ImportantDate> currImportantDatesList = new ArrayList<>(editedPatient.getImportantDates());
        ImportantDate eventToDelete = currImportantDatesList.get(INDEX_FIRST_EVENT.getZeroBased());
        String expected = String.format(MESSAGE_DELETE_IMPORTANT_DATE_SUCCESS,
                Messages.format(editedPatient),
                eventToDelete);
        assertEquals(expected, result.getFeedbackToUser());

        Set<ImportantDate> expectedImportantDates = new HashSet<>(editedPatient.getImportantDates());
        assertTrue(editedPatient.getImportantDates().equals(expectedImportantDates));
    }

    @Test
    public void equalsTest() {
        DeleteImportantDateCommand deleteImportantDateCommandFirst = new DeleteImportantDateCommand(
                INDEX_FIRST_PATIENT, INDEX_FIRST_EVENT);
        DeleteImportantDateCommand deleteImportantDateCommandSecond = new DeleteImportantDateCommand(
                INDEX_SECOND_PATIENT, INDEX_FIRST_EVENT);
        DeleteImportantDateCommand deleteImportantDateCommandThird = new DeleteImportantDateCommand(
                INDEX_FIRST_PATIENT, INDEX_SECOND_EVENT);
        DeleteImportantDateCommand deleteImportantDateCommandFourth = new DeleteImportantDateCommand(
                INDEX_SECOND_PATIENT, INDEX_SECOND_EVENT);


        assertFalse(deleteImportantDateCommandFirst.equals(deleteImportantDateCommandSecond));
        assertFalse(deleteImportantDateCommandFirst.equals(deleteImportantDateCommandThird));
        assertFalse(deleteImportantDateCommandFirst.equals(deleteImportantDateCommandFourth));

        assertTrue(deleteImportantDateCommandFirst.equals(deleteImportantDateCommandFirst));
    }

    @Test
    public void toStringTest() {
        DeleteImportantDateCommand deleteImportantDateCommand = new DeleteImportantDateCommand(
                INDEX_FIRST_PATIENT, INDEX_FIRST_EVENT);

        String expected = DeleteImportantDateCommand.class.getCanonicalName() + "{targetPatientIndex="
                + INDEX_FIRST_PATIENT
                + ", targetEventIndex=" + INDEX_FIRST_EVENT + "}";

        assertTrue(deleteImportantDateCommand.toString().equals(expected));
    }
}
