package seedu.address.model.person.fields;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AddressTest {

    private static final String WHITESPACE = " \t\r\n";
    private static final String INVALID_ADDRESS = " ";
    private static final String VALID_ADDRESS = "123 Main Street #0505";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Address(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Address("")); // empty string
        assertThrows(IllegalArgumentException.class, () -> new Address(" ")); // spaces only
    }

    @Test
    public void constructor_validAddress_success() {
        // valid addresses
        assertDoesNotThrow(() -> new Address("Blk 456, Den Road, #01-355"));
        assertDoesNotThrow(() -> new Address("-")); // one character
        assertDoesNotThrow(() ->
                           new Address("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
    }

    @Test
    public void of_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Address.of((String) null));
    }

    @Test
    public void of_invalidValue_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Address.of(INVALID_ADDRESS));
    }

    @Test
    public void of_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, Address.of(VALID_ADDRESS));
    }

    @Test
    public void of_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, Address.of(addressWithWhitespace));
    }

    @Test
    public void equals() {
        Address address = new Address("Valid Address");

        // same values -> returns true
        Address address2 = new Address("Valid Address");
        assertTrue(address.equals(address2));
        assertEquals(address.hashCode(), address2.hashCode());

        // same object -> returns true
        assertTrue(address.equals(address));
        assertEquals(address.hashCode(), address.hashCode());

        // null -> returns false
        assertFalse(address.equals(null));

        // different types -> returns false
        assertFalse(address.equals(5.0f));

        // different values -> returns false
        Address otherAddress = new Address("Other Valid Address");
        assertFalse(address.equals(otherAddress));
    }

}
