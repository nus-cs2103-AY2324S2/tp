package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.book.Book;
public class JsonAdaptedBook {

    private final String bookTitle;

    @JsonCreator
    public JsonAdaptedBook(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public JsonAdaptedBook(Book source) {
        bookTitle = source.bookTitle;
    }

    @JsonValue
    public String getBookTitle() {
        return this.bookTitle;
    }

    public Book toModelType() throws IllegalValueException {
        if (!Book.isValidBookTitle(bookTitle)) {
            throw new IllegalValueException(Book.MESSAGE_CONSTRAINTS);
        }
        return new Book(bookTitle);
    }
}
