package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_NOTES;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.note.Note;

/**
 * Lists all notes in the address book to the user.
 */
public class ListNoteCommand extends Command {

    public static final String COMMAND_WORD = "list-an";

    public static final String MESSAGE_SUCCESS = "Listed all notes";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all appointment notes of the patient identified "
            + "by the index number used in the displayed patient list. "
            + "Parameters: INDEX (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " 1 ";

    private final Optional<Index> patientIndex;

    /**
     * @param patientIndex of the patient in the filtered patient list
     */
    public ListNoteCommand(Index patientIndex) {
        requireNonNull(patientIndex);
        this.patientIndex = Optional.of(patientIndex);
    }

    /**
     * Creates an ListNoteCommand to list all appointment notes
     */
    public ListNoteCommand() {
        this.patientIndex = Optional.empty();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (patientIndex.isEmpty()) {
            requireNonNull(model);
            model.updateFilteredNoteList(PREDICATE_SHOW_ALL_NOTES);
            return new CommandResult(MESSAGE_SUCCESS);
        }

        List<Person> lastShownList = model.getFilteredPersonList();
        Index index = patientIndex.get();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }

        Person selectedPerson = lastShownList.get(index.getZeroBased());
        ObservableList<Note> notes = selectedPerson.getNotes();

        Predicate<Note> showSelectedPersonNotesPredicate = note -> notes.contains(note);

        model.updateFilteredNoteList(showSelectedPersonNotesPredicate);

        return new CommandResult(String.format(Messages.MESSAGE_LIST_NOTE_SUCCESS, selectedPerson.getName()));
    }
}