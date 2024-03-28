package seedu.hirehub.logic.commands;

import static seedu.hirehub.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.hirehub.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.hirehub.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.hirehub.commons.core.index.Index;
import seedu.hirehub.logic.Messages;
import seedu.hirehub.logic.commands.exceptions.CommandException;
import seedu.hirehub.model.Model;
import seedu.hirehub.model.person.Person;
import seedu.hirehub.model.tag.Tag;

/**
 * Appends tags to an existing person in the address book.
 */
public class TagCommand extends Command {

    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds tags to the person identified "
            + "by the index number used in the last person listing. "
            + "Existing tags will not be overwritten by the input.\n"
            + "At least one tag must be present.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TAG + "INTERNAL " + PREFIX_TAG + "WAITLIST";

    public static final String MESSAGE_ADD_TAGS_SUCCESS = "Added tags to Person: %1$s";
    private final Index index;
    private final Set<Tag> tags;

    /**
     * @param index of the person in the filtered person list to edit the comment
     * @param tags  to be added to the person
     */
    public TagCommand(Index index, Set<Tag> tags) {
        requireAllNonNull(index, tags);

        this.index = index;
        this.tags = tags;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Set<Tag> newTagList = new HashSet<>();
        newTagList.addAll(personToEdit.getTags());
        newTagList.addAll(tags);
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getCountry(), personToEdit.getComment(), newTagList);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_ADD_TAGS_SUCCESS, Messages.format(editedPerson)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TagCommand that = (TagCommand) o;

        if (!Objects.equals(index, that.index)) {
            return false;
        }
        return Objects.equals(tags, that.tags);
    }
}
