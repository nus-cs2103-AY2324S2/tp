package seedu.address.model.module;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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
    @JsonCreator
    public Module(
            @JsonProperty("moduleCode") Code moduleCode,
            @JsonProperty("title") Title title,
            @JsonProperty("description") Description description
    ) {
        requireAllNonNull(moduleCode, title, description);
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
