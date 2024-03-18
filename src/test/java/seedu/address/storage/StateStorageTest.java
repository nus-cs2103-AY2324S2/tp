package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StateStorageTest {

    private StateStorage stateStorage;

    @BeforeEach
    public void setUp() {
        this.stateStorage = new StateStorage();
    }
    @AfterAll
    public static void tearDown() {
        StateStorage.clearState();
    }

    @Test
    public void createStorage_successfullyCreated_throwsNullPointerException() throws NullPointerException {
        assertNotNull(stateStorage);
    }

    @Test
    public void loadState_emptyFile_successfullyLoaded() {
        StateStorage.clearState();
        String expected = "";
        String actual = stateStorage.loadState();
        assertEquals(expected, actual);
    }

    @Test
    public void writeState_successfullyWritten() {
        String expected = "test123abc cba321tset";
        stateStorage.writeState(expected);
        String actual = stateStorage.loadState();
        assertEquals(expected, actual);
    }

    @Test
    public void overWriteState_successfullyOverWritten() {
        String expected = "test123abc cba321tset";
        stateStorage.writeState(expected);
        String actual = stateStorage.loadState();
        assertEquals(expected, actual);

        String overWriteString = "newTest";
        stateStorage.writeState(overWriteString);
        String actualOverWrittenString = stateStorage.loadState();
        assertEquals(overWriteString, actualOverWrittenString);
    }
}
