package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class LastMetTest {
    @Test
    public void constructor_validLastMet_noExceptionThrown() {
        assertDoesNotThrow(() -> new LastMet(LocalDate.now().minusDays(7)));
    }

    @Test
    public void constructor_nullLastMet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LastMet(null));
    }

    @Test
    public void getIsOverdue_isOverdueTrue_returnsCorrectIsOverdue() {
        LastMet.setLastMetDuration(90);
        LocalDate lastMetDate = LocalDate.now().minusDays(100);
        LastMet lastMet = new LastMet(lastMetDate);
        boolean getIsOverdue = lastMet.getIsOverdue();
        assertEquals(true, getIsOverdue);
    }

    @Test
    public void getIsOverdue_isOverdueFalse_returnsCorrectIsOverdue() {
        LastMet.setLastMetDuration(90);
        LocalDate lastMetDate = LocalDate.now().minusDays(50);
        LastMet lastMet = new LastMet(lastMetDate);
        boolean getIsOverdue = lastMet.getIsOverdue();
        assertEquals(false, getIsOverdue);
    }

    @Test
    public void getPeriodGap_validLastMet_returnsCorrectGap() {
        LocalDate lastMetDate = LocalDate.now().minusDays(10);
        LastMet lastMet = new LastMet(lastMetDate);

        long periodGap = lastMet.getPeriodGap();

        assertEquals(10, periodGap);
    }

    @Test
    public void setIsOverdue_overdueLastMet_returnsTrue() {
        LastMet.setLastMetDuration(90);
        LocalDate lastMetDate = LocalDate.now().minusDays(100);
        LastMet lastMet = new LastMet(lastMetDate);

        assertTrue(lastMet.getIsOverdue());
    }

    @Test
    public void setIsOverdue_recentLastMet_returnsFalse() {
        LastMet.setLastMetDuration(90);
        LocalDate lastMetDate = LocalDate.now().minusDays(60);
        LastMet lastMet = new LastMet(lastMetDate);

        assertFalse(lastMet.getIsOverdue());
    }

    @Test
    public void showLastMet_validLastMet_returnsFormattedString() {
        LocalDate lastMetDate = LocalDate.now().minusDays(15);
        LastMet lastMet = new LastMet(lastMetDate);

        String expected = "Last Met: " + lastMetDate + ", 15 Days Ago.";
        assertEquals(expected, lastMet.showLastMet());
    }

    @Test
    public void setLastMetDuration_validDuration_updatesLastMetDuration() {
        long newDuration = 120;
        LastMet.setLastMetDuration(newDuration);

        assertEquals(newDuration, LastMet.getLastMetDuration());
    }
}
