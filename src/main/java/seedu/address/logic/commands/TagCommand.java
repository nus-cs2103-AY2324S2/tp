package seedu.address.logic.commands;

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
            + ": Tags the contact identified by the index number used in the displayed contact list with the specified tag.\n"
            + "Parameters: INDEX (must be a positive integer) | TAG\n"
            + "Example: " + COMMAND_WORD + " 1 | friends";

    public static final String MESSAGE_TAG_CONTACT_SUCCESS = "Tagged Contact: %1$s with %2$s";

    private final int targetIndex;
    private final Tag tag;

    public TagCommand(int targetIndex, Tag tag) {
        this.targetIndex = targetIndex;
        this.tag = tag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Person personToTag = model.getPersonByIndex(targetIndex);
        Person taggedPerson = personToTag.addTag(tag);
        model.setPerson(personToTag, taggedPerson);
        return new CommandResult(String.format(MESSAGE_TAG_CONTACT_SUCCESS, personToTag, tag));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof TagCommand
                && targetIndex == ((TagCommand) other).targetIndex
                && tag.equals(((TagCommand) other).tag));
    }
}
