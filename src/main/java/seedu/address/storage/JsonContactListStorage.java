package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyContactList;

/**
 * A class to access ContactList data stored as a json file on the hard disk.
 */
public class JsonContactListStorage implements ContactListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonContactListStorage.class);

    private Path filePath;

    public JsonContactListStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getContactListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyContactList> readContactList() throws DataLoadingException {
        return readContactList(filePath);
    }

    /**
     * Similar to {@link #readContactList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyContactList> readContactList(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableContactList> jsonContactList = JsonUtil.readJsonFile(
                filePath, JsonSerializableContactList.class);
        if (!jsonContactList.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonContactList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveContactList(ReadOnlyContactList contactList) throws IOException {
        saveContactList(contactList, filePath);
    }

    /**
     * Similar to {@link #saveContactList(ReadOnlyContactList)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveContactList(ReadOnlyContactList contactList, Path filePath) throws IOException {
        requireNonNull(contactList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableContactList(contactList), filePath);
    }

}
