package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import seedu.address.model.book.Book;

/**
 * Represents a borrower's book.
 */
public class BookList {
    public final Book value;

    /**
     * Constructs an {@code BookList}.
     *
     * @param bookTitle A valid email address.
     */
    public BookList(String bookTitle) {
        requireNonNull(bookTitle);
        value = new Book(bookTitle);
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BookList // instanceof handles nulls
                && value.equals(((BookList) other).value)); // state check
    }

    public Book getBook() {
        return value;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
