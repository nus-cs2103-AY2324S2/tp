package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.ClassBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.AttendanceStatus;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Attendance;
import seedu.address.testutil.TypicalPersons;

class EditAttendanceCommandTest {

    private Model model;
    private Person personToEdit;
    private Attendance updatedAttendance;
    private EditAttendanceCommand.EditPersonDescriptor descriptor;
    private EditAttendanceCommand editAttendanceCommand;

    @BeforeEach
    public void setUp() {
        // Assuming you have a method that creates a typical address book,
        // and the typical address book has at least one person
        AddressBook typicalAddressBook = TypicalPersons.getTypicalAddressBook();
        model = new ModelManager(typicalAddressBook, new UserPrefs(), new ClassBook());
        personToEdit = model.getFilteredPersonList().get(0); // Get the first person
        updatedAttendance = new Attendance(new AttendanceStatus("02-05-2024", "0"));
        descriptor = new EditAttendanceCommand.EditPersonDescriptor();
        descriptor.setAttendances(updatedAttendance);
        editAttendanceCommand = new EditAttendanceCommand(Index.fromZeroBased(0), descriptor);
    }

//    @Test
//    public void execute_validIndexAndAttendance_success() throws CommandException {
//        CommandResult commandResult = editAttendanceCommand.execute(model);
//        assertEquals(String.format(EditAttendanceCommand.MESSAGE_EDIT_PERSON_SUCCESS, personToEdit),
//                commandResult.getFeedbackToUser());
//        // Further assertions can be made here to ensure that the attendance record was edited correctly
//    }

}
