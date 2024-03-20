package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.GroupList;
import seedu.address.model.ReadOnlyGroupList;

/**
 * Represents a storage for {@link GroupList}.
 */
public interface GroupListStorage {
    /**
     * Returns the file path of the data file.
     */
    Path getGroupListFilePath();

    /**
     * Returns GroupList data as a {@link ReadOnlyGroupList}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyGroupList> readGroupList() throws DataLoadingException;

    /**
     * @see #getGroupListFilePath()
     */
    Optional<ReadOnlyGroupList> readGroupList(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyGroupList} to the storage.
     * @param groupList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveGroupList(ReadOnlyGroupList groupList) throws IOException;

    /**
     * @see #saveGroupList(ReadOnlyGroupList)
     */
    void saveGroupList(ReadOnlyGroupList groupList, Path filePath) throws IOException;
}
