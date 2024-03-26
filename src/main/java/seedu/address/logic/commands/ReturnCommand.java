package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Removes a book from the book list of the specific borrower.
 */
public class ReturnCommand extends Command {
    public static final String COMMAND_WORD = "return";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the book list of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing borrow will be overwritten to an empty string.\n"
            + "Parameters: INDEX (must be a positive integer) \n"
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_RETURN_BOOK_SUCCESS = "Removed book from Person: %1$s";

    private final Index index;

    /**
     * Creates a ReturnCommand object.
     *
     * @param index The index of the person returning the book.
     */
    public ReturnCommand(Index index) {
        requireAllNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        if (personToEdit.getBookList().toString().isEmpty()) {
            throw new CommandException(Messages.MESSAGE_EMPTY_BOOKLIST_FIELD);
        }

        // From JinHan, I edited BookList("") --> BookList to allow compilation
        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getMeritScore().incrementScore(),
                new ArrayList<>(), personToEdit.getTags());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message when book title is successfully removed
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        return String.format(MESSAGE_RETURN_BOOK_SUCCESS, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ReturnCommand)) {
            return false;
        }

        ReturnCommand e = (ReturnCommand) other;
        return index.equals(e.index);
    }
}
