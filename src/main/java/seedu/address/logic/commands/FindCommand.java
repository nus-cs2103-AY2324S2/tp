package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AVAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all volunteers whose <Attribute> contain "
            + "the specified keywords (case-insensitive) and displays them as a indexed list.\n"
            + "Parameters: <Attribute_Prefix/> KEYWORD [MORE_KEYWORDS]...\n"
            + "Example(find by name): " + COMMAND_WORD + " " + PREFIX_NAME + " alice bob charlie\n"
            + "Example(find by availability): " + COMMAND_WORD + " " + PREFIX_AVAIL + " 01/01/2024 12/12/2024\n"
            + "Example(find by name and availability): " + COMMAND_WORD + " "
            + PREFIX_NAME + " alice " + PREFIX_AVAIL + " 01/01/2024 12/12/2024";

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

    public Predicate<Person> getPredicate() {
        return this.predicate;
    }
}
