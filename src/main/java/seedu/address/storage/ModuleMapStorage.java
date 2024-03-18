package seedu.address.storage;

import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.module.ReadOnlyModuleMap;

/**
 * Represents a storage for {@link seedu.address.model.module.ModuleMap}.
 */
public interface ModuleMapStorage {
    Path getModuleFilePath();

    Optional<ReadOnlyModuleMap> readModuleMap() throws DataLoadingException;
}
