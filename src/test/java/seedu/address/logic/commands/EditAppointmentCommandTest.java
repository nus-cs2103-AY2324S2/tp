package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showAppointmentAtIndex;
import static seedu.address.testutil.TypicalAppointments.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPOINTMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_APPOINTMENT;
import static seedu.address.testutil.TypicalPersons.BROWN;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDate;
import seedu.address.model.person.Doctor;
import seedu.address.testutil.AppointmentBuilder;
import seedu.address.testutil.EditAppointmentDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditAppointmentCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Appointment editedAppointment = new AppointmentBuilder().withDoctor((Doctor) BROWN).build();
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder(editedAppointment).build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(INDEX_FIRST_APPOINTMENT, descriptor);

        String expectedMessage = String.format(EditAppointmentCommand.MESSAGE_EDIT_APPOINTMENT_SUCCESS,
                Messages.format(editedAppointment));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setAppointment(model.getFilteredAppointmentList().get(0), editedAppointment);

        assertCommandSuccess(editAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastAppointment = Index.fromOneBased(model.getFilteredAppointmentList().size());
        Appointment lastAppointment = model.getFilteredAppointmentList().get(indexLastAppointment.getZeroBased());

        AppointmentBuilder appointmentInList = new AppointmentBuilder(lastAppointment);
        Appointment editedAppointment = appointmentInList.withDate(VALID_DATE).build();

        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder().withDate(VALID_DATE).build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(indexLastAppointment, descriptor);

        String expectedMessage = String.format(EditAppointmentCommand.MESSAGE_EDIT_APPOINTMENT_SUCCESS,
                Messages.format(editedAppointment));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setAppointment(lastAppointment, editedAppointment);

        assertCommandSuccess(editAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditAppointmentCommand editAppointmentCommand =
                new EditAppointmentCommand(INDEX_FIRST_APPOINTMENT, new EditAppointmentDescriptor());
        Appointment editedAppointment = model.getFilteredAppointmentList().get(INDEX_FIRST_APPOINTMENT.getZeroBased());

        String expectedMessage = String.format(
                EditAppointmentCommand.MESSAGE_EDIT_APPOINTMENT_SUCCESS, Messages.format(editedAppointment));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showAppointmentAtIndex(model, INDEX_FIRST_APPOINTMENT);

        Appointment appointmentInFilteredList = model.getFilteredAppointmentList()
                .get(INDEX_FIRST_APPOINTMENT.getZeroBased());
        Appointment editedAppointment = new AppointmentBuilder(appointmentInFilteredList).withDate(VALID_DATE).build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(INDEX_FIRST_APPOINTMENT,
                new EditAppointmentDescriptorBuilder().withDate(VALID_DATE).build());

        String expectedMessage = String.format(EditAppointmentCommand.MESSAGE_EDIT_APPOINTMENT_SUCCESS,
                Messages.format(editedAppointment));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setAppointment(model.getFilteredAppointmentList().get(0), editedAppointment);

        assertCommandSuccess(editAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidAppointmentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAppointmentList().size() + 1);
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder().withDate(VALID_DATE).build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editAppointmentCommand, model, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidAppointmentIndexFilteredList_failure() {
        showAppointmentAtIndex(model, INDEX_FIRST_APPOINTMENT);
        Index outOfBoundIndex = INDEX_SECOND_APPOINTMENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getAppointmentList().size());

        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(outOfBoundIndex,
                new EditAppointmentDescriptorBuilder().withDate(VALID_DATE).build());

        assertCommandFailure(editAppointmentCommand, model, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditAppointmentCommand standardAppointmentCommand =
                new EditAppointmentCommand(INDEX_FIRST_APPOINTMENT, new EditAppointmentDescriptor());

        // same values -> returns true
        EditAppointmentDescriptor copyDescriptor = new EditAppointmentDescriptor();
        EditAppointmentCommand commandWithSameValues =
                new EditAppointmentCommand(INDEX_FIRST_APPOINTMENT, copyDescriptor);
        assertTrue(standardAppointmentCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardAppointmentCommand.equals(standardAppointmentCommand));

        // null -> returns false
        assertFalse(standardAppointmentCommand.equals(null));

        // different types -> returns false
        assertFalse(standardAppointmentCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardAppointmentCommand.equals(
                new EditAppointmentCommand(INDEX_SECOND_APPOINTMENT, new EditAppointmentDescriptor())));

        // different descriptor -> returns false
        EditAppointmentDescriptor differentDescriptor = new EditAppointmentDescriptor();
        differentDescriptor.setDate(new AppointmentDate("3000-03-03"));
        assertFalse(standardAppointmentCommand.equals(
                new EditAppointmentCommand(INDEX_FIRST_APPOINTMENT, differentDescriptor)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditAppointmentDescriptor editAppointmentDescriptor = new EditAppointmentDescriptor();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(index, editAppointmentDescriptor);
        String expected = EditAppointmentCommand.class.getCanonicalName() + "{index=" + index
                + ", editAppointmentDescriptor=" + editAppointmentDescriptor + "}";
        assertEquals(expected, editAppointmentCommand.toString());
    }

}
