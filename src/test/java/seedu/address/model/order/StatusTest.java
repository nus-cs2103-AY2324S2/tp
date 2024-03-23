package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StatusTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Status.valueOf(null));
    }

    @Test
    public void constructor_invalidStatus_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Status.valueOf("Invalid"));
    }

    @Test
    public void isValidStatus() {
        // invalid status
        assertFalse(Status.isValidStatus("Invalid"));
        assertFalse(Status.isValidStatus("Arrived"));
        assertFalse(Status.isValidStatus("Late"));
        assertFalse(Status.isValidStatus("Pending"));

        // valid status
        assertTrue(Status.isValidStatus("Pending"));
        assertTrue(Status.isValidStatus("Arrived"));
        assertTrue(Status.isValidStatus("Late"));
    }

    @Test
    public void equals() {
        assertFalse(Status.PENDING.equals(null));
        assertFalse(Status.PENDING.toString().equals("Arrived"));
        assertFalse(Status.ARRIVED.toString().equals("Late"));
        assertFalse(Status.LATE.toString().equals("Pending"));

        assertTrue(Status.PENDING.toString().equals("Pending"));
        assertTrue(Status.ARRIVED.toString().equals("Arrived"));
        assertTrue(Status.LATE.toString().equals("Late"));
    }
}
