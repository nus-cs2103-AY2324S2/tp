package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicate.ComponentPredicate;

/**
 * Finds and lists any contacts whose components pass any of the given predicates.
 * Keyword matching is case-insensitive.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all persons whose components contain any of "
            + "the specified keywords (case-insensitive) according to the different modifiers and"
            + "displays them as a list with index numbers.\n"
            + "Parameters: [component[.attribute]:<value>...]...\n"
            + "Where component is any one of: name, address, phone, tag, email\n"
            + "And attribute is any one of: is, isnt, has, hasnt, word, noword, none, any\n"
            + "Refer to the user guide for more details\n"
            + "Example: " + COMMAND_WORD + " address.has:Kent Ridge";

    private final ArrayList<ComponentPredicate> predicates;

    /**
     * @param predicates The predicates to match the user component on.
     */
    public FilterCommand(ArrayList<ComponentPredicate> predicates) {
        requireNonNull(predicates);
        this.predicates = predicates;
    }

    public Predicate<Person> disjunctivelyCombinePredicates() {
        return person -> predicates.stream().anyMatch(predicate -> predicate.test(person));
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredPersonList(disjunctivelyCombinePredicates());
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
        return predicates.equals(otherFilterCommand.predicates);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicates)
                .toString();
    }
}
