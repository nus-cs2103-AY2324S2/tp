package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.commands.EditCommand.MESSAGE_DUPLICATE_PERSON;
import static seedu.address.logic.commands.EditCommand.MESSAGE_EDIT_PERSON_SUCCESS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.commons.core.index.Index;
import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagStatus;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Changes the remark of an existing person in the address book.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    // to be updated
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the remark of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing remark will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            //+  PREFIX_REMARK + "[REMARK]\n"
            + "Example: " + COMMAND_WORD + " 1 ";
            //+ PREFIX_REMARK + "Likes to swim.";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Tag: %2$s";

    private final Index index;
    private final String tagName;
    private final TagStatus tagStatus;

    public MarkCommand(Index index, String tagName, TagStatus tagStatus) {
        requireAllNonNull(index, tagName, tagStatus);
        this.index = index;
        this.tagName = tagName;
        this.tagStatus = tagStatus;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        // get the tags of the personToEdit
        Set<Tag> currTags = new HashSet<>(personToEdit.getTags());

        // try adding the new tag into the tag list first, replace it later
        Tag newTag = new Tag(tagName, tagStatus);
        if (currTags.contains(newTag)) {
            currTags.remove(newTag);
        };
        currTags.add(new Tag(tagName, tagStatus));
        Person editedPerson = createEditedPerson(personToEdit, currTags);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MarkCommand)) {
            return false;
        }

        // state check
        MarkCommand e = (MarkCommand) other;
        return index.equals(e.index)
                && tagName.equals(e.tagName)
                && tagStatus.equals(tagStatus);
    }

    // temporarily duplicate this
    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, Set<Tag> newTags) {
        assert personToEdit != null;
        return new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), newTags);
    }
}