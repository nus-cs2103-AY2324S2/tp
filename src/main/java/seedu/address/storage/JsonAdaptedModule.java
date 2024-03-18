package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.Description;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.Title;

public class JsonAdaptedModule {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Module's %s field is missing!";

    private final String moduleCode;
    private final String title;
    private final String description;

    public JsonAdaptedModule(
            @JsonProperty("moduleCode") String moduleCode,
            @JsonProperty("title") String title,
            @JsonProperty("description") String description) {
        this.moduleCode = moduleCode;
        this.title = title;
        this.description = description;
    }

    public JsonAdaptedModule(Module source) {
        moduleCode = source.getModuleCode().getCode();
        title = source.getTitle().getTitle();
        description = source.getDescription().getValue();
    }

    public Module toModelType() throws IllegalValueException {
        final ModuleCode modlModuleCode = new ModuleCode(moduleCode);
        final Title modelTitle = new Title(title);
        final Description modelDescription = new Description(description);
        return new Module(modlModuleCode, modelTitle, modelDescription);
    }
}
