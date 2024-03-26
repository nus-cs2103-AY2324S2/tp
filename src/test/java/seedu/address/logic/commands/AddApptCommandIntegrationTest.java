package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppointments.getTypicalAddressBookWithAppointments;
import static seedu.address.testutil.TypicalPersons.CARL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.testutil.AppointmentBuilder;


/**
 * Contains integration tests (interaction with the Model) for {@code AddApptCommand}.
 */
public class AddApptCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBookWithAppointments(), new UserPrefs());
    }

    @Test
    public void execute_newAppointment_success() {
        Appointment validAppointment = new AppointmentBuilder()
                .withNric(CARL.getNric().value).build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addAppointment(validAppointment);

        assertCommandSuccess(new AddApptCommand(validAppointment), model,
                String.format(AddApptCommand.MESSAGE_ADD_APPOINTMENT_SUCCESS, Messages.format(validAppointment)),
                expectedModel);
    }

    @Test
    public void execute_appointmentNricNotFound_throwsCommandException() {
        Appointment appointmentWithNoMatchNric = new AppointmentBuilder()
                .withNric("G9876543K").build();
        assertCommandFailure(new AddApptCommand(appointmentWithNoMatchNric), model,
                Messages.MESSAGE_PATIENT_NRIC_NOT_FOUND);
    }

    @Test
    public void execute_duplicateAppointment_throwsCommandException() {
        Appointment appointmentInList = model.getAddressBook().getAppointmentList().get(0);
        assertCommandFailure(new AddApptCommand(appointmentInList), model,
                AddApptCommand.MESSAGE_ADD_DUPLICATE_APPOINTMENT_FAILURE);
    }
}
