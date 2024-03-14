package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import java.util.Collections;
import java.util.Set;
import java.util.function.Predicate;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
/**
 * Adds a person to the address book.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters applications by tags. "
            + "Parameters: "
            + PREFIX_TAG + "roles/stages ";

    public static final String MESSAGE_SUCCESS = "Persons Filtered: ";
    private final Set<Tag> filteredTag;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public FilterCommand(Set<Tag> givenTag) {
        requireNonNull(givenTag);
        filteredTag = givenTag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Predicate<Person> containsAnyCommonTag = person -> !Collections.disjoint(person.getTags(), filteredTag);

        model.updateFilteredPersonList(containsAnyCommonTag);
        return new CommandResult(String.format(MESSAGE_SUCCESS));
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

        FilterCommand otherAddCommand = (FilterCommand) other;
        return filteredTag.equals(otherAddCommand.filteredTag);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toFilter", this.filteredTag)
                .toString();
    }
}
