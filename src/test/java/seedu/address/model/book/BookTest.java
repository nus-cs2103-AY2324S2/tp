package seedu.address.model.book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class BookTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Book(null));
    }

    @Test
    public void constructor_invalidBookTitle_throwsIllegalArgumentException() {
        String invalidBookTitle = "   ";
        assertThrows(IllegalArgumentException.class, () -> new Book(invalidBookTitle));
    }

    @Test
    public void isValidBookTitle() {
        // null book title
        assertThrows(NullPointerException.class, () -> Book.isValidBookTitle(null));

        // starts with white space
        assertFalse(() -> Book.isValidBookTitle(" "));
        assertFalse(() -> Book.isValidBookTitle(" Harry Potter"));

        // valid titles
        assertTrue(() -> Book.isValidBookTitle(""));
        assertTrue(() -> Book.isValidBookTitle("HarryPotter"));
        assertTrue(() -> Book.isValidBookTitle("Harry Potter"));

    }

    @Test
    public void equalsTest() {
        Book book = new Book("Valid Book");

        // same values -> returns true
        assertTrue(book.equals(new Book("Valid Book")));

        // same object -> returns true
        assertTrue(book.equals(book));

        // null -> returns false
        assertFalse(book.equals(null));

        // different types -> returns false
        assertFalse(book.equals(5.0f));

        // different values -> returns false
        assertFalse(book.equals(new Book("Other Valid Book")));
    }

    @Test
    public void toStringTest() {
        Book book = new Book("Valid Book123!!!");
        assertEquals("Valid Book123!!!", book.toString());
    }
}
