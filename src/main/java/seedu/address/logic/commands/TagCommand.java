package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

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
    private final Tag tag;

    /**
     * @param index of the person in the filtered person list to tag
     * @param tag string to tag the person with
     */
    public TagCommand(Index index, Tag tag) {
        this.targetIndex = index;
        this.tag = tag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToTag = lastShownList.get(targetIndex.getZeroBased());

        Person taggedPerson = personToTag.addTag(tag);
        model.setPerson(personToTag, taggedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_TAG_CONTACT_SUCCESS, personToTag, tag));
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
                && tag.equals(otherTagCommand.tag);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .add("tag", tag)
                .toString();
    }
}
