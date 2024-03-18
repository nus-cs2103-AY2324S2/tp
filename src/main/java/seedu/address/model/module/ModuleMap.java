package seedu.address.model.module;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Wrapper for a hashmap that stores all Module objects
 */
public class ModuleMap implements ReadOnlyModuleMap {
    private final HashMap<ModuleCode, Module> moduleMap;

    public ModuleMap() {
        this.moduleMap = new HashMap<>();
    }

    public void addModule(Module module) {
        moduleMap.put(module.getModuleCode(), module);
    }

    @Override
    public Map<ModuleCode, Module> getModuleMap() {
        return Collections.unmodifiableMap(moduleMap);
    }
}
