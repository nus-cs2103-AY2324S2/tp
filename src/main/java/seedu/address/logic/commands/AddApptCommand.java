package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Nric;


/**
 * Creates an AddApptCommand to add the specified {@code Appointment}
 */
public class AddApptCommand extends Command {

    public static final String COMMAND_WORD = "addAppt";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds an appointment for the patient identified by the NRIC given. \n"
            + "Parameters: "
            + PREFIX_NRIC + "NRIC "
            + PREFIX_DATE + "DATE "
            + PREFIX_START_TIME + "START_TIME "
            + PREFIX_END_TIME + "END_TIME "
            + PREFIX_TAG + "APPOINTMENT_TYPE "
            + "[" + PREFIX_NOTE + "NOTE] \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NRIC + "T0123456A "
            + PREFIX_DATE + "2024-02-20 "
            + PREFIX_START_TIME + "11:00 "
            + PREFIX_END_TIME + "11:30 "
            + PREFIX_TAG + "Medical Check-up "
            + PREFIX_NOTE + "May come earlier ";


    public static final String MESSAGE_ADD_APPOINTMENT_SUCCESS = "New appointment added: %1$s";

    public static final String MESSAGE_ADD_DUPLICATE_APPOINTMENT_FAILURE =
            "This appointment already exists in CLInic";

    private final Appointment apptToAdd;

    /**
     * Creates an AddApptCommand to add the specified {@code Appointment}
     */
    public AddApptCommand(Appointment appointment) {
        requireNonNull(appointment);
        this.apptToAdd = appointment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Nric targetNric = apptToAdd.getNric();

        if (!model.hasPersonWithNric(targetNric)) {
            throw new CommandException(Messages.MESSAGE_PATIENT_NRIC_NOT_FOUND);
        }

        if (model.hasAppointment(apptToAdd)) {
            throw new CommandException(MESSAGE_ADD_DUPLICATE_APPOINTMENT_FAILURE);
        }

        model.addAppointment(apptToAdd);
        return new CommandResult(String.format(MESSAGE_ADD_APPOINTMENT_SUCCESS, Messages.format(apptToAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddApptCommand)) {
            return false;
        }

        AddApptCommand otherAddApptCommand = (AddApptCommand) other;
        return apptToAdd.equals(otherAddApptCommand.apptToAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("appointment", apptToAdd)
                .toString();
    }

}

