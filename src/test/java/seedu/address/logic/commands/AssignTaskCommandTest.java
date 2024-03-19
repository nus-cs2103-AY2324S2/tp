package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;

import static seedu.address.testutil.Assert.assertThrows;

public class AssignTaskCommandTest {
    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AssignTaskCommand(null, null));
    }
}
