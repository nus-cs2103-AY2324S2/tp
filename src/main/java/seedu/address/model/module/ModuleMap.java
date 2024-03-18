package seedu.address.model.module;

import java.util.HashMap;

/**
 * Wrapper for a hashmap that stores all Module objects
 */
public class ModuleMap {
    private final HashMap<ModuleCode, Module> moduleMap;

    public ModuleMap() {
        this.moduleMap = new HashMap<>();
    }

    public void addModule(Module module) {
        moduleMap.put(module.getModuleCode(), module);
    }

    public boolean hasModule(ModuleCode code) {
        return moduleMap.containsKey(code);
    }
    public Module getModule(ModuleCode code) {
        return moduleMap.get(code);
    }
}
