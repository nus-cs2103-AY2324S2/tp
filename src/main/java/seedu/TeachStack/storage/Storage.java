package seedu.TeachStack.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.TeachStack.commons.exceptions.DataLoadingException;
import seedu.TeachStack.model.ReadOnlyAddressBook;
import seedu.TeachStack.model.ReadOnlyUserPrefs;
import seedu.TeachStack.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataLoadingException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyAddressBook> readAddressBook() throws DataLoadingException;

    @Override
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;

}
