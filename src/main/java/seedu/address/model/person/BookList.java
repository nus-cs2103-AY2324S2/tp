package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a borrower's book.
 */
public class BookList {
    public final String value;

    /**
     * Constructs an {@code Borrow}.
     *
     * @param bookTitle A valid email address.
     */
    public BookList(String bookTitle) {
        requireNonNull(bookTitle);
        value = bookTitle;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BookList // instanceof handles nulls
                && value.equals(((BookList) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
