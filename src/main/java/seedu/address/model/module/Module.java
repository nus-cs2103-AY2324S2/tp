package seedu.address.model.module;

/**
 * Represents a Module in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Module {
    private final Code moduleCode;
    private final Title title;
    private final Description description;

    /**
     * The constructor for a Module. This requires that all fields cannot be null.
     */
    public Module(Code moduleCode, Title title, Description description) {
        this.moduleCode = moduleCode;
        this.title = title;
        this.description = description;
    }

    public Code getModuleCode() {
        return moduleCode;
    }

    public Title getTitle() {
        return title;
    }

    public Description getDescription() {
        return description;
    }
}
