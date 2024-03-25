package seedu.edulink.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.function.Predicate;

import seedu.edulink.commons.util.ToStringBuilder;
import seedu.edulink.logic.Messages;
import seedu.edulink.model.Model;
import seedu.edulink.model.student.Student;

/**
 * Filters list of student based on tags.
 * Can have multiple tags involved.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose tag matches exaclty with "
            + "the specified tags and displays them as a list with index numbers.\n"
            + "Parameters: " + PREFIX_TAG + "KEYWORD "
            + "[" + PREFIX_TAG + "KEYWORD] ... \n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_TAG + "TA" + " " + PREFIX_TAG + "Kind";

    private final Predicate<Student> predicate;

    public FilterCommand(Predicate<Student> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
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
        if (!(other instanceof FilterCommand)) {
            return false;
        }

        FilterCommand otherFilterCommand = (FilterCommand) other;
        return predicate.equals(otherFilterCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
