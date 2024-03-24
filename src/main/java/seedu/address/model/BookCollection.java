package seedu.address.model;

import java.util.ArrayList;

import seedu.address.model.book.Book;

/**
 * The BookCollection class represents a collection of books.
 * It manages operations such as adding, retrieving, and deleting books from the list.
 */
public class BookCollection {
    protected ArrayList<Book> bookList;

    /**
     * Construct an empty BookList.
     */
    public BookCollection() {
        this.bookList = new ArrayList<>();
    }

    /**
     * Construct a BookList with the specified list of books.
     *
     * @param bookList The list of books to initialize the BookCollection with.
     */
    public BookCollection(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }

    /**
     * Add a book to the BookList.
     *
     * @param book The book to be added.
     */
    public void addBook(Book book) {
        this.bookList.add(book);
    }

    /**
     * Retrieve the book at the specified index from the BookCollection.
     *
     * @param index The index of the book to retrieve.
     * @return The book at the specified index.
     */
    public Book getBook(int index) {
        return bookList.get(index);
    }

    /**
     * Delete the book at the specified index from the BookCollection.
     *
     * @param index The index of the book to delete.
     */
    public void deleteBook(int index) {
        this.bookList.remove(index);
    }

}
