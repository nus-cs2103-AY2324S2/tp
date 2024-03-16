package staffconnect.logic.commands;

import static java.util.Objects.requireNonNull;
// import static staffconnect.logic.parser.CliSyntax.PREFIX_FACULTY; // TODO: add filtering for faculty
import static staffconnect.logic.parser.CliSyntax.PREFIX_MODULE;
import static staffconnect.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.function.Predicate;

import staffconnect.commons.util.ToStringBuilder;
import staffconnect.logic.Messages;
import staffconnect.model.Model;
import staffconnect.model.person.Person;

/**
 * Filters all persons in staff book whose module code or faculty shorthand or
 * tags include the given filtering criteria.
 * Criteria matching is case-insensitive.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all persons in staff book whose"
            + " module, faculty or tags contain the specified criteria (tags are case-insensitive)"
            + " and displays them as a list with index numbers.\n"
            + "Parameters: "
            // + "[" + PREFIX_FACULTY + "FACULTY]"
            + " [" + PREFIX_MODULE + "MODULE]"
            + " [" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TAG + "BestProf";

    // private final PersonHasModulePredicate modulePredicate;
    // private final PersonHasTagsPredicate tagPredicate;
    // private final PersonMatchesFilterCriteriaPredicate filterPredicate;
    private final Predicate<Person> personPredicate;

    /**
     * Creates a FilterCommand to filter for the specified {@code Tag}
     */
    public FilterCommand(Predicate<Person> personPredicate) {
        this.personPredicate = personPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(personPredicate);
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
        return personPredicate.equals(otherFilterCommand.personPredicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("personPredicate", personPredicate)
                .toString();
    }

}
