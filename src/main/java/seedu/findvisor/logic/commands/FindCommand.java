package seedu.findvisor.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.findvisor.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.findvisor.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.findvisor.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.findvisor.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.function.Predicate;

import seedu.findvisor.commons.util.ToStringBuilder;
import seedu.findvisor.logic.Messages;
import seedu.findvisor.model.Model;
import seedu.findvisor.model.person.Person;

/**
 * Finds persons based on search criteria of the specified category.
 * Only exactly one category of the following can be specified, either name, email, phone or tags.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose information matches "
            + "the specified keywords (case-insensitive) of the specified category"
            + "and displays them as a list with index numbers.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME | "
            + PREFIX_EMAIL + "EMAIL | "
            + PREFIX_PHONE + "PHONE | "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " t/PRUActiveCash t/friends";

    private final Predicate<Person> predicate;

    public FindCommand(Predicate<Person> predicate) {
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
        if (!(other instanceof FindCommand)) {
            return false;
        }

        FindCommand otherFindCommand = (FindCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
