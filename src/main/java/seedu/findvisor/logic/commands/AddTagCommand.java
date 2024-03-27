package seedu.findvisor.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.findvisor.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.findvisor.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.findvisor.commons.core.index.Index;
import seedu.findvisor.commons.util.ToStringBuilder;
import seedu.findvisor.logic.Messages;
import seedu.findvisor.logic.commands.exceptions.CommandException;
import seedu.findvisor.model.Model;
import seedu.findvisor.model.person.Address;
import seedu.findvisor.model.person.Email;
import seedu.findvisor.model.person.Meeting;
import seedu.findvisor.model.person.Name;
import seedu.findvisor.model.person.Person;
import seedu.findvisor.model.person.Phone;
import seedu.findvisor.model.person.Remark;
import seedu.findvisor.model.tag.Tag;

/**
 * Adds tags to an existing person in the address book.
 */
public class AddTagCommand extends Command {

    public static final String COMMAND_WORD = "addtag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds new tags to the person identified "
            + "by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer), TAGS (must be alphanumeric with no spaces)\n"
            + "Single tag example: " + COMMAND_WORD + " 1 "
            + PREFIX_TAG + "PRUTravellerProtect\n"
            + "Multiple tag example: " + COMMAND_WORD + " 1 "
            + PREFIX_TAG + "PRUactiveCash "
            + PREFIX_TAG + "UniversityStudentYear1";

    public static final String MESSAGE_ADD_TAGS_TO_PERSON_SUCCESS = "Added tags to Person: %1$s";
    public static final String MESSAGE_TAG_CONSTRAINTS_VIOLATED = "One or more tags violate the tag constraints\n"
            + "Tags must be alphanumeric with no spaces\n"
            + "Example: " + PREFIX_TAG + "PRUactiveCash23\n"
            + "Multiple tag example: " + PREFIX_TAG + "PRUactiveCash23 " + PREFIX_TAG + "PRUTravellerProtect";

    private final Index index;
    private final Set<Tag> newTags;

    /**
     * @param index of the person in the filtered person list to add tags to
     * @param newTags tags to add to the person
     */
    public AddTagCommand(Index index, Set<Tag> newTags) {
        requireNonNull(index);
        requireNonNull(newTags);

        this.index = index;
        this.newTags = newTags;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToAddTagTo = lastShownList.get(index.getZeroBased());
        Person personWithAddedTags = addTagsToPerson(personToAddTagTo, newTags);

        model.setPerson(personToAddTagTo, personWithAddedTags);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_ADD_TAGS_TO_PERSON_SUCCESS,
                Messages.format(personWithAddedTags)));
    }

    /**
     * Creates and returns a {@code Person} with the same details as {@code  personToAddTagTo}
     * and all the newly added tags from {@code newTags}
     */
    private static Person addTagsToPerson(Person personToAddTagTo, Set<Tag> newTags) {
        assert personToAddTagTo != null;

        //keep original details of person but add the tag to their tag set
        Name name = personToAddTagTo.getName();
        Phone phone = personToAddTagTo.getPhone();
        Email email = personToAddTagTo.getEmail();
        Address address = personToAddTagTo.getAddress();
        Set<Tag> tags = personToAddTagTo.getTags();
        Optional<Meeting> meeting = personToAddTagTo.getMeeting();
        Optional<Remark> remark = personToAddTagTo.getRemark();

        Set<Tag> updatedTags = new HashSet<Tag>();
        updatedTags.addAll(tags);
        updatedTags.addAll(newTags);

        return new Person(name, phone, email, address, updatedTags, meeting, remark);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddTagCommand)) {
            return false;
        }

        AddTagCommand otherAddTagCommand = (AddTagCommand) other;
        return newTags.equals(otherAddTagCommand.newTags) && index.equals(otherAddTagCommand.index);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("tags", newTags)
                .toString();
    }
}
