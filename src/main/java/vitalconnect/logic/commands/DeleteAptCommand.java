package vitalconnect.logic.commands;

import java.time.format.DateTimeFormatter;
import java.util.List;

import vitalconnect.logic.commands.exceptions.CommandException;
import vitalconnect.model.Appointment;
import vitalconnect.model.Model;


/**
 * A command to delete an appointment of a patient from the address book based on the index provided.
 * This command allows users to remove a specific appointment identified by its index in the
 * list of all displayed appointments and the patient's name.
 */
public class DeleteAptCommand extends Command {
    public static final String COMMAND_WORD = "deleteApt";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes an appointment of a patient by the index "
            + "in the list and patient name.\n"
            + "Parameters: INDEX (must be a positive integer) /name NAME\n"
            + "Example: " + COMMAND_WORD + " 1 /name John Doe";

    private final int index;
    private final String patientName;

    /**
     * Constructs a {@code DeleteAptCommand} with the specified index and patient name.
     *
     * @param index The index of the appointment to be deleted, as displayed to the user.
     * @param patientName The name of the patient whose appointment is to be deleted.
     */
    public DeleteAptCommand(int index, String patientName) {
        this.index = index;
        this.patientName = patientName;
    }

    /**
     * Executes the deletion of an appointment identified by its index and the specified patient's name.
     * <p>
     * The method checks if the appointment list is empty, if the provided index is within the valid range,
     * and if the appointment at the specified index belongs to the patient with the given name. If any of
     * these conditions are not met, a {@code CommandException} is thrown with an appropriate message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return A {@code CommandResult} object containing the success message upon
     *         successful deletion of the appointment.
     * @throws CommandException If the appointment list is empty, the index is out of range,
     *                          or no appointment matches the provided index and patient name.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Appointment> lastShownList = model.getFilteredAppointmentList();

        if (lastShownList.size() < 1) {
            throw new CommandException("OOPS! The appointment list is empty.");
        }

        if (index < 1 || index > lastShownList.size()) {
            throw new CommandException("OOPS! The deletion of the appointment failed as the index of "
                    + "appointment is out of range.");
        }

        Appointment appointmentToDelete = lastShownList.get(index - 1);
        if (!appointmentToDelete.getPatientName().equals(patientName)) {
            throw new CommandException("OOPS! The deletion of the appointment failed as the appointment of "
                    + patientName + " does not exist in the appointment list.");
        }

        model.deleteAppointment(appointmentToDelete);
        return new CommandResult(String.format("Deleted the appointment successfully:\nName: %s\nTime: %s",
                patientName,
                appointmentToDelete.getDateTime().format(DateTimeFormatter.ofPattern("d MMM uuuu HH:mm"))));
    }
}
