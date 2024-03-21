package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.storage.StateStorage.clearState;
import static seedu.address.storage.StateStorage.deleteStateStorage;
import static seedu.address.storage.StateStorage.getLastCommand;
import static seedu.address.storage.StateStorage.loadState;
import static seedu.address.storage.StateStorage.writeState;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Paths;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.DataLoadingException;

public class StateStorageTest {

    private StateStorage stateStorage;

    @BeforeEach
    public void setUp() {
        this.stateStorage = new StateStorage();
    }
    @AfterAll
    public static void tearDown() {
        clearState();
    }

    @Test
    public void createStorage_successfullyCreated_throwsNullPointerException() throws NullPointerException {
        assertNotNull(stateStorage);
    }


    @Test
    public void getFilePath_successfullyReturned() {
        assertNotNull(StateStorage.getFilePath());
        assertEquals(Paths.get("./data/state.txt"), StateStorage.getFilePath());

    }

    @Test
    public void getFilePathString_successfullyReturned() {
        assertEquals("./data/state.txt", StateStorage.getFilePathString());
    }

    @Test
    public void getDirectoryPath_successfullyReturned() {
        assertEquals(Paths.get("./data"), StateStorage.getDirectoryPath());
    }


    @Test
    public void loadState_emptyFile_successfullyLoaded() throws DataLoadingException {
        clearState();
        String expected = "";
        String actual = loadState();
        assertEquals(expected, actual);
    }

    @Test
    public void getLastCommand_emptyFile_successfullyLoaded() throws DataLoadingException {
        clearState();
        String expected = "";
        String actual = getLastCommand();
        assertEquals(expected, actual);
    }

    @Test
    public void writeState_successfullyWritten() throws DataLoadingException {
        String expected = "test123abc cba321tset";
        writeState(expected);
        String actual = loadState();
        assertEquals(expected, actual);
    }

    @Test
    public void overWriteState_successfullyOverWritten() throws DataLoadingException {
        String expected = "test123abc cba321tset";
        writeState(expected);
        String actual = loadState();
        assertEquals(expected, actual);

        String overWriteString = "newTest";
        writeState(overWriteString);
        String actualOverWrittenString = loadState();
        assertEquals(overWriteString, actualOverWrittenString);
    }

    @Test
    public void loadFromFile_fileDoesNotExist_handlesDataLoadingException() {
        deleteStateStorage();
        assertThrows(DataLoadingException.class, () -> loadState());
    }

    @Test
    public void isStateStorageExists_fileDoesNotExist_returnsFalse() {
        deleteStateStorage();
        assertEquals(false, StateStorage.isStateStorageExists());
    }
}
