package seedu.address.logic.commands;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BORROW;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Borrow;

public class BorrowCommand extends Command {
    public static final String COMMAND_WORD = "borrow";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the borrow of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing borrow will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_BORROW + "[borrow]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_BORROW + "Likes to swim.";

    public static final String MESSAGE_ADD_borrow_SUCCESS = "Added borrow to Person: %1$s";
    public static final String MESSAGE_DELETE_borrow_SUCCESS = "Removed borrow from Person: %1$s";

    private final Index index;
    private final Borrow bookTitle;

    /**
     * @param index     of the person in the filtered person list to edit the
     *                  bookTitle
     * @param bookTitle of the person to be updated to
     */
    public BorrowCommand(Index index, Borrow bookTitle) {
        requireAllNonNull(index, bookTitle);

        this.index = index;
        this.bookTitle = bookTitle;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), bookTitle, personToEdit.getTags());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on whether the bookTitle
     * is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = !bookTitle.value.isEmpty() ? MESSAGE_ADD_borrow_SUCCESS : MESSAGE_DELETE_borrow_SUCCESS;
        return String.format(message, personToEdit);
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BorrowCommand)) {
            return false;
        }

        BorrowCommand e = (BorrowCommand) other;
        return index.equals(e.index)
                && bookTitle.equals(e.bookTitle);
    }
}
