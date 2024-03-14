package seedu.address.model.module;

import seedu.address.commons.core.Config;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.util.JsonUtil;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Optional;

public class ModuleMap {
    public static final Path DEFAULT_MODULE_DATA_FILE = Paths.get("src/main/resources/module_data.json");
    public HashMap<String, Module> moduleMap;

    /**
     * Initialises ModuleMap object, which stores a mapping from the module name to the Module object.
     * @throws DataLoadingException Throws an error if there is an issue getting the module data.
     */
    public ModuleMap() throws DataLoadingException {
        this.moduleMap = new HashMap<>();

        Optional<Module[]> optionalModuleArray = JsonUtil.readJsonArrayFile(DEFAULT_MODULE_DATA_FILE, Module[].class);
        Module[] moduleArray = optionalModuleArray.orElse(new Module[]{});

        for (Module module : moduleArray) {
            this.moduleMap.put(module.getModuleCode().getCode(), module);
        }
    }
}
