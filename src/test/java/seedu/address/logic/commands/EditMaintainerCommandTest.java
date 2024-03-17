package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY_MAINTAINER;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB_MAINTAINER;
import static seedu.address.testutil.TypicalPersons.ALICEMAINTAINER;
import static seedu.address.testutil.TypicalPersons.BENSONMAINTAINER;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditMaintainerCommand.EditMaintainerDescriptor;
import seedu.address.model.person.Name;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditMaintainerCommand.
 */
public class EditMaintainerCommandTest {
    @Test
    public void equals() {
        final EditMaintainerCommand standardCommand = new EditMaintainerCommand(ALICEMAINTAINER.getName(), DESC_AMY_MAINTAINER);

        // same values -> returns true
        EditMaintainerDescriptor copyDescriptor = new EditMaintainerDescriptor(DESC_AMY_MAINTAINER);
        EditMaintainerCommand commandWithSameValues = new EditMaintainerCommand(ALICEMAINTAINER.getName(), copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditMaintainerCommand(BENSONMAINTAINER.getName(), DESC_AMY_MAINTAINER)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditMaintainerCommand(ALICEMAINTAINER.getName(), DESC_BOB_MAINTAINER)));
    }

    @Test
    public void toStringMethod() {
        Name name = ALICEMAINTAINER.getName();
        EditMaintainerDescriptor editMaintainerDescriptor = new EditMaintainerDescriptor();
        EditMaintainerCommand editMaintainerCommand = new EditMaintainerCommand(ALICEMAINTAINER.getName(), editMaintainerDescriptor);
        String expected = EditMaintainerCommand.class.getCanonicalName() + "{name=" + name + ", editMaintainerDescriptor="
                + editMaintainerDescriptor + "}";
        assertEquals(expected, editMaintainerCommand.toString());
    }

}
