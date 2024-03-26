package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKLIST;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.book.Book;
import seedu.address.model.person.Person;

/**
 * Adds a book to the book list to the specific borrower.
 */
public class BorrowCommand extends Command {
    public static final String COMMAND_WORD = "borrow";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the book list of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing borrow will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_BOOKLIST + "[borrow]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_BOOKLIST + "Likes to swim.";

    public static final String MESSAGE_ADD_BORROW_SUCCESS = "Added book to Person: %1$s";

    private final Index index;
    private final Book book;

    /**
     * @param index     of the person in the filtered person list to edit the
     *                  bookTitle
     * @param book of the person to be updated to
     */
    public BorrowCommand(Index index, Book book) {
        requireAllNonNull(index, book);
        this.index = index;
        this.book = book;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        // Check whether the borrowed book is empty field
        if (book.equals(new Book(""))) {
            throw new CommandException(Messages.MESSAGE_EMPTY_BOOK_INPUT_FIELD);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        // Check whether personToEdit has sufficient merit score
        if (personToEdit.getMeritScore().getMeritScoreInt() <= 0) {
            throw new CommandException(Messages.MESSAGE_INSUFFICIENT_MERIT_SCORE);
        }

        personToEdit.getBookList().add(book);
        ArrayList<Book> updatedBookList = personToEdit.getBookList();

        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
            personToEdit.getAddress(), personToEdit.getMeritScore().decrementScore(), updatedBookList,
                personToEdit.getTags());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Formats and returns the borrow success message.
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        return String.format(MESSAGE_ADD_BORROW_SUCCESS, personToEdit);
    }

    @Override
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
                && book.equals(e.book);
    }
}
