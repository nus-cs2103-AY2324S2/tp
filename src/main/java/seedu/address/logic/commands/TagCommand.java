package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collection;
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
 * Tags a contact identified using its displayed index in the address book.
 */
public class TagCommand extends Command {

    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Tags the contact identified by the index number"
            + " used in the displayed contact list with the specified tag.\n"
            + "Parameters: INDEX (must be a positive integer) t/ TAG\n"
            + "Example: " + COMMAND_WORD + " 1 t/ friends";

    public static final String MESSAGE_TAG_CONTACT_SUCCESS = "Tagged Contact: %1$s with %2$s";

    private final Index targetIndex;
    private final Set<Tag> tags;

    /**
     * Creates a command to add a {@code tag} to the person at {@code index}.
     */
    public TagCommand(Index index, Collection<Tag> tags) {
        this.targetIndex = index;
        this.tags = new HashSet<>(tags);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToTag = lastShownList.get(targetIndex.getZeroBased());

        Person taggedPerson = addTag(personToTag);
        model.setPerson(personToTag, taggedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_TAG_CONTACT_SUCCESS, personToTag, tags));
    }

    private Person addTag(Person personToTag) {
        HashSet<Tag> personTags = new HashSet<>(personToTag.getTags());

        personTags.addAll(tags);

        return new Person(
                personToTag.getName(),
                personToTag.getPhone(),
                personToTag.getEmail(),
                personToTag.getAddress(),
                personTags);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TagCommand)) {
            return false;
        }

        TagCommand otherTagCommand = (TagCommand) other;
        return targetIndex.equals(otherTagCommand.targetIndex)
                && tags.equals(otherTagCommand.tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .add("tag", tags)
                .toString();
    }
}
