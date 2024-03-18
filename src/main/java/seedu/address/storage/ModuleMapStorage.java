package seedu.address.storage;

import java.nio.file.Path;

/**
 * Represents a storage for {@link seedu.address.model.module.ModuleMap}.
 */
public interface ModuleMapStorage {
    Path getModuleFilePath();
}
