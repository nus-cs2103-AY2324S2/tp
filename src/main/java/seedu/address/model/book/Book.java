package seedu.address.model.book;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Book in the book list.
 * Guarantees: immutable;
 */
public class Book {
    public final String bookTitle;

    /**
     * Constructs a {@code Book}.
     *
     * @param bookTitle A valid tag name.
     */
    public Book(String bookTitle) {
        requireNonNull(bookTitle);
        this.bookTitle = bookTitle;
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
        return '[' + "Title:" + bookTitle + ']';
    }
}
