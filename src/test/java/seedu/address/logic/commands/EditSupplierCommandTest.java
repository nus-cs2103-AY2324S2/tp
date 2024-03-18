package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY_SUPPLIER;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB_SUPPLIER;
import static seedu.address.testutil.TypicalPersons.ALICESUPPLIER;
import static seedu.address.testutil.TypicalPersons.BENSONSUPPLIER;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditSupplierCommand.EditSupplierDescriptor;
import seedu.address.model.person.Name;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditSupplierCommand.
 */
public class EditSupplierCommandTest {
    @Test
    public void equals() {
        final EditSupplierCommand standardCommand = new EditSupplierCommand(ALICESUPPLIER.getName(), DESC_AMY_SUPPLIER);

        // same values -> returns true
        EditSupplierDescriptor copyDescriptor = new EditSupplierDescriptor(DESC_AMY_SUPPLIER);
        EditSupplierCommand commandWithSameValues = new EditSupplierCommand(ALICESUPPLIER.getName(), copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditSupplierCommand(BENSONSUPPLIER.getName(), DESC_AMY_SUPPLIER)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditSupplierCommand(ALICESUPPLIER.getName(), DESC_BOB_SUPPLIER)));
    }

    @Test
    public void toStringMethod() {
        Name name = ALICESUPPLIER.getName();
        EditSupplierDescriptor editSupplierDescriptor = new EditSupplierDescriptor();
        EditSupplierCommand editSupplierCommand =
                new EditSupplierCommand(ALICESUPPLIER.getName(), editSupplierDescriptor);
        String expected = EditSupplierCommand.class.getCanonicalName() + "{name=" + name + ", editSupplierDescriptor="
                + editSupplierDescriptor + "}";
        assertEquals(expected, editSupplierCommand.toString());
    }

}
