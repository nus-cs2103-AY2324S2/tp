package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

public class VersionedAddressBookTest {

    private final VersionedAddressBook addressBook = new VersionedAddressBook(getTypicalAddressBook());

    @Test
    public void canUndoMethod() {
        assertEquals(false, addressBook.canUndo());
        addressBook.commit();
        assertEquals(true, addressBook.canUndo());
    }

    @Test
    public void canRedoMethod() {
        assertEquals(false, addressBook.canRedo());
        addressBook.commit();
        addressBook.undo();
        assertEquals(true, addressBook.canRedo());
    }

    @Test
    public void toStringMethod() {
        String expected = VersionedAddressBook.class.getCanonicalName()
                + "{persons=" + addressBook.getPersonList() + "}";
        assertEquals(expected, addressBook.toString());
    }

    @Test
    public void equals() {
        VersionedAddressBook b1 = new VersionedAddressBook(getTypicalAddressBook());
        VersionedAddressBook b2 = new VersionedAddressBook(getTypicalAddressBook());

        // same object -> returns true
        assertTrue(b1.equals(b2));

        // different types -> returns false
        assertFalse(b1.equals(1));

        // null -> returns false
        assertFalse(b1.equals(null));

        b1.commit();
        // different history and different pointer -> return false
        assertFalse(b1.equals(b2));

        b1.undo();
        // same history and different pointer -> return false
        assertFalse(b1.equals(b2));

        b2.commit();
        b2.undo();
        // same history and same pointer -> return true
        assertTrue(b1.equals(b2));
    }

}
