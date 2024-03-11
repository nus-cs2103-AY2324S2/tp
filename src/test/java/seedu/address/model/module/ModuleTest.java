package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ModuleTest {

    @Test
    public void equals() {
        ModuleCode module = new ModuleCode("CS2103T");

        // same object -> returns true
        assertTrue(module.equals(module));

        // same values -> returns true
        ModuleCode remarkCopy = new ModuleCode(module.value);
        assertTrue(module.equals(remarkCopy));

        // different types -> returns false
        assertFalse(module.equals(1));

        // null -> returns false
        assertFalse(module.equals(null));

        // different remark -> returns false
        ModuleCode differentModule = new ModuleCode("CS1101S");
        assertFalse(module.equals(differentModule));
    }
}
