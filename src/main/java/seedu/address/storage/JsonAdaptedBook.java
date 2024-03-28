package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.book.Book;

/**
 * Jackson-friendly version of {@link Book}.
 */
public class JsonAdaptedBook {

    private final String bookTitle;

    /**
     * Constructs a {@code JsonAdaptedBook} with the given book title.
     *
     * @param bookTitle The title of the book.
     */
    @JsonCreator
    public JsonAdaptedBook(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    /**
     * Converts a given {@code Book} into this class for Jackson use.
     *
     * @param source The source book.
     */
    public JsonAdaptedBook(Book source) {
        bookTitle = source.bookTitle;
    }

    /**
     * Returns the book title.
     *
     * @return The book title.
     */
    @JsonValue
    public String getBookTitle() {
        return this.bookTitle;
    }

    /**
     * Converts this Jackson-friendly adapted book object into the model's {@code Book} object.
     *
     * @return The model's Book object.
     * @throws IllegalValueException If there were any data constraints violated in the adapted book.
     */
    public Book toModelType() throws IllegalValueException {
        if (!Book.isValidBookTitle(bookTitle)) {
            throw new IllegalValueException(Book.MESSAGE_CONSTRAINTS);
        }
        return new Book(bookTitle);
    }
}
