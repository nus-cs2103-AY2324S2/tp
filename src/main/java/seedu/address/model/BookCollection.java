package seedu.address.model;

import java.util.ArrayList;

import seedu.address.model.book.Book;

/**
 * The BookCollection class represents a collection of books.
 * It manages operations such as adding, retrieving, and deleting books from the collection.
 */
public class BookCollection {
    public ArrayList<Book> bookCollection;

    /**
     * Construct an empty BookCollection.
     */
    public BookCollection() {
        this.bookCollection = new ArrayList<>();
    }

    /**
     * Construct a BookCollection with the specified list of books.
     *
     * @param bookCollection The list of books to initialize the BookCollection with.
     */
    public BookCollection(ArrayList<Book> bookCollection) {
        this.bookCollection = bookCollection;
    }

    /**
     * Add a book to the BookCollection.
     *
     * @param book The book to be added.
     */
    public void addBook(Book book) {
        this.bookCollection.add(book);
    }

    /**
     * Retrieve the book at the specified index from the BookCollection.
     *
     * @param index The index of the book to retrieve.
     * @return The book at the specified index.
     */
    public Book getBook(int index) {
        return bookCollection.get(index);
    }

    /**
     * Delete the book at the specified index from the BookCollection.
     *
     * @param index The index of the book to delete.
     */
    public void deleteBook(int index) {
        this.bookCollection.remove(index);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof BookCollection) {
            BookCollection otherBookCollection = (BookCollection) other;
            for (int i = 0; i < bookCollection.size(); i++) {
                if (this.getBook(i).equals(otherBookCollection.getBook(i))) {
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
