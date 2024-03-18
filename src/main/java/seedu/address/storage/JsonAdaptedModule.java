package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.Description;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.Title;

/**
 * Jackson-friendly version of {@link Module}.
 */
public class JsonAdaptedModule {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Module's %s field is missing!";

    private final String moduleCode;
    private final String title;
    private final String description;

    /**
     * Constructs a {@code JsonAdaptedModule} with the given module details.
     */
    public JsonAdaptedModule(
            @JsonProperty("moduleCode") String moduleCode,
            @JsonProperty("title") String title,
            @JsonProperty("description") String description) {
        this.moduleCode = moduleCode;
        this.title = title;
        this.description = description;
    }

    /**
     * Converts a given {@code Module} into this class for Jackson use.
     */
    public JsonAdaptedModule(Module source) {
        moduleCode = source.getModuleCode().getCode();
        title = source.getTitle().getTitle();
        description = source.getDescription().getValue();
    }

    /**
     * Converts this Jackson-friendly adapted module object into the model's {@code Module} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted module.
     */
    public Module toModelType() throws IllegalValueException {
        final ModuleCode modlModuleCode = new ModuleCode(moduleCode);
        final Title modelTitle = new Title(title);
        final Description modelDescription = new Description(description);
        return new Module(modlModuleCode, modelTitle, modelDescription);
    }
}
