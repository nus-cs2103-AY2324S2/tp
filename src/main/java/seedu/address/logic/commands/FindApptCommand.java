package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.appointment.AppointmentContainsKeywordsPredicate;

/**
 * Finds and lists all appointments in address book whose details fit any of the argument keywords.
 */
public class FindApptCommand extends Command {

    public static final String COMMAND_WORD = "findAppt";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds the details of the appointment identified either by patient's NRIC, Date, or Start time.\n"
            + "Parameters: "
            + "[" + PREFIX_NRIC + " NRIC] "
            + "[" + PREFIX_NAME + "DATE] "
            + "[" + PREFIX_START_TIME + "START_TIME]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NRIC + " T0123456A "
            + PREFIX_DATE + " 2024-02-03 "
            + PREFIX_START_TIME + " 11:00";

    private final AppointmentContainsKeywordsPredicate predicate;

    public FindApptCommand(AppointmentContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredAppointmentList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_APPOINTMENTS_LISTED_OVERVIEW,
                        model.getFilteredAppointmentViewList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindApptCommand)) {
            return false;
        }

        FindApptCommand otherFindCommand = (FindApptCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
