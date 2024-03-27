package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_NOTES;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.note.Description;
import seedu.address.model.person.note.Note;

/**
 * Edits an appointment in the address book.
 */
public class EditNoteCommand extends Command {

    public static final String COMMAND_WORD = "edit-an";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the appointment note identified "
            + "by the patient index number used in the displayed patient list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: PATIENT_INDEX (must be a positive integer) "
            + "NOTE_INDEX (must be a positive integer) "
            + "[" + PREFIX_DATE + "DATE<DD-MM-YYYY>] "
            + "[" + PREFIX_TIME + "TIME<HHMM>] "
            + "[" + PREFIX_NOTE + "NOTE] "
            + "Example: " + COMMAND_WORD + " 1 1 "
            + PREFIX_DATE + "19-02-2024 "
            + PREFIX_TIME + "1430 "
            + PREFIX_NOTE + "General Flu ";

    public static final String MESSAGE_EDIT_NOTE_SUCCESS = "Edited Note: %1$s";
    public static final String MESSAGE_NOTE_NOT_EDITED = "No fields edited. Please try again.";
    private final Index patientIndex;
    private final Index noteIndex;
    private final EditNoteDescriptor editNoteDescriptor;

    /**
     * @param patientIndex         of the patient with the appointment note to edit
     * @param noteIndex            of the note in the filtered note list to edit
     * @param editNoteDescriptor   details to edit the note with
     */
    public EditNoteCommand(Index patientIndex, Index noteIndex, EditNoteDescriptor editNoteDescriptor) {
        requireNonNull(patientIndex);
        requireNonNull(noteIndex);
        requireNonNull(editNoteDescriptor);

        this.patientIndex = patientIndex;
        this.noteIndex = noteIndex;
        this.editNoteDescriptor = editNoteDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownPatientList = model.getFilteredPersonList();

        if (patientIndex.getZeroBased() >= lastShownPatientList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }

        if (noteIndex.getZeroBased() >= lastShownPatientList.get(patientIndex.getZeroBased()).getNotes().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }

        Person personToEdit = lastShownPatientList.get(patientIndex.getZeroBased());
        Note noteToEdit = personToEdit.getNotes().get(noteIndex.getZeroBased());
        Note editedNote = createEditedNote(noteToEdit, editNoteDescriptor);

        ObservableList<Note> updatedNotes = FXCollections.observableArrayList(personToEdit.getNotes());
        updatedNotes.set(noteIndex.getZeroBased(), editedNote);

        Person updatedPerson = new Person.Builder(personToEdit).setNotes(updatedNotes).build();
        model.setPerson(personToEdit, updatedPerson);

        model.updateFilteredNoteList(PREDICATE_SHOW_ALL_NOTES);
        return new CommandResult(String.format(MESSAGE_EDIT_NOTE_SUCCESS, Messages.format(editedNote)));
    }

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public String getMessageUsage() {
        return MESSAGE_USAGE;
    }

    /**
     * Creates and returns a {@code Note} with the details of {@code noteToEdit}
     * edited with {@code editNoteDescriptor}.
     */
    private static Note createEditedNote(Note noteToEdit, EditNoteDescriptor editNoteDescriptor) {
        assert noteToEdit != null;

        LocalDate updatedDate = editNoteDescriptor.getDate().orElse(noteToEdit.getDateTime().toLocalDate());
        LocalTime updatedTime = editNoteDescriptor.getTime().orElse(noteToEdit.getDateTime().toLocalTime());
        Description updatedDescription = editNoteDescriptor.getDescription().orElse(noteToEdit.getDescription());

        LocalDateTime updatedDateTime = updatedDate.atTime(updatedTime);

        return new Note(updatedDateTime, updatedDescription);
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditNoteCommand)) {
            return false;
        }

        EditNoteCommand otherEditNoteCommand = (EditNoteCommand) other;
        return patientIndex.equals(otherEditNoteCommand.patientIndex)
                && noteIndex.equals(otherEditNoteCommand.noteIndex)
                && editNoteDescriptor.equals(otherEditNoteCommand.editNoteDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("patientIndex", patientIndex)
                .add("noteIndex", noteIndex)
                .add("editNoteDescriptor", editNoteDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the note with. Each non-empty field value will
     * replace the
     * corresponding field value of the person.
     */
    public static class EditNoteDescriptor {
        private LocalDate date;
        private LocalTime time;
        private Description description;

        public EditNoteDescriptor() {
        }

        /**
         * Copy constructor.
         */
        public EditNoteDescriptor(EditNoteDescriptor toCopy) {
            setDate(toCopy.date);
            setTime(toCopy.time);
            setDescription(toCopy.description);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(date, time, description);
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public Optional<LocalDate> getDate() {
            return Optional.ofNullable(date);
        }

        public void setTime(LocalTime time) {
            this.time = time;
        }
        public Optional<LocalTime> getTime() {
            return Optional.ofNullable(time);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }


        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditNoteDescriptor)) {
                return false;
            }

            EditNoteDescriptor otherEditNoteDescriptor = (EditNoteDescriptor) other;
            return Objects.equals(date, otherEditNoteDescriptor.date)
                    && Objects.equals(time, otherEditNoteDescriptor.time)
                    && Objects.equals(description, otherEditNoteDescriptor.description);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("date", date)
                    .add("time", time)
                    .add("description", description)
                    .toString();
        }
    }
}
