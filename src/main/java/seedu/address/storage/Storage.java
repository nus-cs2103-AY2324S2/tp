package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyContactList;
import seedu.address.model.ReadOnlyGroupList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends ContactListStorage, GroupListStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataLoadingException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getContactListFilePath();

    @Override
    Optional<ReadOnlyContactList> readContactList() throws DataLoadingException;

    @Override
    void saveContactList(ReadOnlyContactList contactList) throws IOException;

    @Override
    Path getGroupListFilePath();

    @Override
    Optional<ReadOnlyGroupList> readGroupList() throws DataLoadingException;

    @Override
    void saveGroupList(ReadOnlyGroupList groupList) throws IOException;

}
