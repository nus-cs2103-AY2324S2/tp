package seedu.address.model.book;

import java.util.ArrayList;
import java.util.Comparator;

import seedu.address.model.BookCollection;

public class Library extends BookCollection {
    public static Comparator<Book> bookComparator = new Comparator<Book>() {
        @Override
        public int compare(Book book1, Book book2) {
            return book1.bookTitle.compareTo(book2.bookTitle);
        }
    };

    public Library() {
        super();
    }

    public Library(ArrayList<Book> bookCollection) {
        super(bookCollection);
    }

    public void sortAlphabetically() {
        bookCollection.sort(bookComparator);
    }

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
        System.out.println("hello");
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
