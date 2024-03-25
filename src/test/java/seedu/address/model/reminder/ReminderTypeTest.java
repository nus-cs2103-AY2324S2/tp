package seedu.address.model.reminder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ReminderTypeTest {
    @Test
    public void toStringMethod() {
        assertEquals("Last Met", ReminderType.LAST_MET.toString());
        assertEquals("Schedules", ReminderType.SCHEDULES.toString());
    }
}
