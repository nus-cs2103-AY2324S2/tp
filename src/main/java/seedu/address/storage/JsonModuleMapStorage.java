package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleMap;

public class JsonModuleMapStorage implements ModuleMapStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonModuleMapStorage.class);

    private final Path filePath = Paths.get("src/main/resources/module_data.json");

    public JsonModuleMapStorage() {}

    @Override
    public Path getModuleFilePath() {
        return null;
    }

    @Override
    public ModuleMap readModuleMap() {
        requireNonNull(filePath);

        ModuleMap moduleMap = new ModuleMap();

        Optional<JsonAdaptedModule[]> jsonModuleMap = Optional.empty();

        try {
            jsonModuleMap = JsonUtil.readJsonArrayFile(filePath, JsonAdaptedModule[].class);
        } catch (DataLoadingException e) {
            logger.severe("Error reading from module file: " + filePath);
            throw new Error("Error reading from module file: " + filePath, e);
        }

        JsonAdaptedModule[] jsonModules = jsonModuleMap.orElse(new JsonAdaptedModule[0]);
        for (JsonAdaptedModule jsonModule : jsonModules) {
            try {
                Module module = jsonModule.toModelType();
                moduleMap.addModule(module);
            } catch (IllegalValueException ive) {
                throw new Error("Error reading from module file: " + filePath, ive);
            }
        }
        return moduleMap;
    }
}
