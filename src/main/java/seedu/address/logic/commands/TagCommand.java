package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Id;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.YearJoined;
import seedu.address.model.tag.Tag;

/**
 * Tags an employee in the address book.
 */
public class TagCommand extends Command {

    public static final String COMMAND_WORD = "/tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Tags an employee in the address book.\n"
            + "Format:\n"
            + "• " + COMMAND_WORD + " ID " + PREFIX_TAG + " TAG...\n "
            + "Examples:\n"
            + "• " + COMMAND_WORD + " 240001 " + PREFIX_TAG + " Finance " + PREFIX_TAG + " Manager\n";

    public static final String MESSAGE_SUCCESS = "Employee tagged\n";

    public static final String MESSAGE_DUPLICATE_TAGS = "All tags are already present in the employee!";

    private final Id id;

    private Set<Tag> tags;

    /**
     * @param id Id of {@Code Person} to tag.
     * @param tags Set of (@Code Tag} to tag {@Code Person} with.
     */
    public TagCommand(Id id, Set<Tag> tags) {
        requireNonNull(id);
        this.id = id;
        this.tags = tags;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        Person personToTag = lastShownList
                .stream()
                .filter(person -> person.getId().value == (this.id.value))
                .findFirst()
                .orElse(null);

        if (personToTag == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_ID);
        }

        Person editedPerson = addTagsToPerson(personToTag, tags);

        model.setPerson(personToTag, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

    /**
     * Adds a set of tags to an existing {@Code Person}.
     * Returns a new (@Code Person} with the added set of tags.
     */
    private Person addTagsToPerson(Person personToTag, Set<Tag> tagsToAdd) throws CommandException {
        Id id = personToTag.getId();
        Name name = personToTag.getName();
        Email email = personToTag.getEmail();
        Phone phone = personToTag.getPhone();
        YearJoined yearJoined = personToTag.getYearJoined();
        Address address = personToTag.getAddress();
        Set<Tag> tags = personToTag.getTags();

        int originalLength = tags.size();

        tagsToAdd.addAll(tags);

        int newLength = tagsToAdd.size();

        if (originalLength == newLength) {
            throw new CommandException(MESSAGE_DUPLICATE_TAGS);
        }

        Person editedPerson = new Person(id, name, phone, email, yearJoined, address, tagsToAdd);

        return editedPerson;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagCommand)) {
            return false;
        }

        TagCommand otherTagCommand = (TagCommand) other;
        return id.equals(otherTagCommand.id)
                && tags.equals(otherTagCommand.tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("id", id)
                .add("tags", tags)
                .toString();
    }
}


