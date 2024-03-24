package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;

import java.util.HashSet;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;

/**
 * Deletes a group from the address book.
 */
public class DeleteGroupCommand extends Command {

    public static final String COMMAND_WORD = "deletegroup";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a group to the address book. "
            + "Parameters: "
            + PREFIX_GROUP + "GROUP NAME ";

    public static final String MESSAGE_SUCCESS = "Group removed: %1$s";
    public static final String MESSAGE_NOT_FOUND = "Group is not found";

    private final Group toRemove;

    /**
     * Creates a DeleteGroupCommand
     */
    public DeleteGroupCommand(Group group) {
        requireNonNull(group);
        toRemove = group;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasGroup(toRemove)) {
            throw new CommandException(MESSAGE_NOT_FOUND);
        }

        ObservableList<Person> persons = model.getAddressBook().getPersonList();
        for (Person person : persons) {
            if (person.getGroups().contains(toRemove)) {
                Set<Group> editedGroups = new HashSet<>(person.getGroups());
                editedGroups.remove(toRemove);
                Person editedPerson = new Person(person.getName(), person.getPhone(), person.getEmail(),
                        person.getYear(), person.getTelegram(), person.getMajor(), person.getRemark(),
                        editedGroups);
                model.setPerson(person, editedPerson);
            }
        }

        model.deleteGroup(toRemove);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toRemove));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteGroupCommand)) {
            return false;
        }

        DeleteGroupCommand otherAddGroupCommand = (DeleteGroupCommand) other;
        return toRemove.equals(otherAddGroupCommand.toRemove);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toRemove", toRemove)
                .toString();
    }
}
