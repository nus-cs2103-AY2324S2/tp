package seedu.address.logic.attribute;

import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.attributes.AddAttributeCommand;


public class AddAttributeCommandTest {

    @Test
    public void execute_null() {
        AddAttributeCommand addAttributeCommand =
                new AddAttributeCommand(ALICE.getUuidString(), "Name", "Alice");
        assertThrows(NullPointerException.class, () -> addAttributeCommand.execute(null));
    }
}
