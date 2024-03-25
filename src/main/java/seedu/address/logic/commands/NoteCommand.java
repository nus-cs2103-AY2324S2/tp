package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STARTUPS;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.startup.Note;
import seedu.address.model.startup.Startup;

/**
 * Edits a Note of a startup in the address book!
 */
public class NoteCommand extends Command {

    public static final String COMMAND_WORD = "note";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the notes of the startup identified "
            + "by the index number used in the displayed startup list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[NOTE]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "Lovely Smell ";

    public static final String MESSAGE_EDIT_STARTUP_SUCCESS = "Edited Note of Startup: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_STARTUP = "This startup already exists in the address book.";

    private final Index index;
    private final NoteStartupDescriptor noteStartupDescriptor;

    /**
     * @param index of the startup in the filtered startup list to edit
     * @param noteStartupDescriptor details to edit the startup with
     */
    public NoteCommand(Index index, NoteStartupDescriptor noteStartupDescriptor) {
        requireNonNull(index);
        requireNonNull(noteStartupDescriptor);

        this.index = index;
        this.noteStartupDescriptor = new NoteStartupDescriptor(noteStartupDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Startup> lastShownList = model.getFilteredStartupList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STARTUP_DISPLAYED_INDEX);
        }

        Startup startupToEdit = lastShownList.get(index.getZeroBased());
        Startup editedStartup = createEditedStartup(startupToEdit, noteStartupDescriptor);

        model.setStartup(startupToEdit, editedStartup);
        model.updateFilteredStartupList(PREDICATE_SHOW_ALL_STARTUPS);
        return new CommandResult(String.format(MESSAGE_EDIT_STARTUP_SUCCESS, Messages.format(editedStartup)));
    }

    /**
     * Creates and returns a {@code Startup} with the details of {@code startupToEdit}
     * edited with {@code editStartupDescriptor}.
     */
    private static Startup createEditedStartup(Startup startupToEdit, NoteStartupDescriptor noteStartupDescriptor) {
        assert startupToEdit != null;

        Note updatedNote = noteStartupDescriptor.getNote().orElse(startupToEdit.getNote());

        return new Startup(startupToEdit.getName(), startupToEdit.getFundingStage(), startupToEdit.getIndustry(),
                startupToEdit.getPhone(), startupToEdit.getEmail(), startupToEdit.getAddress(),
                startupToEdit.getTags(), updatedNote);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NoteCommand)) {
            return false;
        }

        NoteCommand otherNoteCommand = (NoteCommand) other;
        return index.equals(otherNoteCommand.index)
                && noteStartupDescriptor.equals(otherNoteCommand.noteStartupDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("noteStartupDescriptor", noteStartupDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the startup with. Each non-empty field value will replace the
     * corresponding field value of the startup.
     */
    public static class NoteStartupDescriptor {
        private Note note;

        public NoteStartupDescriptor() {}

        // Copy constructor
        public NoteStartupDescriptor(NoteStartupDescriptor toCopy) {
            setNote(toCopy.note);
        }

        public void setNote(Note note) {
            this.note = note;
        }

        public Optional<Note> getNote() {
            return Optional.ofNullable(note);
        }

        // Returns true if the note field is edited.
        public boolean isAnyFieldEdited() {
            return note != null;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof NoteStartupDescriptor)) {
                return false;
            }
            NoteStartupDescriptor that = (NoteStartupDescriptor) other;
            return Objects.equals(note, that.note);
        }

        @Override
        public int hashCode() {
            return Objects.hash(note);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("note", note)
                    .toString();
        }
    }
}
