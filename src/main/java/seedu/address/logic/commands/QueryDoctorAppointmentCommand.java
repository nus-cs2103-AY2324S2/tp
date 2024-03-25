package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.appointment.AppointmentContainsDoctorPredicate;

/**
 * Represents a command for querying appointments for a specific doctor.
 * The command searches for appointments of doctors whose NRICs/names contain any of the specified keywords
 * (case-insensitive) and displays them as a list with index numbers.
 */
public class QueryDoctorAppointmentCommand extends Command {
    public static final String COMMAND_WORD = "appfordoctor";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all appointments of doctors whose "
            + "nrics/names contain any of the specified keywords (case-insensitive) and displays them as a "
            + "list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n (Keywords can either be NRICs or Names)"
            + "Example: " + COMMAND_WORD + " alice bob T1234567A S7654321A";

    private final AppointmentContainsDoctorPredicate predicate;

    public QueryDoctorAppointmentCommand(AppointmentContainsDoctorPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredAppointmentList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_APPOINTMENTS_LISTED_OVERVIEW,
                        model.getFilteredAppointmentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof QueryDoctorAppointmentCommand)) {
            return false;
        }

        QueryDoctorAppointmentCommand otherQueryDoctorAppointmentCommand = (QueryDoctorAppointmentCommand) other;
        return predicate.equals(otherQueryDoctorAppointmentCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
