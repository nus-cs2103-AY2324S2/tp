package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        // invalid name
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(Name.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Name.isValidName("peter jack")); // alphabets only
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(Name.isValidName("Capital Tan")); // with capital letters
        assertTrue(Name.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }

    @Test
    public void compareTo() {
        Name fname = new Name("Alice");
        Name sname = new Name("Bob");
        Name tname = new Name("Charlie");


        // same values -> returns 0
        assertTrue(fname.compareTo(fname) == 0);
        assertTrue(sname.compareTo(sname) == 0);
        assertTrue(tname.compareTo(tname) == 0);
        assertFalse(fname.compareTo(sname) == 0);
        assertFalse(sname.compareTo(tname) == 0);
        assertFalse(tname.compareTo(fname) == 0);

        // Name object where method is invoked upon with lower lexicographical ordering
        // than the Name object passed in as argument -> returns negative value
        assertTrue(fname.compareTo(sname) < 0);
        assertTrue(sname.compareTo(tname) < 0);
        assertTrue(fname.compareTo(tname) < 0);
        assertFalse(sname.compareTo(fname) < 0);
        assertFalse(tname.compareTo(sname) < 0);
        assertFalse(tname.compareTo(fname) < 0);

        // Name object where method is invoked upon with higher lexicographical ordering
        // than the Name object passed in as argument -> returns positive value
        assertTrue(sname.compareTo(fname) > 0);
        assertTrue(tname.compareTo(sname) > 0);
        assertTrue(tname.compareTo(fname) > 0);
        assertFalse(fname.compareTo(sname) > 0);
        assertFalse(sname.compareTo(tname) > 0);
        assertFalse(fname.compareTo(tname) > 0);

    }

    @Test
    public void equals() {
        Name name = new Name("Valid Name");

        // same values -> returns true
        assertTrue(name.equals(new Name("Valid Name")));

        // same object -> returns true
        assertTrue(name.equals(name));

        // null -> returns false
        assertFalse(name.equals(null));

        // different types -> returns false
        assertFalse(name.equals(5.0f));

        // different values -> returns false
        assertFalse(name.equals(new Name("Other Valid Name")));
    }
}
