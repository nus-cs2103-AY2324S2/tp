package seedu.address.model.person;

import java.util.ArrayList;

import seedu.address.model.BookCollection;
import seedu.address.model.book.Book;

/**
 * Represents a borrower's book.
 */
public class BookList extends BookCollection {

    /**
     * Construct an empty BookCollection.
     */
    public BookList() {
        super();
    }

    /**
     * Constructs an {@code BookList}.
     *
     * @param bookList A valid email address.
     */
    public BookList(ArrayList<Book> bookList) {
        super(bookList);
    }

    /**
     * To check whether the book collection has books
     */
    public Boolean hasBooks() {
        if (bookCollection == null || bookCollection.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < bookCollection.size(); i++) {
            result += this.getBook(i).bookTitle.toString();
            if (i != bookCollection.size() - 1) {
                result += "\n";
            }
        }
        return result;
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof BookList) {
            BookList otherBookList = (BookList) other;
            for (int i = 0; i < bookCollection.size(); i++) {
                if (this.getBook(i).equals(otherBookList.getBook(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    @Override
    public int hashCode() {
        return bookCollection.hashCode();
    }
}
