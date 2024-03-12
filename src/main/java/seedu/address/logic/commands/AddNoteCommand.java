package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.note.Note;

/**
 * Adds a person to the address book.
 */
public class AddNoteCommand extends Command {

    public static final String COMMAND_WORD = "add-an";
    public static final Prefix PREFIX_DATE = new Prefix("d/");
    public static final Prefix PREFIX_TIME = new Prefix("t/");
    public static final Prefix PREFIX_NOTE = new Prefix("n/");


    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an appointment note to the specified person. "
        + "Parameters: "
        + PREFIX_DATE + "DATE<DD-MM-YYYY> "
        + PREFIX_TIME + "TIME<HHMM> "
        + PREFIX_NOTE + "NOTE "
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_DATE + "19-02-2024 "
        + PREFIX_TIME + "1430 "
        + PREFIX_NOTE + "General FLu ";

    public static final String MESSAGE_SUCCESS = "New appointment note added: %1$s";

    private final Index personIndex;
    private final Note note;

    /**
     * Creates an AddNoteCommand to add a {@code Note} to a given {@code Person}
     */
    public AddNoteCommand(Index personIndex, Note note) {
        requireAllNonNull(personIndex, note);
        this.personIndex = personIndex;
        this.note = note;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> persons = model.getFilteredPersonList();

        if (personIndex.getZeroBased() >= persons.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }

        Person person = persons.get(personIndex.getZeroBased());
        Person.Builder builder = new Person.Builder(person);
        builder.getNotes().add(note);

        model.setPerson(person, builder.build());
        // TODO: Show notes for a given person.

        return new CommandResult(String.format(MESSAGE_SUCCESS, note.getDescription()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AddNoteCommand that = (AddNoteCommand) o;
        return Objects.equals(personIndex, that.personIndex) && Objects.equals(note, that.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personIndex, note);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("personIndex", personIndex)
            .add("note", note)
            .toString();
    }
}
