package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;

import java.util.function.Function;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.appointment.AppointmentContainsKeywordsPredicate;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Finds and lists all appointments whose associated person's name contains any
 * of the argument keywords. Keyword matching is case insensitive.
 */
public class FindAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "findappt";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all appointments whose associated person's name contain any of "
            + "the specified keywords and displays them as a list with index numbers.\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final Function<Model, AppointmentContainsKeywordsPredicate> predicateFunction;

    public FindAppointmentCommand(Function<Model, AppointmentContainsKeywordsPredicate> predicateFunction) {
        this.predicateFunction = predicateFunction;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        AppointmentContainsKeywordsPredicate predicate = predicateFunction.apply(model);
        model.updateFilteredAppointmentList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_APPOINTMENTS_LISTED_OVERVIEW,
                        model.getFilteredAppointmentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null) {
            return false;
        }

        if (!(other instanceof FindAppointmentCommand)) {
            return false;
        }

        FindAppointmentCommand otherFindCommand = (FindAppointmentCommand) other;

        return predicateFunction.equals(otherFindCommand.predicateFunction);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicateFunction", predicateFunction)
                .toString();
    }
}
