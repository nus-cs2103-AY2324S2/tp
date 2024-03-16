package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

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
 * Removes a tag associated to a person identified using it's displayed index from the address book.
 */
public class RemoveTagCommand extends Command {

    public static final String COMMAND_WORD = "RemoveTag";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Tags: %2$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Removes the tag on the person identified by the index number used in the displayed person list.\n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "[" + PREFIX_TAG + "TAG]...\n"
        + "Example: " + COMMAND_WORD + " 1 " + PREFIX_TAG + "friends";

    public static final String MESSAGE_REMOVE_TAG_SUCCESS = "Tags %1$s removed from this person : %2$s";
    public static final String MESSAGE_TAG_DOES_NOT_EXIST = "This person does not have that tag";

    private final Index targetIndex;
    private final Set<Tag> tags;

    /**
     * @param targetIndex of the person in the filtered person list to remove the tag
     * @param tags to be removed from the person
     */
    public RemoveTagCommand(Index targetIndex, Set<Tag> tags) {
        this.targetIndex = targetIndex;
        this.tags = tags;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToRemove = lastShownList.get(targetIndex.getZeroBased());
        Set<Tag> oldTags = personToRemove.getTags();
        Set<Tag> newSet = new HashSet<>(oldTags);
        for (Tag tag : tags) {
            if (!oldTags.contains(tag)) {
                throw new CommandException(MESSAGE_TAG_DOES_NOT_EXIST + " " + tag.toString());
            }
            newSet.remove(tag);
        }
        Person removedTagPerson = new Person(personToRemove.getName(), personToRemove.getPhone(),
            personToRemove.getEmail(), personToRemove.getAddress(), newSet);
        model.setPerson(personToRemove, removedTagPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_REMOVE_TAG_SUCCESS,
            tags.toString(), removedTagPerson.getName()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemoveTagCommand)) {
            return false;
        }

        RemoveTagCommand otherRemoveTagCommand = (RemoveTagCommand) other;
        return targetIndex.equals(otherRemoveTagCommand.targetIndex) && tags.equals(otherRemoveTagCommand.tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .add("tags", tags)
                .toString();
    }
}
