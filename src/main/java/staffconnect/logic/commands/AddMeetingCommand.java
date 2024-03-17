package staffconnect.logic.commands;

import static java.util.Objects.requireNonNull;
import static staffconnect.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static staffconnect.logic.parser.CliSyntax.PREFIX_STARTDATE;
import static staffconnect.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import staffconnect.commons.core.index.Index;
import staffconnect.commons.util.ToStringBuilder;
import staffconnect.logic.Messages;
import staffconnect.logic.commands.exceptions.CommandException;
import staffconnect.model.Model;
import staffconnect.model.meeting.Meeting;
import staffconnect.model.person.Email;
import staffconnect.model.person.Module;
import staffconnect.model.person.Name;
import staffconnect.model.person.Person;
import staffconnect.model.person.Phone;
import staffconnect.model.person.Venue;
import staffconnect.model.tag.Tag;

/**
 * Adds a meeting to the staff book.
 */
public class AddMeetingCommand extends Command {

    public static final String COMMAND_WORD = "meeting";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a meeting to the person identified "
        + "by the index number used in the displayed person list. "
        + "Existing values will be overwritten by the input values.\n"
        + "Parameters: INDEX (must be a positive integer) " + PREFIX_DESCRIPTION + "DESCRIPTION " + PREFIX_STARTDATE
        + "DATETIME[dd/MM/yyyy HH:mm]" + "Example: " + COMMAND_WORD + " 1 " + PREFIX_DESCRIPTION + "Meet for finals "
        + PREFIX_STARTDATE + "12/04/2023 18:00";

    public static final String MESSAGE_SUCCESS = "New meeting added: %1$s";
    public static final String MESSAGE_DUPLICATE_MEETING = "This meeting is already planned for this person!";

    private final Meeting toAdd;
    private final Index index;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddMeetingCommand(Index index, Meeting meeting) {
        requireNonNull(index);
        requireNonNull(meeting);
        this.index = index;
        toAdd = meeting;

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person personToEdit = lastShownList.get(index.getZeroBased());

        if (personToEdit.hasDuplicateMeeting(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_MEETING);
        }


        Person editedPerson = addMeetingToPerson(personToEdit, toAdd);
        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    /**
     * Creates and returns a {@code Person} with details of {@code meeting}
     */
    private static Person addMeetingToPerson(Person personToEdit, Meeting meeting) {
        assert personToEdit != null;

        Name currentName = personToEdit.getName();
        Phone currentPhone = personToEdit.getPhone();
        Email currentEmail = personToEdit.getEmail();
        Venue currentVenue = personToEdit.getVenue();
        Module currentModule = personToEdit.getModule();
        Set<Tag> currentTags = personToEdit.getTags();
        Set<Meeting> currentMeetings = new HashSet<>(personToEdit.getMeetings()); //to reduce coupling with Person
        Person editedPerson = new Person(currentName, currentPhone, currentEmail, currentVenue, currentModule,
                                         currentTags);
        currentMeetings.add(meeting);
        editedPerson.setMeetings(currentMeetings);
        return editedPerson;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddMeetingCommand)) {
            return false;
        }

        AddMeetingCommand otherAddMeetingCommand = (AddMeetingCommand) other;
        return index.equals(otherAddMeetingCommand.index) && toAdd.equals(otherAddMeetingCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("toAdd", toAdd).toString();
    }
}
