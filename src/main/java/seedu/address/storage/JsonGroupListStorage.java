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
import seedu.address.model.ReadOnlyGroupList;

/**
 * A class to access GroupList data stored as a json file on the hard disk.
 */
public class JsonGroupListStorage implements GroupListStorage {
    private static final Logger logger = LogsCenter.getLogger(JsonGroupListStorage.class);

    private Path filePath;

    public JsonGroupListStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getGroupListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyGroupList> readGroupList() throws DataLoadingException {
        return readGroupList(filePath);
    }

    @Override
    public Optional<ReadOnlyGroupList> readGroupList(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableGroupList> jsonGroupList = JsonUtil.readJsonFile(
                filePath, JsonSerializableGroupList.class);
        if (jsonGroupList.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonGroupList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveGroupList(ReadOnlyGroupList groupList) throws IOException {
        saveGroupList(groupList, filePath);
    }

    @Override
    public void saveGroupList(ReadOnlyGroupList groupList, Path filePath) throws IOException {
        requireNonNull(groupList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableGroupList(groupList), filePath);
    }

}
