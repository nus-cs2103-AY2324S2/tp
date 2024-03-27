package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StatusTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Status(null));
    }

    @Test
    public void constructor_invalidStatus_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Status("invalid"));
    }

    @Test
    public void isValidStatus() {
        // invalid status
        assertFalse(Status.isValidStatus("test"));
        assertFalse(Status.isValidStatus(""));

        // valid status
        assertTrue(Status.isValidStatus("Pending"));
        assertTrue(Status.isValidStatus("Arrived"));
        assertTrue(Status.isValidStatus("Late"));
    }

    @Test
    public void equals() {
        assertFalse(Status.StatusEnum.PENDING.equals(null));
        assertFalse(Status.StatusEnum.PENDING.toString().equals("Arrived"));
        assertFalse(Status.StatusEnum.ARRIVED.toString().equals("Late"));
        assertFalse(Status.StatusEnum.LATE.toString().equals("Pending"));

        assertTrue(Status.StatusEnum.PENDING.toString().equals("Pending"));
        assertTrue(Status.StatusEnum.ARRIVED.toString().equals("Arrived"));
        assertTrue(Status.StatusEnum.LATE.toString().equals("Late"));
    }
}
