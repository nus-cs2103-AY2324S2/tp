package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY_STAFF;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB_STAFF;
import static seedu.address.testutil.TypicalPersons.ALICESTAFF;
import static seedu.address.testutil.TypicalPersons.BENSONSTAFF;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditStaffCommand.EditStaffDescriptor;
import seedu.address.model.person.Name;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditStaffCommand.
 */
public class EditStaffCommandTest {
    @Test
    public void equals() {
        final EditStaffCommand standardCommand = new EditStaffCommand(ALICESTAFF.getName(), DESC_AMY_STAFF);

        // same values -> returns true
        EditStaffDescriptor copyDescriptor = new EditStaffDescriptor(DESC_AMY_STAFF);
        EditStaffCommand commandWithSameValues = new EditStaffCommand(ALICESTAFF.getName(), copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditStaffCommand(BENSONSTAFF.getName(), DESC_AMY_STAFF)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditStaffCommand(ALICESTAFF.getName(), DESC_BOB_STAFF)));
    }

    @Test
    public void toStringMethod() {
        Name name = ALICESTAFF.getName();
        EditStaffDescriptor editStaffDescriptor = new EditStaffDescriptor();
        EditStaffCommand editStaffCommand = new EditStaffCommand(ALICESTAFF.getName(), editStaffDescriptor);
        String expected = EditStaffCommand.class.getCanonicalName() + "{name=" + name + ", editStaffDescriptor="
                + editStaffDescriptor + "}";
        assertEquals(expected, editStaffCommand.toString());
    }

}
