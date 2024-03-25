package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.*;
import seedu.address.model.module.Module;

/**
 * Jackson-friendly version of {@link Module}.
 */
public class JsonAdaptedModuleTiming {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "ModuleTiming's %s field is missing!";

    private final String moduleCode;
    private final String day;
    private final String startTime;
    private final String endTime;

    /**
     * Constructs a {@code JsonAdaptedModule} with the given module details.
     */
    public JsonAdaptedModuleTiming(
            @JsonProperty("moduleCode") String moduleCode,
            @JsonProperty("day") String day,
            @JsonProperty("startTime") String startTime,
            @JsonProperty("endTime") String endTime) {
        this.moduleCode = moduleCode;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Converts a given {@code Module} into this class for Jackson use.
     */
    public JsonAdaptedModuleTiming(ModuleTiming source) {
        moduleCode = source.getModuleCode().getCode();
        day = source.getDay().getDayString();
        startTime = source.getStartTime().getTimingString();
        endTime = source.getEndTime().getTimingString();
    }


    /**
     * Converts this Jackson-friendly adapted module object into the model's {@code Module} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted module.
     */
    public ModuleTiming toModelType() throws IllegalValueException {
        final ModuleCode modelModuleCode = new ModuleCode(moduleCode);
        final Day modelDay = new Day(day);
        final Timing modelStartTime = new Timing(startTime);
        final Timing modelEndTime = new Timing(endTime);
        return new ModuleTiming(modelModuleCode, modelDay, modelStartTime, modelEndTime);
    }
}
