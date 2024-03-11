package staffconnect.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import staffconnect.commons.exceptions.DataLoadingException;
import staffconnect.model.ReadOnlyStaffBook;
import staffconnect.model.ReadOnlyUserPrefs;
import staffconnect.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends StaffBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataLoadingException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getStaffBookFilePath();

    @Override
    Optional<ReadOnlyStaffBook> readStaffBook() throws DataLoadingException;

    @Override
    void saveStaffBook(ReadOnlyStaffBook staffBook) throws IOException;

}
