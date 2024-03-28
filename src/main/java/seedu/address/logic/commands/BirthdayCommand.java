package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Person;

/**
 * Adds the birthday to a new contact in the address book, or
 * Changes the birthday of an existing person in the address book
 */
public class BirthdayCommand extends Command {

    public static final String COMMAND_WORD = "birthday";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Birthday: %2$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the remark of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing birthday date will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "b/[BIRTHDAY in YYYY-MM-DD]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "b/2000-02-02";
    public static final String MESSAGE_ADD_BIRTHDAY_SUCCESS = "Added birthday to Person: %1$s";
    public static final String MESSAGE_DELETE_BIRTHDAY_SUCCESS = "Removed birthday from Person: %1$s";

    private final Index index;
    private final Birthday birthday;

    /**
     * @param index of the person in the filtered person list to edit the remark
     * @param birthday of the person to be updated to
     */
    public BirthdayCommand(Index index, Birthday birthday) {
        requireAllNonNull(index, birthday);

        this.index = index;
        this.birthday = birthday;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getBirthday(), personToEdit.getInstrument(),
                personToEdit.getTags(), personToEdit.getAttendances());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on whether
     * the remark is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = !birthday.value.isEmpty() ? MESSAGE_ADD_BIRTHDAY_SUCCESS : MESSAGE_DELETE_BIRTHDAY_SUCCESS;
        return String.format(message, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BirthdayCommand)) {
            return false;
        }

        BirthdayCommand e = (BirthdayCommand) other;
        return index.equals(e.index)
                && birthday.equals(e.birthday);
    }

}
