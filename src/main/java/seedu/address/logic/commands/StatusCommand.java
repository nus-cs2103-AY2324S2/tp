package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Comment;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Status;
import seedu.address.model.tag.Tag;

/**
 * Updates recruitment status for candidates in the address book.
 */
public class StatusCommand extends Command {

    public static final String COMMAND_WORD = "status";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Updates recruitment status of a candidate"
            + " existing in the address book to one of the five status:\n"
            + "PRESCREEN, IN_PROGRESS, WAITLIST, ACCEPTED, REJECTED\n"
            + "Parameters: INDEX(must be a positive integer) STATUS \n"
            + "Example: " + COMMAND_WORD + " 2 ACCEPTED";

    public static final String MESSAGE_STATUS_PERSON_SUCCESS = "Status of Candidate Number %1$d Successfully"
            + " Updated to %2$s";

    public static final String MESSAGE_DUPLICATE_PERSON = "This candidate with identical recruitment status "
            + "already exists in the address book or this candidate's status is already set to %1$s.";

    public static final String STATUS_CANNOT_BE_EDITED = "Status of candidates cannot be edited via edit method.\n"
            + "Please use status command instead in order to update recruitment status for candidates.";
    private final Index index;
    private final Status status;

    /**
     * Creates an StatusCommand to update the candidate status for specified {@code Person}
     */
    public StatusCommand(Index index, Status status) {
        requireNonNull(index);
        requireNonNull(status);

        this.index = index;
        this.status = status;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToUpdateStatus = lastShownList.get(index.getZeroBased());
        Person statusUpdatedPerson = updatePersonStatus(personToUpdateStatus);

        if (personToUpdateStatus.equals(statusUpdatedPerson) && model.hasPerson(statusUpdatedPerson)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_PERSON, status.toString()));
        }

        model.setPerson(personToUpdateStatus, statusUpdatedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_STATUS_PERSON_SUCCESS,
                index.getOneBased(), status.toString()));
    }

    /**
     * Updates a person with a new user-provided status among one of the five enum values
     */
    public Person updatePersonStatus(Person personToUpdateStatus) {
        assert personToUpdateStatus != null;

        Name updatedName = personToUpdateStatus.getName();
        Phone updatedPhone = personToUpdateStatus.getPhone();
        Email updatedEmail = personToUpdateStatus.getEmail();
        Address updatedAddress = personToUpdateStatus.getAddress();
        Status updatedStatus = status;
        Comment updatedComment = personToUpdateStatus.getComment();
        Set<Tag> updatedTags = personToUpdateStatus.getTags();

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedStatus,
                updatedComment, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StatusCommand)) {
            return false;
        }

        StatusCommand otherStatusCommand = (StatusCommand) other;
        return index.equals(otherStatusCommand.index)
                && status.equals(otherStatusCommand.status);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("status", status)
                .toString();
    }
}
