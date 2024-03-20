package seedu.address.model.module;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Module in the address book.
 * Guarantees: details are present and not null, field values are validated,
 * immutable.
 */
public class Module {
    private final ModuleCode moduleCode;
    private final Title title;
    private final Description description;

    /**
     * The constructor for a Module. This requires that all fields cannot be null.
     */
    public Module(
            ModuleCode moduleCode,
            Title title,
            Description description) {
        requireAllNonNull(moduleCode, title, description);
        this.moduleCode = moduleCode;
        this.title = title;
        this.description = description;
    }

    public ModuleCode getModuleCode() {
        return moduleCode;
    }

    public Title getTitle() {
        return title;
    }

    public Description getDescription() {
        return description;
    }
}
