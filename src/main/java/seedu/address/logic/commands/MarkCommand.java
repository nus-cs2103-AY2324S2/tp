package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAGSTATUS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagStatus;

/**
 * Changes the remark of an existing person in the address book.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    // to be updated
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Updates the status of the specified tag "
            + "with the specified status.\n"
            + "If the tag specified does not exist, a new tag with the tag name"
            + " and tag status would be created.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_TAG + " [TAG] " + PREFIX_TAGSTATUS + " [TAGSTATUS]\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_TAG + " assignment1 "
            + PREFIX_TAGSTATUS + " cg";

    public static final String MESSAGE_MARK_PERSON_SUCCESS = "Updated Person: %1$s";
    private final Index index;
    private final String tagName;
    private final TagStatus tagStatus;

    /**
     * @param index of the person in the filtered person list to update tag status
     * @param tagName name of the tag whose status is to be updated
     * @param tagStatus the status to update the specified tag with
     */
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

        // check whether index specified is within valid range
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Set<Tag> currTags = new HashSet<>(personToEdit.getTags());

        // create a new person with the new tag, necessary as the person fields are currently final
        Person editedPerson = createEditedPerson(personToEdit, updateTagsWithNewTag(currTags));

        // update the person list and make GUI show all existing person
        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_MARK_PERSON_SUCCESS, Messages.format(editedPerson)));
    }

    private Set<Tag> updateTagsWithNewTag(Set<Tag> currTags) {
        // Instead of retrieving the Tag sharing the same name and update it,
        // remove the potentially existing Tag of the same name from the hashset
        // and then add in a new Tag with the same tagName but updated tagStatus.
        // This is to avoid having linearly check through the hashset to retrieve
        // the existing Tag
        Tag newTag = new Tag(tagName, tagStatus);
        currTags.remove(newTag);
        currTags.add(new Tag(tagName, tagStatus));
        return currTags;
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
                && tagStatus.equals(e.tagStatus);
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     */
    private static Person createEditedPerson(Person personToEdit, Set<Tag> newTags) {
        assert personToEdit != null;
        return new Person(personToEdit.getType(), personToEdit.getName(), personToEdit.getPhone(),
                personToEdit.getEmail(), personToEdit.getAddress(), newTags);
    }
}
