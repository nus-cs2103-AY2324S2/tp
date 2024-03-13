package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

/**
 * Tags a contact identified using its displayed index in the address book.
 */
public class TagCommand extends Command {

    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Tags the contact identified by the index number used in the displayed contact list with the specified tag.\n"
            + "Parameters: INDEX (must be a positive integer) t/ TAG\n"
            + "Example: " + COMMAND_WORD + " 1 t/ friends";

    public static final String MESSAGE_TAG_CONTACT_SUCCESS = "Tagged Contact: %1$s with %2$s";

    private final Index index;
    private final Tag tag;

    public TagCommand(Index index, Tag tag) {
        this.index = index;
        this.tag = tag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToTag = lastShownList.get(index.getZeroBased());

        Person taggedPerson = personToTag.addTag(tag);
        model.setPerson(personToTag, taggedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_TAG_CONTACT_SUCCESS, personToTag, tag));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof TagCommand
                && index == ((TagCommand) other).index
                && tag.equals(((TagCommand) other).tag));
    }
}
