package seedu.address.logic.commands;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.logic.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameEqualsKeywordPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.Group;

/**
 * Changes the remark of an existing person in the address book.
 */
public class GroupCommand extends Command {

    public static final String COMMAND_WORD = "group";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a group to the person identified by the contact name "
            + "Existing group will be overwritten by the input.\n"
            + "g/ [GROUP_NAME]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "g/ Friends";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Remark: %2$s";
    public static final String MESSAGE_ADD_REMARK_SUCCESS = "Grouped the person: %1$s under %2$s";
    public static final String MESSAGE_DELETE_REMARK_SUCCESS = "Removed Person: %1$s from the group: %2$s";

    private final String name;
    private final Group group;

    public static final String MESSAGE_NOT_IMPLEMENTED_YET =
            "Group command not implemented yet";

    /**
     * @param name  of the person in the filtered person list to edit the remark
     * @param group of the person to be updated to
     */
    public GroupCommand(String name, Group group) {
        requireAllNonNull(name, group);

        this.name = name;
        this.group = group;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(new NameEqualsKeywordPredicate(name));
        List<Person> lastShownList = model.getFilteredPersonList();

        if (lastShownList.size() <= 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(0);
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), group, personToEdit.getTags());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on whether
     * the remark is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = !group.value.isEmpty() ? MESSAGE_ADD_REMARK_SUCCESS : MESSAGE_DELETE_REMARK_SUCCESS;
        return String.format(message, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GroupCommand)) {
            return false;
        }

        GroupCommand e = (GroupCommand) other;
        return name.equals(e.name)
                && group.equals(e.group);

    }
}