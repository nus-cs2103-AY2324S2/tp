package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;

/**
 * Edits the details of an existing meeting in the address book.
 */
public class EditMeetingCommand extends Command {

    public static final String COMMAND_WORD = "editMeeting";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the meeting identified "
            + "by the index number used in the displayed meeting list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_CLIENT_INDEX + "CLIENT INDEX "
            + PREFIX_MEETING_INDEX + "MEETING INDEX "
            + PREFIX_NAME + "NAME "
            + PREFIX_DATETIME + "DATETIME \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CLIENT_INDEX + "1 "
            + PREFIX_MEETING_INDEX + "2 "
            + PREFIX_NAME + "starbucks meeting "
            + PREFIX_DATETIME + "01-01-2024 12:00 ";

    public static final String MESSAGE_EDIT_MEETING_SUCCESS = "Edited Meeting: %1$s";
    public static final String MEETING_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_MEETING = "This meeting already exists in the address book.";

    private final Index meetingIndex;
    private final Index clientIndex;

    private final EditMeetingDescriptor editMeetingDescriptor;

    /**
     * @param clientIndex of the person in the filtered person list to edit
     * @param meetingIndex of the meeting in the filtered meeting list to edit
     * @param editMeetingDescriptor details to edit the meeting with
     */
    public EditMeetingCommand(Index clientIndex, EditMeetingDescriptor editMeetingDescriptor, Index meetingIndex) {
        requireNonNull(meetingIndex);
        requireNonNull(editMeetingDescriptor);
        requireNonNull(clientIndex);

        this.meetingIndex = meetingIndex;
        this.editMeetingDescriptor = new EditMeetingDescriptor(editMeetingDescriptor);
        this.clientIndex = clientIndex;

    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> clientList = model.getFilteredPersonList();

        if (clientIndex.getZeroBased() >= clientList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person selectedClient = clientList.get(clientIndex.getZeroBased());
        List<Meeting> clientMeetingList = selectedClient.getMeetings();
        System.out.println(meetingIndex);
        System.out.println(clientMeetingList);

        if (meetingIndex.getZeroBased() >= clientMeetingList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }

        Meeting meetingToEdit = clientList.get(clientIndex.getZeroBased())
                .getMeetings().get(meetingIndex.getZeroBased());
        Meeting editedMeeting = createEditedMeeting(meetingToEdit, editMeetingDescriptor);

        if (meetingToEdit.isSameMeeting(editedMeeting) && model.hasMeeting(editedMeeting)) {
            throw new CommandException(MESSAGE_DUPLICATE_MEETING);
        }

        DeleteMeetingCommand deleteMeetingCommand = new DeleteMeetingCommand(clientIndex, meetingIndex);
        AddMeetingCommand addMeetingCommand = new AddMeetingCommand(editedMeeting.getDateTime(),
                editedMeeting.getDescription(), clientIndex);
        deleteMeetingCommand.execute(model);
        addMeetingCommand.execute(model);
        return new CommandResult(String.format(MESSAGE_EDIT_MEETING_SUCCESS, Messages.formatMeeting(editedMeeting)));
    }

    /**
     * Creates and returns a {@code Meeting} with the details of {@code meetingToEdit}
     * edited with {@code editMeetingDescriptor}.
     */
    private static Meeting createEditedMeeting(Meeting meetingToEdit, EditMeetingDescriptor editMeetingDescriptor) {
        assert meetingToEdit != null;

        String updatedName = editMeetingDescriptor.getDescription().orElse(meetingToEdit.getDescription());
        LocalDateTime updatedDateTime = editMeetingDescriptor.getDateTime().orElse(meetingToEdit.getDateTime());
        Person updatedClient = editMeetingDescriptor.getClient().orElse(meetingToEdit.getClient());
        return new Meeting(updatedName, updatedDateTime, updatedClient);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditMeetingCommand)) {
            return false;
        }

        EditMeetingCommand otherEditMeeting = (EditMeetingCommand) other;
        return meetingIndex.equals(otherEditMeeting.meetingIndex)
                && editMeetingDescriptor.equals(otherEditMeeting.editMeetingDescriptor)
                && clientIndex.equals(otherEditMeeting.clientIndex);
    }

    public Index getClientIndex() {
        return clientIndex;
    }

    public Index getMeetingIndex() {
        return meetingIndex;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("clientIndex", clientIndex)
                .add("editMeetingDescriptor", editMeetingDescriptor)
                .add("meetingIndex", meetingIndex)
                .toString();
    }

    /**
     * Stores the details to edit the meeting with. Each non-empty field value will replace the
     * corresponding field value of the meeting.
     */
    public static class EditMeetingDescriptor {
        private String description;

        private LocalDateTime dateTime;
        private Person client;

        public EditMeetingDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditMeetingDescriptor(EditMeetingDescriptor toCopy) {
            setDescription(toCopy.description);
            setDateTime(toCopy.dateTime);
            setClient(toCopy.client);
        }

        public void setDescription(String name) {
            this.description = name;
        }

        public Optional<String> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
        }

        public Optional<LocalDateTime> getDateTime() {
            return Optional.ofNullable(dateTime);
        }

        public void setClient(Person client) {
            this.client = client;
        }

        public Optional<Person> getClient() {
            return Optional.ofNullable(client);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditMeetingDescriptor)) {
                return false;
            }

            EditMeetingDescriptor otherEditPersonDescriptor = (EditMeetingDescriptor) other;
            return Objects.equals(description, otherEditPersonDescriptor.description)
                    && Objects.equals(dateTime, otherEditPersonDescriptor.dateTime)
                    && Objects.equals(client, otherEditPersonDescriptor.client);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("description", description)
                    .add("dateTime", dateTime)
                    .add("client", client)
                    .toString();
        }
    }
}
