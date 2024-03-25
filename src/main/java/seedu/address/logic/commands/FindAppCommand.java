package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.appointment.AppointmentContainsKeywordsPredicate;

/**
 * Finds and lists all appointments in address book whose details fit any of the argument keywords.
 */
public class FindAppCommand extends Command {

    public static final String COMMAND_WORD = "findApp";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all appointments whose fields fit any of "
            + "the specified arguments and displays them as a list.\n"
            + "Parameters: KEYWORD [MORE_OPTIONAL_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " i/ T0123456A d/ 2024-03-16 from/ 11:00";

    private final AppointmentContainsKeywordsPredicate predicate;

    public FindAppCommand(AppointmentContainsKeywordsPredicate predicate) {
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
        if (!(other instanceof FindAppCommand)) {
            return false;
        }

        FindAppCommand otherFindCommand = (FindAppCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
