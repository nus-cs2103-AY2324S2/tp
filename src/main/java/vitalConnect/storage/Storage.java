package vitalConnect.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import vitalConnect.commons.exceptions.DataLoadingException;
import vitalConnect.model.ReadOnlyClinic;
import vitalConnect.model.ReadOnlyUserPrefs;
import vitalConnect.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends ClinicStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataLoadingException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getClinicFilePath();

    @Override
    Optional<ReadOnlyClinic> readClinic() throws DataLoadingException;

    @Override
    void saveClinic(ReadOnlyClinic clinic) throws IOException;

}
