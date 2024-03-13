package staffconnect.logic.commands;

import static java.util.Objects.requireNonNull;

import staffconnect.logic.Messages;
// import static staffconnect.logic.parser.CliSyntax.PREFIX_FACULTY; // TODO: add filtering for faculty and module
// import static staffconnect.logic.parser.CliSyntax.PREFIX_MODULE;
import static staffconnect.logic.parser.CliSyntax.PREFIX_TAG;

import staffconnect.commons.util.ToStringBuilder;
import staffconnect.model.Model;
import staffconnect.model.person.PersonHasTagPredicate; // TagContainsKeywordsPredicate

/**
 * Filters all persons in staff book whose module code or faculty shorthand or
 * tags include the given filtering criteria.
 * Criteria matching is case-insensitive.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all persons in staff book whose tags"
            + " include the given tag name (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: "
            // + "[" + PREFIX_FACULTY + "FACULTY]\n"
            // + "[" + PREFIX_MODULE + "MOODULE]\n"
            + "[" + PREFIX_TAG + "TAG]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TAG + "BestProf";

    private final PersonHasTagPredicate tagPredicate;

    /**
     * Creates a FilterTagCommand to filter for the specified {@code Tag}
     */
    public FilterCommand(PersonHasTagPredicate tagPredicate) {
        this.tagPredicate = tagPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(tagPredicate);
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
        return tagPredicate.equals(otherFilterCommand.tagPredicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("tagPredicate", tagPredicate)
                .toString();
    }

}
