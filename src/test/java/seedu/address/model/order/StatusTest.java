package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class StatusTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Status(null));
    }

    @Test
    public void constructor_invalidStatus_throwsIllegalArgumentException() {
        String invalidStatus = "invalid";
        assertThrows(IllegalArgumentException.class, () -> new Status(invalidStatus));
    }

    @Test
    public void isValidStatus() {
        // null status
        assertThrows(NullPointerException.class, () -> Status.isValidStatus(null));

        // invalid statuses
        assertFalse(Status.isValidStatus("invalid")); // Not matching any status enum

        // valid statuses
        assertTrue(Status.isValidStatus("pending"));
        assertTrue(Status.isValidStatus("completed"));
        assertTrue(Status.isValidStatus("canceled"));
        assertTrue(Status.isValidStatus("PENDING")); // Case insensitivity
        assertTrue(Status.isValidStatus("COMPLETED")); // Case insensitivity
        assertTrue(Status.isValidStatus("CANCELED")); // Case insensitivity
    }

    @Test
    public void equals() {
        Status status = new Status("pending");

        // same values -> returns true
        assertEquals(status, new Status("pending"));

        // same object -> returns true
        assertEquals(status, status);

        // null -> returns false
        assertNotEquals(null, status);

        // different types -> returns false
        assertNotEquals(status, 5.0);

        // different values -> returns false
        assertNotEquals(status, new Status("completed"));
    }

}
