package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Maintainer;
import seedu.address.model.person.Name;
import seedu.address.model.person.Note;
import seedu.address.model.person.Person;
import seedu.address.model.person.Staff;
import seedu.address.model.person.Supplier;

/**
 * Adds a note of an existing person in the address book.
 * A non-empty note must be specified.
 */
public class NoteCommand extends Command {

    public static final String MESSAGE_ADD_NOTE_SUCCESS = "Added note to Person: %1$s";
    public static final String COMMAND_WORD = "/note";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds note to person.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME, "
            + PREFIX_NOTE + "NOTE "
            + "Example: " + COMMAND_WORD + " Moochie" + " Meet at 6pm tuesday";
    private final Name name;
    private final Note note;

    /**
     * @param name of the person in the filtered person list to edit the note
     * @param note of the person to be updated to
     */
    public NoteCommand(Name name, Note note) {
        requireAllNonNull(name, note);
        this.name = name;
        this.note = note;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        Person personToEdit = findByName(lastShownList, name);
        if (personToEdit instanceof Maintainer || personToEdit instanceof Staff
                || personToEdit instanceof Supplier) {
            throw new CommandException("Person is not of general person class");
        }
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), note, personToEdit.getTags());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = MESSAGE_ADD_NOTE_SUCCESS;
        return String.format(message, personToEdit);
    }

    /**
     * Finds a person from a List of persons identified by its name.
     *
     * @param personList The list of persons to search from.
     * @param targetName The name of the person to return.
     *
     * @return The person object with name equals to {@code targetName}.
     * */
    public Person findByName(List<Person> personList, Name targetName) {
        for (Person person: personList) {
            Name name = person.getName();
            if (name.equals(targetName)) {
                return person;
            }
        }
        return null;
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

        NoteCommand e = (NoteCommand) other;
        return name.equals(e.name)
                && note.equals(e.note);
    }
}
