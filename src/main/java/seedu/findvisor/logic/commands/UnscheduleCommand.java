package seedu.findvisor.logic.commands;

import static java.util.Objects.requireNonNull;
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
import seedu.findvisor.model.person.Name;
import seedu.findvisor.model.person.Person;
import seedu.findvisor.model.person.Phone;
import seedu.findvisor.model.tag.Tag;

/**
 * Unschedules a meeting with a person.
 */
public class UnscheduleCommand extends Command {
    public static final String COMMAND_WORD = "unschedule";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Unschedules a meeting with the person identified "
            + "by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_UNSCHEDULE_SUCCESS = "Unscheduled meeting with %1$s";
    public static final String MESSAGE_NO_MEETING_TO_UNSCHEDULE = "No scheduled meeting with %1$s!";

    private final Index targetIndex;

    /**
     * Creates an UnscheduleCommand to unschedule a meeting with the person at the specified {@code Index}
     */
    public UnscheduleCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(targetIndex.getZeroBased());
        if (personToEdit.getMeeting().isEmpty()) {
            throw new CommandException(MESSAGE_NO_MEETING_TO_UNSCHEDULE);
        }
        Person editedPerson = createEditedPerson(personToEdit);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_UNSCHEDULE_SUCCESS, editedPerson.getName()));
    }

    /**
     * Creates and returns a copy of {@code personToEdit} with meeting unscheduled.
     */
    private static Person createEditedPerson(Person personToEdit) {
        assert personToEdit != null;

        Name name = personToEdit.getName();
        Phone phone = personToEdit.getPhone();
        Email email = personToEdit.getEmail();
        Address address = personToEdit.getAddress();
        Set<Tag> tags = personToEdit.getTags();

        return new Person(name, phone, email, address, tags, Optional.empty());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UnscheduleCommand)) {
            return false;
        }

        UnscheduleCommand otherScheduleCommand = (UnscheduleCommand) other;
        return targetIndex.equals(otherScheduleCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toUnschedule", targetIndex)
                .toString();
    }
}
