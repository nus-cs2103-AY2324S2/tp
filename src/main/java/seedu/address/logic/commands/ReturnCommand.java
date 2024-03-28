package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKLIST;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.book.Book;
import seedu.address.model.person.Person;

/**
 * Removes a book from the book list of the specific borrower.
 */
public class ReturnCommand extends Command {
    public static final String COMMAND_WORD = "return";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the book list of the person identified "
            + "by the index number used in the last person listing. "
            + "If book exists in the person's book list, it will be removed. \n"
            + "Parameters: INDEX (must be a positive integer) \n"
            + PREFIX_BOOKLIST + "[borrow]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_BOOKLIST + "Likes to swim.";

    public static final String MESSAGE_RETURN_BOOK_SUCCESS = "Removed book: %1$s from Person: %2$s";

    private final Index index;
    private final Book book;

    /**
     * Creates a ReturnCommand object.
     *
     * @param index The index of the person returning the book.
     */
    public ReturnCommand(Index index, Book book) {
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

        // Checks whether the borrowed book is an empty field.
        if (book.equals(new Book(""))) {
            throw new CommandException(Messages.MESSAGE_EMPTY_BOOK_INPUT_FIELD);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        if (personToEdit.getBookList().toString().isEmpty()) {
            throw new CommandException(Messages.MESSAGE_EMPTY_BOOKLIST_FIELD);
        }

        if (!personToEdit.getBookList().contains(book)) {
            throw new CommandException(Messages.MESSAGE_BOOK_DOES_NOT_EXIST);
        }

        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getMeritScore().incrementScore(),
                personToEdit.getBookListWithoutBook(book), personToEdit.getTags());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.addBook(book);

        return new CommandResult(generateSuccessMessage(book, editedPerson));
    }

    /**
     * Generates a command execution success message when book title is successfully removed
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Book book, Person personToEdit) {
        return String.format(MESSAGE_RETURN_BOOK_SUCCESS, book, personToEdit);
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
        return index.equals(e.index)
                && book.equals(e.book);
    }
}
