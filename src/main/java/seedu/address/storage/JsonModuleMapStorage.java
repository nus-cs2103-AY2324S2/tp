package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.module.ReadOnlyModuleMap;

public class JsonModuleMapStorage implements ModuleMapStorage {

    private final Path filePath;

    public JsonModuleMapStorage(Path moduleMapFilePath) throws IOException {
        this.filePath = moduleMapFilePath;
    }

    @Override
    public Path getModuleFilePath() {
        return null;
    }

    @Override
    public Optional<ReadOnlyModuleMap> readModuleMap() throws DataLoadingException {
        return Optional.empty();
    }
}
