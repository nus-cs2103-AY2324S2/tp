package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.appointment.AppointmentContainsPatientPredicate;


public class QueryPatientAppointmentCommand extends Command {
    public static final String COMMAND_WORD = "appforpatient";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all appointments of patients whose "
            + "nrics/names contain any of the specified keywords (case-insensitive) and displays them as a "
            + "list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n (Keywords can either be NRICs or Names)"
            + "Example: " + COMMAND_WORD + " alice bob T1234567A S7654321A";

    private final AppointmentContainsPatientPredicate predicate;

    public QueryPatientAppointmentCommand(AppointmentContainsPatientPredicate predicate) { this.predicate = predicate; }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredAppointmentList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, model.getFilteredAppointmentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof QueryPatientAppointmentCommand)) {
            return false;
        }

        QueryPatientAppointmentCommand otherQueryPatientAppointmentCommand = (QueryPatientAppointmentCommand) other;
        return predicate.equals(otherQueryPatientAppointmentCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
