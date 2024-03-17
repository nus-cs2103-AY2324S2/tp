package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyInsuraConnectBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends InsuraConnectBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataLoadingException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyInsuraConnectBook> readAddressBook() throws DataLoadingException;

    @Override
    void saveAddressBook(ReadOnlyInsuraConnectBook addressBook) throws IOException;

}
