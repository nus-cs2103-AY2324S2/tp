package vitalconnect.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import vitalconnect.model.Appointment;
import vitalconnect.model.Model;


/**
 * Represents a command to list all appointments in the address book.
 * This command allows users to view all scheduled appointments, sorted in the order they were added.
 */
public class ListAptCommand extends Command {

    public static final String COMMAND_WORD = "listApt";
    public static final String MESSAGE_SUCCESS = "Listed all appointments";

    /**
     * Executes the command to list all appointments in the address book.
     * <p>
     * If no appointments are present in the address book, a message indicating
     * that no appointments are in the list is returned. Otherwise, all appointments
     * are listed, each prefixed with an index for easy reference.
     *
     * @param model {@code Model} which the command should operate on.
     * @return A {@code CommandResult} object containing the list of appointments if any,
     *         or a message indicating that no appointments are in the list.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Appointment> lastShownList = model.getFilteredAppointmentList();

        if (lastShownList.size() < 1) {
            return new CommandResult("No appointment is in the list.",
                    false, false, CommandResult.Type.SHOW_APPOINTMENTS);

        }
        return new CommandResult(MESSAGE_SUCCESS,
                false, false, CommandResult.Type.SHOW_APPOINTMENTS);
    }
}

