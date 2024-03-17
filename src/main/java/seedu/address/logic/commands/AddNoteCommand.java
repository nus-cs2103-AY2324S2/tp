package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.IdentityCardNumberMatchesPredicate;
import seedu.address.model.person.Note;
import seedu.address.model.person.Person;

/**
 * Changes the note of an existing person in the address book.
 */
public class AddNoteCommand extends Command {

    public static final String COMMAND_WORD = "addnote";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the note of the person whose profile matches "
            + "the specified IC (case-insensitive). "
            + "Existing remark will be appended by default. To replace the original note, add -replace at "
            + "the end of your command. E.g. addnote i/S0123456Q n/Diabetes -replace\n"
            + "Parameters: "
            + PREFIX_IC + "IC "
            + PREFIX_NOTE + "NOTE \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_IC + " S0123456Q "
            + PREFIX_NOTE + "Healthy.";


    public static final String MESSAGE_MODIFY_NOTE_SUCCESS = "%1$s's note modified successfully!";
    private final IdentityCardNumberMatchesPredicate icPredicate;
    private final Note note;
    private final boolean isReplace;

    /**
     * @param icPredicate of the person in the filtered person list to edit the note
     * @param note of the person to be updated to
     */
    public AddNoteCommand(IdentityCardNumberMatchesPredicate icPredicate, Note note, boolean isReplace) {
        requireAllNonNull(icPredicate, note);

        this.icPredicate = icPredicate;
        this.note = note;
        this.isReplace = isReplace;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.updateFilteredPersonList(icPredicate);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (lastShownList.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON);
        }

        Person personToEdit = lastShownList.get(0);
        Person editedPerson;

        if (isReplace) {
            editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                    personToEdit.getIdentityCardNumber(), personToEdit.getAge(), personToEdit.getSex(),
                    personToEdit.getAddress(), note, personToEdit.getTags());
        } else {
            Note updatedNote = personToEdit.getNote().append("\n" + note.toString());
            editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                    personToEdit.getIdentityCardNumber(), personToEdit.getAge(), personToEdit.getSex(),
                    personToEdit.getAddress(), updatedNote, personToEdit.getTags());
        }
        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on whether the remark is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        return String.format(MESSAGE_MODIFY_NOTE_SUCCESS, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddNoteCommand)) {
            return false;
        }

        AddNoteCommand e = (AddNoteCommand) other;
        return icPredicate.equals(e.icPredicate)
                && note.equals(e.note)
                && isReplace == e.isReplace;
    }
}
