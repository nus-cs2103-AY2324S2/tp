package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MET;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

import java.util.List;

/**
 * Adds a person to the address book.
 */
public class MetCommand extends Command {

    public static final String COMMAND_WORD = "met";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": marks a person as met. "
            + "Parameters: "
            + PREFIX_MET + "INDEX \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MET + "1 ";

    public static final String MESSAGE_SUCCESS = "You have met: %1$s";
    public static final String MESSAGE_ALREADY_MET = "You have already met %1$s before.";
    public static final boolean MET = true;
    public static final boolean NOT_MET = false;

    private final Index index;

    /**
     * Creates an MetCommand to add the specified {@code Person}
     */
    public MetCommand(Index index) {
        requireNonNull(index);

        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToMeet = lastShownList.get(index.getZeroBased());
        Person metPerson = new Person(
                personToMeet.getName(), personToMeet.getPhone(), personToMeet.getEmail(),
                personToMeet.getAddress(), MET, personToMeet.getTags());

        if (personToMeet.getHasMet()) {
            throw new CommandException(String.format(MESSAGE_ALREADY_MET, personToMeet.getName()));
        }

        model.setPerson(personToMeet, metPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, personToMeet.getName()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MetCommand)) {
            return false;
        }

        MetCommand otherMetCommand = (MetCommand) other;
        return index.equals(otherMetCommand.index);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .toString();
    }
}
