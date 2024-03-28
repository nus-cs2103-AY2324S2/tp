package scrolls.elder.model.log;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import scrolls.elder.testutil.TypicalLogs;

public class LogTest {

    @Test
    public void equalsMethod() {
        Log log1 = new Log(0, TypicalLogs.LOG_ALICE_TO_ELLE);
        Log log2 = new Log(0, TypicalLogs.LOG_ALICE_TO_ELLE);

        // same values -> returns true
        assertEquals(log1, log2);

        // same object -> returns true
        assertEquals(log1, log1);

        // null -> returns false
        assertNotEquals(log1, null);

        // different types -> returns false
        assertNotEquals(log1, new Object());

        // different log -> returns false
        Log differentLog = new Log(1, TypicalLogs.LOG_BENSON_TO_FIONA);
        assertNotEquals(log1, differentLog);
    }

    @Test
    public void hashCodeMethod() {
        Log log1 = new Log(0, TypicalLogs.LOG_ALICE_TO_ELLE);
        Log log2 = new Log(0, TypicalLogs.LOG_ALICE_TO_ELLE);

        int hashCode1 = log1.hashCode();
        int hashCode2 = log1.hashCode();
        int hashCode3 = log2.hashCode();

        // Consistency: hashCode on same object returns same result
        assertEquals(hashCode1, hashCode2);

        // Equality: equal objects return same hashCode
        assertEquals(hashCode1, hashCode3);
    }
}
