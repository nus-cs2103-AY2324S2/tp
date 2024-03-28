package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Meeting;
import seedu.address.model.person.Person;

/**
 * Changes the meeting of an existing person in the address book.
 */
public class AddMeetingCommand extends Command {

    public static final String COMMAND_WORD = "mtg";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a meeting to the person identified by the contact name.\n"
            + "mtg [CONTACT_NAME] m/[MTG_DESC] time/[TIMING]\n"
            + "Example: " + COMMAND_WORD + " Alex Tan "
            + "m/Interview t/23-03-2024 1600-1700";

    public static final String MESSAGE_ADD_MEETING_SUCCESS = "Added the meeting with %1$s. The meeting details "
            + "are as follows:\n" + "%2$s\n";
    public static final String MESSAGE_DELETE_MEETING_SUCCESS = "Removed the meeting with %1$s.";

    public static final String MESSAGE_PERSON_NOT_FOUND = "Oops, %1$s's contact does not exist. Unable to add "
            + "meeting.";
    public static final String MESSAGE_EMPTY_NAME = "Oops, please state the name of the contact.";

    private static Logger logger = Logger.getLogger("MeetingLogger");
    private final String name;
    private final Meeting meeting;

    /**
     * @param name  of the person in the filtered person list to edit the meeting
     * @param meeting of the person to be updated to
     */
    public AddMeetingCommand(String name, Meeting meeting) {
        requireAllNonNull(name, meeting);

        this.name = name;
        this.meeting = meeting;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        logger.log(Level.INFO, "going to execute the AddMeetingCommand");
        if (name.isEmpty()) {
            logger.log(Level.WARNING, "empty name inputted.");
            throw new CommandException(MESSAGE_EMPTY_NAME);
        }
        List<Person> contactList = model.getFilteredPersonList();
        Person personToEdit = null;
        for (Person person : contactList) {
            if (person.getName().fullName.equalsIgnoreCase(name)) {
                personToEdit = person;
                break;
            }
        }
        if (personToEdit == null) {
            throw new CommandException(String.format(MESSAGE_PERSON_NOT_FOUND, name));
        }
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getCompany(), meeting, personToEdit.getPriority(),
                personToEdit.isStarred(), personToEdit.getTags());
        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on whether
     * the meeting is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = !meeting.toString().isEmpty() ? MESSAGE_ADD_MEETING_SUCCESS : MESSAGE_DELETE_MEETING_SUCCESS;
        String result = String.format(message, personToEdit.getName(), meeting.toString());
        return result;
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

        AddMeetingCommand e = (AddMeetingCommand) other;
        return name.equals(e.name)
                && meeting.equals(e.meeting);
    }
}
