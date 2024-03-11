package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ApplicationStatusTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ApplicationStatus(null));
    }

    @Test
    public void constructor_invalidApplicationStatus_throwsIllegalArgumentException() {
        String invalidApplicationStatus = "";
        assertThrows(IllegalArgumentException.class, () -> new ApplicationStatus(invalidApplicationStatus));
    }

    @Test
    public void isValidApplicationStatus() {
        // null applicationStatuses
        assertThrows(NullPointerException.class, () -> ApplicationStatus.isValidApplicationStatus(null));

        // invalid applicationStatuses
        assertFalse(ApplicationStatus.isValidApplicationStatus(""));
        assertFalse(ApplicationStatus.isValidApplicationStatus(" "));

        // valid applicationStatuses
        assertTrue(ApplicationStatus.isValidApplicationStatus("to_apply"));
        assertTrue(ApplicationStatus.isValidApplicationStatus("pending"));
        assertTrue(ApplicationStatus.isValidApplicationStatus("rejected"));
        assertTrue(ApplicationStatus.isValidApplicationStatus("accepted"));
        assertTrue(ApplicationStatus.isValidApplicationStatus("ongoing"));
        assertTrue(ApplicationStatus.isValidApplicationStatus("TO_APPLY"));
        assertTrue(ApplicationStatus.isValidApplicationStatus("PENDING"));
        assertTrue(ApplicationStatus.isValidApplicationStatus("REJECTED"));
        assertTrue(ApplicationStatus.isValidApplicationStatus("ACCEPTED"));
        assertTrue(ApplicationStatus.isValidApplicationStatus("ONGOING"));
        ;
    }

    @Test
    public void equals() {
        ApplicationStatus applicationStatus = new ApplicationStatus("to_apply");

        // same values -> returns true
        assertTrue(applicationStatus.equals(new ApplicationStatus("to_apply")));

        // same object -> returns true
        assertTrue(applicationStatus.equals(applicationStatus));

        // null -> returns false
        assertFalse(applicationStatus.equals(null));

        // different types -> returns false
        assertFalse(applicationStatus.equals(5.0f));

        // different values -> returns false
        assertFalse(applicationStatus.equals(new ApplicationStatus("pending")));
    }

}
