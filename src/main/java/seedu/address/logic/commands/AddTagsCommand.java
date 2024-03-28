package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Adds tags to an existing person in the address book.
 */
public class AddTagsCommand extends Command {

    public static final String COMMAND_WORD = "addtags";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds tags to the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TAG + "TAG]... \n"
            + "Example: " + COMMAND_WORD + " 1 t/car t/health";

    public static final String MESSAGE_SUCCESS = "Added tags to Person: %1$s";
    public static final String MESSAGE_NOT_ADDED = "At least one tag must be provided";

    private final Index index;
    private final Set<Tag> tags;

    /**
     * @param index of the person in the filtered person list to edit
     * @param tags to be added to the person
     */
    public AddTagsCommand(Index index, Set<Tag> tags) {
        this.index = index;
        this.tags = tags;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getSortedPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Set<Tag> updatedTags = new HashSet<>(personToEdit.getTags());
        updatedTags.addAll(tags);
        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), updatedTags, personToEdit.getUpcoming(), personToEdit.getLastcontact());

        model.setPerson(personToEdit, editedPerson);
        model.setToInitialList();

        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(editedPerson)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddTagsCommand)) {
            return false;
        }

        AddTagsCommand otherCommand = (AddTagsCommand) other;
        return index.equals(otherCommand.index)
                && tags.equals(otherCommand.tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("tags", tags)
                .toString();
    }

}
