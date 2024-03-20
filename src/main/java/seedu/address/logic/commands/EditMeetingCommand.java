package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Description;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditMeetingCommand extends Command {

    public static final String COMMAND_WORD = "edit_meeting";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the meeting identified "
            + "by the index number used in the displayed meeting list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_CLIENT_INDEX + "CLIENT INDEX] "
            + "[" + PREFIX_MEETING_INDEX + "MEETING INDEX] "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_DATETIME + "PHONE] "
            + "[" + PREFIX_CLIENT + "CLIENT] "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CLIENT_INDEX + "1 "
            + PREFIX_MEETING_INDEX + "2 "
            + PREFIX_NAME + "john "
            + PREFIX_DATETIME + "01-01-2024 12:00 "
            + PREFIX_CLIENT + "johndoe@example.com";

    public static final String MESSAGE_EDIT_MEETING_SUCCESS = "Edited Meeting: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_MEETING = "This meeting already exists in the address book.";

    private final Index meetingIndex;
    private final Index clientIndex;

    private final EditMeetingDescriptor editMeetingDescriptor;

    /**
     * @param clientIndex of the person in the filtered person list to edit
     * @param meetingIndex of the meeting in the filtered meeting list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditMeetingCommand(Index meetingIndex, EditMeetingDescriptor editPersonDescriptor, Index clientIndex) {
        requireNonNull(meetingIndex);
        requireNonNull(editPersonDescriptor);
        requireNonNull(clientIndex);

        this.meetingIndex = meetingIndex;
        this.editMeetingDescriptor = new EditMeetingDescriptor(editPersonDescriptor);
        this.clientIndex = clientIndex;

    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Meeting> lastShownList = model.getFilteredMeetingList();

        if (meetingIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Meeting meetingToEdit = lastShownList.get(meetingIndex.getZeroBased());
        Meeting editedMeeting = createEditedMeeting(meetingToEdit, editMeetingDescriptor);

        if (!meetingToEdit.isSameMeeting(editedMeeting) && model.hasMeeting(editedMeeting)) {
            throw new CommandException(MESSAGE_DUPLICATE_MEETING);
        }

        model.setMeeting(meetingToEdit, editedMeeting);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        List<Person> clientList = model.getFilteredPersonList();
        Person selectedClient = clientList.get(this.clientIndex.getZeroBased());

        ArrayList<Meeting> clientMeetingList = selectedClient.getMeetings();
        clientMeetingList.set(meetingIndex.getZeroBased(), editedMeeting);
        selectedClient.setMeetings(clientMeetingList);

        return new CommandResult(String.format(MESSAGE_EDIT_MEETING_SUCCESS, Messages.formatMeeting(editedMeeting)));
    }
    /*
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ArrayList<Meeting> lastShownList = client.getMeetings();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Meeting meetingToEdit = lastShownList.get(index.getZeroBased());
        Meeting editedMeeting = createEditedMeeting(meetingToEdit, editMeetingDescriptor);

        if (!meetingToEdit.isSameMeeting(editedMeeting) && lastShownList.contains(editedMeeting)) {
            throw new CommandException(MESSAGE_DUPLICATE_MEETING);
        }
        lastShownList.set(index.getZeroBased(), editedMeeting);
        client.setMeetings(lastShownList);
        //model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, Messages.formatMeeting(editedMeeting)));
    }
    */
    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Meeting createEditedMeeting(Meeting meetingToEdit, EditMeetingDescriptor editMeetingDescriptor) {
        assert meetingToEdit != null;

        String updatedName = editMeetingDescriptor.getDescription().orElse(meetingToEdit.getDescription());
        LocalDateTime updatedDateTime = editMeetingDescriptor.getDateTime().orElse(meetingToEdit.getDateTime());
        Person updatedClient = editMeetingDescriptor.getClient().orElse(meetingToEdit.getClient());
        //Set<Tag> updatedTags = editMeetingDescriptor.getTags().orElse(meetingToEdit.getTags());
        ArrayList<Meeting> meetings = new ArrayList<Meeting>();
        return new Meeting(updatedName, updatedDateTime, updatedClient);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        EditMeetingCommand otherEditMeeting = (EditMeetingCommand) other;
        return meetingIndex.equals(otherEditMeeting.meetingIndex)
                && editMeetingDescriptor.equals(otherEditMeeting.editMeetingDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", meetingIndex)
                .add("editMeetingDescriptor", editMeetingDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
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

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(description, dateTime, client);
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

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
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
