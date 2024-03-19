package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.parser.CliSyntax;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;

/**
 * Finds the appointment in the CogniCare appointment list which matches the student and appointment id.
 */
public class FindAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "findappointment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds the appointment which contains "
            + "the specified indexes. Parameters: "
            + CliSyntax.PREFIX_STUDENT_ID + "STUDENT_ID "
            + CliSyntax.PREFIX_APPOINTMENT_ID + "APPOINTMENT_ID\n"
            + "Example: " + COMMAND_WORD + " "
            + CliSyntax.PREFIX_STUDENT_ID + "1 "
            + CliSyntax.PREFIX_APPOINTMENT_ID + "1";

    private final Predicate<Appointment> predicate;

    public FindAppointmentCommand(Predicate<Appointment> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredAppointmentList(predicate);
        return new CommandResult(String.format(Messages.MESSAGE_APPOINTMENTS_LISTED_OVERVIEW,
                model.getFilteredAppointmentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindAppointmentCommand)) {
            return false;
        }

        FindAppointmentCommand otherAppointmentFindCommand = (FindAppointmentCommand) other;
        return predicate.equals(otherAppointmentFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
