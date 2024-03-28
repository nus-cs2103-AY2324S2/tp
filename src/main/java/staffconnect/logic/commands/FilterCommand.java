package staffconnect.logic.commands;

import static java.util.Objects.requireNonNull;
import static staffconnect.logic.parser.CliSyntax.PREFIX_AVAILABILITY;
import static staffconnect.logic.parser.CliSyntax.PREFIX_FACULTY;
import static staffconnect.logic.parser.CliSyntax.PREFIX_MODULE;
import static staffconnect.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.function.Predicate;

import staffconnect.commons.util.ToStringBuilder;
import staffconnect.logic.Messages;
import staffconnect.model.Model;
import staffconnect.model.person.Person;
import staffconnect.model.person.predicates.PersonHasAvailabilitiesPredicate;
import staffconnect.model.person.predicates.PersonHasFacultyPredicate;
import staffconnect.model.person.predicates.PersonHasModulePredicate;
import staffconnect.model.person.predicates.PersonHasTagsPredicate;

/**
 * Filters all persons in staff book whose module code or faculty or
 * tags include the given filtering criteria.
 * Criteria matching is case-insensitive.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all persons in staff book whose"
            + " module, faculty, tags or availabilities contain the specified criteria (case-insensitive)"
            + " and displays them as a list with index numbers.\n"
            + "Parameters: "
            + "[" + PREFIX_MODULE + "MODULE]"
            + " [" + PREFIX_FACULTY + "FACULTY]"
            + " [" + PREFIX_TAG + "TAG]..."
            + " [" + PREFIX_AVAILABILITY + "AVAILABILITY]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TAG + "BestProf";

    private final PersonHasModulePredicate modulePredicate;
    private final PersonHasFacultyPredicate facultyPredicate;
    private final PersonHasTagsPredicate tagsPredicate;
    private final PersonHasAvailabilitiesPredicate availabilitiesPredicate;
    private final Predicate<Person> personPredicate;

    /**
     * Creates a FilterCommand to filter for the specified {@code Module},
     * {@code Faculty}, {@code Tags}, {@code Availabilities}.
     */
    public FilterCommand(PersonHasModulePredicate modulePredicate, PersonHasFacultyPredicate facultyPredicate,
            PersonHasTagsPredicate tagsPredicate, PersonHasAvailabilitiesPredicate availabilitiesPredicate) {
        this.modulePredicate = modulePredicate;
        this.facultyPredicate = facultyPredicate;
        this.tagsPredicate = tagsPredicate;
        this.availabilitiesPredicate = availabilitiesPredicate;
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
     * Sets the filtering criteria for a person, given any {@code Module},
     * {@code Faculty}, {@code Tags}, {@code Availabilities}.
     * @return the person predicate to filter persons from.
     */
    private Predicate<Person> setPersonPredicate() {
        return this.modulePredicate.and(facultyPredicate.and(tagsPredicate.and(availabilitiesPredicate)));
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
        return modulePredicate.toString().equals(otherFilterCommand.modulePredicate.toString())
                && facultyPredicate.toString().equals(otherFilterCommand.facultyPredicate.toString())
                && tagsPredicate.toString().equals(otherFilterCommand.tagsPredicate.toString())
                && availabilitiesPredicate.toString().equals(otherFilterCommand.availabilitiesPredicate.toString());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("modulePredicate", modulePredicate)
                .add("facultyPredicate", facultyPredicate)
                .add("tagsPredicate", tagsPredicate)
                .add("availabilitiesPredicate", availabilitiesPredicate)
                .toString();
    }

}
