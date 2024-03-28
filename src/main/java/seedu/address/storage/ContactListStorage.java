package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ContactList;
import seedu.address.model.ReadOnlyContactList;

/**
 * Represents a storage for {@link ContactList}.
 */
public interface ContactListStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getContactListFilePath();

    /**
     * Returns ContactList data as a {@link ReadOnlyContactList}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyContactList> readContactList() throws DataLoadingException;

    /**
     * @see #getContactListFilePath()
     */
    Optional<ReadOnlyContactList> readContactList(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyContactList} to the storage.
     * @param contactList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveContactList(ReadOnlyContactList contactList) throws IOException;

    /**
     * @see #saveContactList(ReadOnlyContactList)
     */
    void saveContactList(ReadOnlyContactList contactList, Path filePath) throws IOException;

}
