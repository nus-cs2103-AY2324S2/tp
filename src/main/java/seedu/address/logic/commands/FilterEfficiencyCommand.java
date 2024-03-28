package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.PersonLessThanEfficiencyPredicate;

/**
 * Finds and lists all persons in address book whose efficiency below the argument threshold.
 */
public class FilterEfficiencyCommand extends Command {

    public static final String COMMAND_WORD = "filter_efficiency";

    public static final String MESSAGE_SUCCESS = "Filtered out poor performing employees with efficiency less than %d";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Filters employees with efficiency less than threshold given.\n"
            + "Parameters: integer (MUST within the range: 0 to 100)\n"
            + "Example: " + COMMAND_WORD + " 30";
    public static final String MESSAGE_INTEGER_OUT_OF_RANGE = "The input efficiency is out of range (0-100)";
    private final PersonLessThanEfficiencyPredicate predicate;

    public FilterEfficiencyCommand(PersonLessThanEfficiencyPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterEfficiencyCommand)) {
            return false;
        }

        FilterEfficiencyCommand otherFilterEfficiencyCommand = (FilterEfficiencyCommand) other;
        return predicate.equals(otherFilterEfficiencyCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }

}
