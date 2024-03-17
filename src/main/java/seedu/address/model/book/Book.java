package seedu.address.model.book;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Book in the book list.
 * Guarantees: immutable;
 */
public class Book {
    public static final String MESSAGE_CONSTRAINTS = "Book titles can take any values, and it should not be blank";
    /*
     * The book title can be empty string to represent no book present or,
     * The first character of the book title must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "(|\\S.*)";
    public final String bookTitle;

    /**
     * Constructs a {@code Book}.
     *
     * @param bookTitle A valid tag name.
     */
    public Book(String bookTitle) {
        requireNonNull(bookTitle);
        checkArgument(isValidBookTitle(bookTitle), MESSAGE_CONSTRAINTS);
        this.bookTitle = bookTitle;

    }

    /**
     * Returns true if a given string is a valid book title.
     * Empty titles or titles that are not blank are valid.
     */
    public static boolean isValidBookTitle(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Book)) {
            return false;
        }

        Book otherBook = (Book) other;
        return bookTitle.equals(otherBook.bookTitle);
    }

    @Override
    public int hashCode() {
        return bookTitle.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return bookTitle;
    }
}
