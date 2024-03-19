package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;

/**
 * Deletes an appointment identified using it's displayed index from the CogniCare address book.
 */
public class DeleteAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "deleteappointment";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the appointment identified by the index number used in the displayed appointment list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_APPOINTMENT_SUCCESS = "Deleted Appointment: %1$s";

    private final Index targetAppointmentIndex;

    public DeleteAppointmentCommand(Index targetAppointmentIndex) {
        this.targetAppointmentIndex = targetAppointmentIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Appointment> lastShownList = model.getFilteredAppointmentList();

        if (targetAppointmentIndex.getZeroBased() >= Appointment.getIdTracker()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        // We try to find the appointment based on the given appointmentId.
        Optional<Appointment> appointmentToDelete = lastShownList.stream()
                .filter(appointment -> appointment.getAppointmentId() == targetAppointmentIndex.getOneBased())
                .findFirst();

        if (appointmentToDelete.isPresent()) {
            // Delete the appointment if it was successfully found.
            model.deleteAppointment(appointmentToDelete.get());

            return new CommandResult(String.format(MESSAGE_DELETE_APPOINTMENT_SUCCESS,
                    Messages.formatAppointment(appointmentToDelete.get())));

        } else {
            // Otherwise we just throw an error.
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteAppointmentCommand)) {
            return false;
        }

        DeleteAppointmentCommand otherAppointmentDeleteCommand = (DeleteAppointmentCommand) other;
        return targetAppointmentIndex.equals(otherAppointmentDeleteCommand.targetAppointmentIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetAppointmentIndex", targetAppointmentIndex)
                .toString();
    }
}
