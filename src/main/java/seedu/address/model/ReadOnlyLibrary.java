package seedu.address.model;

import java.util.ArrayList;

import seedu.address.model.book.Book;
import seedu.address.model.library.Threshold;

/**
 * Unmodifiable view of a library.
 */
public interface ReadOnlyLibrary {
    /**
     * Returns an unmodifiable view of the book list.
     */
    ArrayList<Book> getBookList();

    /**
     * Returns an unmodifiable view of the threshold.
     */
    Threshold getThreshold();
}
