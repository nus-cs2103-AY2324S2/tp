package seedu.address.model.module;

import java.util.Map;

public interface ReadOnlyModuleMap {
    Map<ModuleCode, Module> getModuleMap();
}
