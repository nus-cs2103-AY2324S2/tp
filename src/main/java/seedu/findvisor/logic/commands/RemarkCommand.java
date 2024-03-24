package seedu.findvisor.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.findvisor.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.findvisor.model.Model.PREDICATE_SHOW_ALL_PERSONS;

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
 * Updates a remark to an existing person in the address book.
 */
public class RemarkCommand extends Command {

    public static final String COMMAND_WORD = "remark";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Updates a remark of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "r/ [REMARK]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "r/ Plans to own a property by the age of 30";

    public static final String MESSAGE_ADD_REMARK_SUCCESS = "Updated remark for %1$s to %2$s";
    public static final String MESSAGE_REMOVE_REMARK_SUCCESS = "Removed remark for %1$s";

    private final Index index;
    private final Optional<Remark> remark;

    /**
     * @param index of the person in the filtered person list to edit the remark
     * @param remark new remark of the person
     */
    public RemarkCommand(Index index, Optional<Remark> remark) {
        requireAllNonNull(index, remark);

        this.index = index;
        this.remark = remark;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, remark);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        String feedback = remark.map(r -> String.format(MESSAGE_ADD_REMARK_SUCCESS, personToEdit.getName(), r.value))
                .orElse(String.format(MESSAGE_REMOVE_REMARK_SUCCESS, personToEdit.getName()));

        return new CommandResult(feedback);
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code updatedRemark}.
     */
    private static Person createEditedPerson(Person personToEdit, Optional<Remark> updatedRemark) {
        assert personToEdit != null;

        Name name = personToEdit.getName();
        Phone phone = personToEdit.getPhone();
        Email email = personToEdit.getEmail();
        Address address = personToEdit.getAddress();
        Set<Tag> tags = personToEdit.getTags();
        Optional<Meeting> meeting = personToEdit.getMeeting();

        return new Person(name, phone, email, address, tags, meeting, updatedRemark);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemarkCommand)) {
            return false;
        }

        RemarkCommand otherRemarkCommand = (RemarkCommand) other;
        return index.equals(otherRemarkCommand.index) && remark.equals(otherRemarkCommand.remark);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("remark", remark.map(r -> r.value).orElse(""))
                .toString();
    }
}
