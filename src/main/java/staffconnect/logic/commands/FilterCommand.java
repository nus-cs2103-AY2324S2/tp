package staffconnect.logic.commands;

import static java.util.Objects.requireNonNull;
import static staffconnect.logic.parser.CliSyntax.PREFIX_FACULTY;
import static staffconnect.logic.parser.CliSyntax.PREFIX_MODULE;
import static staffconnect.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.function.Predicate;

import staffconnect.commons.util.ToStringBuilder;
import staffconnect.logic.Messages;
import staffconnect.model.Model;
import staffconnect.model.person.Person;
import staffconnect.model.person.PersonHasFacultyPredicate;
import staffconnect.model.person.PersonHasModulePredicate;
import staffconnect.model.person.PersonHasTagsPredicate;

/**
 * Filters all persons in staff book whose module code or faculty or
 * tags include the given filtering criteria.
 * Criteria matching is case-insensitive.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all persons in staff book whose"
            + " module, faculty or tags contain the specified criteria (tags are case-insensitive)"
            + " and displays them as a list with index numbers.\n"
            + "Parameters: "
            + "[" + PREFIX_FACULTY + "FACULTY]"
            + " [" + PREFIX_MODULE + "MODULE]"
            + " [" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TAG + "BestProf";

    private final PersonHasFacultyPredicate facultyPredicate;
    private final PersonHasModulePredicate modulePredicate;
    private final PersonHasTagsPredicate tagsPredicate;
    private final Predicate<Person> personPredicate;

    /**
     * Creates a FilterCommand to filter for the specified {@code Faculty},
     * {@code Module}, {@code Tags}.
     */
    public FilterCommand(PersonHasFacultyPredicate facultyPredicate, PersonHasModulePredicate modulePredicate,
            PersonHasTagsPredicate tagsPredicate) {
        this.facultyPredicate = facultyPredicate;
        this.modulePredicate = modulePredicate;
        this.tagsPredicate = tagsPredicate;
        this.personPredicate = setPersonPredicate();
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(personPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    /**
     * Sets the filtering criteria for a person, given any {@code Faculty},
     * {@code Module}, {@code Tags}.
     * @return the person predicate to filter persons from.
     */
    private Predicate<Person> setPersonPredicate() {
        return this.facultyPredicate.and(modulePredicate.and(tagsPredicate));
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
        return facultyPredicate.toString().equals(otherFilterCommand.facultyPredicate.toString())
                && modulePredicate.toString().equals(otherFilterCommand.modulePredicate.toString())
                && tagsPredicate.toString().equals(otherFilterCommand.tagsPredicate.toString());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("facultyPredicate", facultyPredicate)
                .add("modulePredicate", modulePredicate)
                .add("tagsPredicate", tagsPredicate)
                .toString();
    }

}
