package seedu.realodex.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.realodex.commons.exceptions.DataLoadingException;
import seedu.realodex.model.ReadOnlyRealodex;
import seedu.realodex.model.ReadOnlyUserPrefs;
import seedu.realodex.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends RealodexStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataLoadingException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getRealodexFilePath();

    @Override
    Optional<ReadOnlyRealodex> readRealodex() throws DataLoadingException;

    @Override
    void saveRealodex(ReadOnlyRealodex realodex) throws IOException;

}
