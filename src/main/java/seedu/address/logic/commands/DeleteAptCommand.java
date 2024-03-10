package seedu.address.logic.commands;

import seedu.address.model.Model;
import java.time.format.DateTimeFormatter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Appointment;
import java.util.List;

public class DeleteAptCommand extends Command {
    public static final String COMMAND_WORD = "deleteApt";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes an appointment of a patient by the index "
            + "in the list and patient name.\n"
            + "Parameters: INDEX (must be a positive integer) /name NAME\n"
            + "Example: " + COMMAND_WORD + " 1 /name John Doe";

    private final int index;
    private final String patientName;

    public DeleteAptCommand(int index, String patientName) {
        this.index = index;
        this.patientName = patientName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Appointment> lastShownList = model.getFilteredAppointmentList();

        if (index < 1 || index > lastShownList.size()) {
            throw new CommandException("OOPS! The deletion of the appointment failed as the index of appointment is out of range.");
        }

        Appointment appointmentToDelete = lastShownList.get(index - 1);
        if (!appointmentToDelete.getPatientName().equals(patientName)) {
            throw new CommandException("OOPS! The deletion of the appointment failed as the appointment of " + patientName + " does not exist in the appointment list.");
        }

        model.deleteAppointment(appointmentToDelete);
        return new CommandResult(String.format("Deleted the appointment successfully:\nName: %s\nTime: %s",
                patientName, appointmentToDelete.getDateTime().format(DateTimeFormatter.ofPattern("d MMM uuuu HH:mm"))));
    }
}
