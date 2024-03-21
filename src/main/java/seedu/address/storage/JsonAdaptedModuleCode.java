package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.ModuleCode;

/**
 * Jackson-friendly version of {@link ModuleCode}.
 */
class JsonAdaptedModuleCode {

    private final String moduleCode;

    /**
     * Constructs a {@code moduleCode} with the given {@code moduleCode}.
     */
    @JsonCreator
    public JsonAdaptedModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedModuleCode(ModuleCode source) {
        moduleCode = source.getCode();
    }

    @JsonValue
    public String getModuleCode() {
        return moduleCode;
    }

    /**
     * Converts this Jackson-friendly adapted moduleCode object into the model's {@code ModuleCode} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted moduleCode.
     */
    public ModuleCode toModelType() throws IllegalValueException {
        if (!ModuleCode.isValidCode(moduleCode)) {
            throw new IllegalValueException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        return new ModuleCode(moduleCode);
    }

}
