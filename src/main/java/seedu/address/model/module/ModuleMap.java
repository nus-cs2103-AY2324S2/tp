package seedu.address.model.module;

import seedu.address.commons.core.Config;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.util.JsonUtil;

import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Optional;

public class ModuleMap {
    public static final Path DEFAULT_MODULE_DATA_FILE = Paths.get("module_data.json");
    public HashMap<String, Module> moduleMap;

    public ModuleMap() throws DataLoadingException {
        this.moduleMap = new HashMap<>();
        Optional<Module[]> moduleArray = JsonUtil.readJsonArrayFile(DEFAULT_MODULE_DATA_FILE, Module[].class);
    }

    public static void main(String[] args) throws DataLoadingException {
        new ModuleMap();
    }
}
