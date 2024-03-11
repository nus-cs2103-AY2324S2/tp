package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Name;

public class ModuleTest {

    @Test
    public void equals() {
        Module module = new Module(new Name("CS2103T"));

        // same object -> returns true
        assertTrue(module.equals(module));

        // same values -> returns true
        Module remarkCopy = new Module(module.name);
        assertTrue(module.equals(remarkCopy));

        // different types -> returns false
        assertFalse(module.equals(1));

        // null -> returns false
        assertFalse(module.equals(null));

        // different remark -> returns false
        Module differentModule = new Module(new Name("CS1101S"));
        assertFalse(module.equals(differentModule));
    }
}
