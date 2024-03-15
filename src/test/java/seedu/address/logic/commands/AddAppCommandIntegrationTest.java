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
 * Contains integration tests (interaction with the Model) for {@code AddAppCommand}.
 */
public class AddAppCommandIntegrationTest {

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

        assertCommandSuccess(new AddAppCommand(validAppointment), model,
                String.format(AddAppCommand.MESSAGE_SUCCESS, Messages.format(validAppointment)),
                expectedModel);
    }

    @Test
    public void execute_appointmentNricNotFound_throwsCommandException() {
        Appointment appointmentWithNoMatchNric = new AppointmentBuilder()
                .withNric("G9876543K").build();
        assertCommandFailure(new AddAppCommand(appointmentWithNoMatchNric), model,
                AddAppCommand.MESSAGE_PATIENT_NOT_FOUND);
    }

    @Test
    public void execute_duplicateAppointment_throwsCommandException() {
        Appointment appointmentInList = model.getAddressBook().getAppointmentList().get(0);
        assertCommandFailure(new AddAppCommand(appointmentInList), model,
                AddAppCommand.MESSAGE_DUPLICATE_APPOINTMENT);
    }
}
