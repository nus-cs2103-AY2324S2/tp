package vitalconnect.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import vitalconnect.commons.exceptions.DataLoadingException;
import vitalconnect.model.ReadOnlyClinic;
import vitalconnect.model.ReadOnlyUserPrefs;
import vitalconnect.model.UserPrefs;

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
