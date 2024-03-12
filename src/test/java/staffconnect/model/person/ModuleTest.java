package staffconnect.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static staffconnect.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ModuleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Module(null));
    }

    @Test
    public void constructor_invalidModule_throwsIllegalArgumentException() {
        String invalidModule = "";
        assertThrows(IllegalArgumentException.class, () -> new Module(invalidModule));
    }

    @Test
    public void isValidModule() {
        // null phone number
        assertThrows(NullPointerException.class, () -> Module.isValidModule(null));

        // invalid phone numbers
        assertFalse(Module.isValidModule("")); // empty string
        assertFalse(Module.isValidModule(" ")); // spaces only
        assertFalse(Module.isValidModule("ABCD")); // letters only
        assertFalse(Module.isValidModule("1234")); // numbers only
        assertFalse(Module.isValidModule("2103T")); // missing prefix
        assertFalse(Module.isValidModule("C2103T")); // only 1 prefix
        assertFalse(Module.isValidModule("CS2103TTTT")); // too many suffix
        assertFalse(Module.isValidModule("CSCSCS2103T")); // too many prefix
        assertFalse(Module.isValidModule("CS210310101010T")); // too many numbers
        assertFalse(Module.isValidModule("CS21T")); // too little numbers
        assertFalse(Module.isValidModule("cs2103t")); // suffix not capitalised
        assertFalse(Module.isValidModule("cs2103T")); // prefix not capitalised

        // valid phone numbers
        assertTrue(Module.isValidModule("CS2103")); // 2 prefix, 4 letters without 1 optional suffix
        assertTrue(Module.isValidModule("CS2103T")); // 2 prefix, 4 letters with 1 optional suffix
        assertTrue(Module.isValidModule("GEN2050")); // 3 prefix, 4 letters without  1 optional suffix
        assertTrue(Module.isValidModule("GEN2050Y")); // 3 prefix, 4 letters with  1 optional suffix
        assertTrue(Module.isValidModule("GESS1035")); // 4 prefix, 4 letters without 1 optional suffix
        assertTrue(Module.isValidModule("GESS1035X")); // 4 prefix, 4 letters with  1 optional suffix
    }

    @Test
    public void equals() {
        Module module = new Module("CS2103");

        // same values -> returns true
        assertTrue(module.equals(new Module("CS2103")));

        // same object -> returns true
        assertTrue(module.equals(module));

        // null -> returns false
        assertFalse(module.equals(null));

        // different values -> returns false
        assertFalse(module.equals(new Module("MS2345")));
    }
}
