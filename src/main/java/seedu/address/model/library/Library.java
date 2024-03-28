package seedu.address.model.library;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Comparator;

import seedu.address.model.ReadOnlyLibrary;
import seedu.address.model.book.Book;
import seedu.address.model.person.Person;

/**
 * Represents a collection of books in a library.
 */
public class Library implements ReadOnlyLibrary {

    /**
     * Comparator for comparing books alphabetically by title.
     */
    private static Comparator<Book> bookComparator = new Comparator<Book>() {
        @Override
        public int compare(Book book1, Book book2) {
            return book1.bookTitle.compareTo(book2.bookTitle);
        }
    };

    private ArrayList<Book> bookList;
    private Threshold threshold;
    private LibraryLogic libraryLogic;

    /**
     * Construct an empty library.
     */
    public Library() {
        bookList = new ArrayList<>();
        threshold = new Threshold();
        libraryLogic = new LibraryLogic();
    }

    /**
     * Construct a library with the specified list of books and threshold.
     *
     * @param toBeCopied The read only library from data.
     */
    public Library(ReadOnlyLibrary toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Construct an empty library with preset threshold.
     */
    public Library(int i) {
        bookList = new ArrayList<>();
        threshold = new Threshold(i);
    }

    /**
     * Construct a library with the specified list of books.
     *
     * @param bookList The list of books to initialize the library with.
     */
    public Library(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }

    /**
     * Construct a library with the specified list of books and threshold.
     *
     * @param bookList The list of books to initialize the library with.
     * @param threshold The threshold limit
     */
    public Library(ArrayList<Book> bookList, Threshold threshold) {
        this.bookList = bookList;
        this.threshold = threshold;
    }

    public void addBook(Book book) {
        this.bookList.add(book);
    }

    public void deleteBook(int index) {
        this.bookList.remove(index - 1);
    }

    /**
     * Sort the books in the library alphabetically by title.
     */
    public void sortAlphabetically() {
        bookList.sort(bookComparator);
    }

    /**
     * Retrieve a list of books from the library, sorted alphabetically by title.
     *
     * @return An ArrayList containing the books in the library, sorted alphabetically.
     */
    public ArrayList<Book> list() {
        this.sortAlphabetically();
        return this.bookList;
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
    public ArrayList<Book> getBookList() {
        return bookList;
    }

    public void setBookList(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }

    public void setThreshold(Threshold threshold) {
        this.threshold = threshold;
    }

    @Override
    public Threshold getThreshold() {
        return threshold;
    }

    public boolean hasThreshold(Threshold threshold) {
        return this.threshold.equals(threshold);
    }

    public boolean hasBookInLibrary(Book book) {
        return bookList.contains(book);
    }

    public Book popBookFromLibrary(Book book) {
        if (hasBookInLibrary(book)) {
            int i = bookList.indexOf(book);
            return bookList.remove(i);
        }
        return null;
    }

    /**
     * Resets the existing data of this {@code Library} with {@code newData}.
     */
    public void resetData(ReadOnlyLibrary newData) {
        requireNonNull(newData);

        setThreshold(newData.getThreshold());
        setBookList(newData.getBookList());
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 1; i < this.list().size() + 1; i++) {
            result += i + ". " + this.bookList.get(i - 1).bookTitle.toString();
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
                if (this.bookList.get(i).equals(otherLibrary.bookList.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
