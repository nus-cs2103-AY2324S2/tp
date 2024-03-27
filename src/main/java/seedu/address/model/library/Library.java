package seedu.address.model.library;

import java.util.ArrayList;
import java.util.Comparator;

import seedu.address.model.book.Book;
import seedu.address.model.person.Person;

/**
 * Represents a collection of books in a library.
 */
public class Library {

    /**
     * Comparator for comparing books alphabetically by title.
     */
    private static Comparator<Book> bookComparator = new Comparator<Book>() {
        @Override
        public int compare(Book book1, Book book2) {
            return book1.bookTitle.compareTo(book2.bookTitle);
        }
    };

    private ArrayList<Book> library;
    private Threshold threshold;

    /**
     * Construct an empty library.
     */
    public Library() {
        library = new ArrayList<>();
        threshold = new Threshold();
    }

    /**
     * Construct an empty library with preset threshold.
     */
    public Library(int i) {
        library = new ArrayList<>();
        threshold = new Threshold(i);
    }

    /**
     * Construct a library with the specified list of books.
     *
     * @param library The list of books to initialize the library with.
     */
    public Library(ArrayList<Book> library) {
        this.library = library;
    }

    public void addBook(Book book) {
        this.library.add(book);
    }

    public void deleteBook(int index) {
        this.library.remove(index - 1);
    }

    /**
     * Sort the books in the library alphabetically by title.
     */
    public void sortAlphabetically() {
        library.sort(bookComparator);
    }

    /**
     * Retrieve a list of books from the library, sorted alphabetically by title.
     *
     * @return An ArrayList containing the books in the library, sorted alphabetically.
     */
    public ArrayList<Book> list() {
        this.sortAlphabetically();
        return this.library;
    }

    //TODO use this in BorrowCommand

    /**
     * Checks if person is able to borrow a book from the library.
     *
     * @param person Person attempting to borrow a book.
     * @return if the person can borrow the book or not.
     */
    public boolean canLendTo(Person person) {
        return threshold.isLessThanOrEqualTo(person.getMeritScore());
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 1; i < this.list().size() + 1; i++) {
            result += i + ". " + this.library.get(i - 1).bookTitle.toString();
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
            Library otherLibrary = (Library) other;
            for (int i = 0; i < this.list().size(); i++) {
                if (this.library.get(i).equals(otherLibrary.library.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
