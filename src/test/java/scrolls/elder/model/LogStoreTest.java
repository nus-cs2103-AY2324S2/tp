package scrolls.elder.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import scrolls.elder.model.log.Log;
import scrolls.elder.testutil.Assert;
import scrolls.elder.testutil.TypicalLogs;

public class LogStoreTest {

    private LogStore logStore;

    @BeforeEach
    public void setUp() {
        logStore = new LogStore();
    }

    @Test
    public void emptyConstructor() {
        assertEquals(Collections.emptyList(), logStore.getLogList());
    }

    @Test
    public void nonEmptyConstructor_createsDeepCopy() {
        LogStore originalLogStore = TypicalLogs.getTypicalLogStore();
        LogStore copiedLogStore = new LogStore(originalLogStore);

        // Check if deep copied
        assertNotSame(originalLogStore, copiedLogStore);
        // Check if the data is the same
        assertEquals(originalLogStore, copiedLogStore);

        // Check if the data is independent
        originalLogStore.addLog(TypicalLogs.LOG_ALICE_TO_ELLE);
        assertFalse(copiedLogStore.hasLog(TypicalLogs.LOG_ALICE_TO_ELLE));
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> logStore.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyLogStore_replacesData() {
        LogStore newData = TypicalLogs.getTypicalLogStore();
        logStore.resetData(newData);
        assertEquals(newData, logStore);
    }

    @Test
    public void hasLog_nullLog_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> logStore.hasLog(null));
    }

    @Test
    public void hasLog_logNotInLogStore_returnsFalse() {
        assertFalse(logStore.hasLog(TypicalLogs.LOG_ALICE_TO_ELLE));
    }

    @Test
    public void hasLog_logInLogStore_returnsTrue() {
        logStore.addLogWithId(TypicalLogs.LOG_BENSON_TO_FIONA);
        assertTrue(logStore.hasLog(TypicalLogs.LOG_BENSON_TO_FIONA));
    }

    @Test
    public void setLog_logInLogStore_updatesLog() {
        Log originalLog = TypicalLogs.LOG_ALICE_TO_ELLE;
        logStore.addLogWithId(originalLog);

        Log editedLog = new Log(originalLog.getLogId(), TypicalLogs.LOG_BENSON_TO_FIONA);
        logStore.setLog(editedLog);

        Log logInStore = logStore.getLogById(originalLog.getLogId());
        assertEquals(editedLog, logInStore);
    }

    @Test
    public void removeLog_logInLogStore_removesLog() {
        Log logToRemove = TypicalLogs.LOG_BENSON_TO_FIONA;
        logStore.addLogWithId(logToRemove);

        logStore.removeLog(logToRemove.getLogId());

        assertFalse(logStore.hasLog(logToRemove));
    }

    @Test
    public void getLogList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, () -> logStore.getLogList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = LogStore.class.getCanonicalName() + "{logs=" + logStore.getLogList() + "}";
        assertEquals(expected, logStore.toString());
    }

    @Test
    public void equalsMethod() {
        logStore.addLog(TypicalLogs.LOG_ALICE_TO_ELLE);

        // same values -> returns true
        LogStore logStoreCopy = new LogStore(logStore);
        assertEquals(logStore, logStoreCopy);

        // same object -> returns true
        assertEquals(logStore, logStore);

        // null -> returns false
        assertNotEquals(logStore, null);

        // different types -> returns false
        assertNotEquals(logStore, new Object());

        // different person store -> returns false
        LogStore differentLogStore = new LogStore();
        differentLogStore.addLog(TypicalLogs.LOG_BENSON_TO_FIONA);
        assertNotEquals(logStore, differentLogStore);
    }

    @Test
    public void hashCodeMethod() {
        LogStore logStore1 = new LogStore();
        logStore1.addLog(TypicalLogs.LOG_ALICE_TO_ELLE);

        LogStore logStore2 = new LogStore();
        logStore2.addLog(TypicalLogs.LOG_ALICE_TO_ELLE);

        int hashCode1 = logStore1.hashCode();
        int hashCode2 = logStore1.hashCode();
        int hashCode3 = logStore2.hashCode();

        // Consistency: hashCode on same object returns same result
        assertEquals(hashCode1, hashCode2);

        // Equality: equal objects return same hashCode
        assertEquals(hashCode1, hashCode3);
    }

}
