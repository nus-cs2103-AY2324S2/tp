package seedu.address.model.book;

import java.util.ArrayList;
import java.util.Comparator;

import seedu.address.model.BookCollection;

/**
 * Represents a collection of books in a library.
 *
 * This class extends the {@link seedu.address.model.BookCollection} class and provides additional functionality
 * specific to managing a library of books, such as sorting books alphabetically and generating a formatted list
 * of books.
 */
public class Library extends BookCollection {

    /**
     * Comparator for comparing books alphabetically by title.
     */
    public static Comparator<Book> bookComparator = new Comparator<Book>() {
        @Override
        public int compare(Book book1, Book book2) {
            return book1.bookTitle.compareTo(book2.bookTitle);
        }
    };

    /**
     * Construct an empty library.
     */
    public Library() {
        super();
    }

    /**
     * Construct a library with the specified list of books.
     *
     * @param bookCollection The list of books to initialize the library with.
     */
    public Library(ArrayList<Book> bookCollection) {
        super(bookCollection);
    }

    /**
     * Sort the books in the library alphabetically by title.
     */
    public void sortAlphabetically() {
        bookCollection.sort(bookComparator);
    }

    /**
     * Retrieve a list of books from the library, sorted alphabetically by title.
     *
     * @return An ArrayList containing the books in the library, sorted alphabetically.
     */
    public ArrayList<Book> list() {
        this.sortAlphabetically();
        return this.bookCollection;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 1; i < this.list().size() + 1; i++) {
            result += i + ". " + this.getBook(i - 1).bookTitle.toString();
            if (i != this.list().size() - 1) {
                result += "\n";
            }
        }
        return result;
    }
    
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof Library) {
            Library otherBookCollection = (Library) other;
            for (int i = 0; i < this.list().size(); i++) {
                if (this.getBook(i).equals(otherBookCollection.getBook(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
