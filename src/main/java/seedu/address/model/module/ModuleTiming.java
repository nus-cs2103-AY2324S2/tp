package seedu.address.model.module;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Module in the address book.
 * Guarantees: details are present and not null, field values are validated,
 * immutable.
 */
public class ModuleTiming {
    private final ModuleCode moduleCode;
    private final Day day;
    private final Timing startTime;
    private final Timing endTime;

    /**
     * The constructor for a Module. This requires that all fields cannot be null.
     */
    public ModuleTiming(
            ModuleCode moduleCode,
            Day day,
            Timing startTime,
            Timing endTime) {
        requireAllNonNull(moduleCode, day, startTime, endTime);
        this.moduleCode = moduleCode;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public ModuleCode getModuleCode() {
        return moduleCode;
    }

    public Day getDay() {
        return day;
    }

    public Timing getStartTime() {
        return startTime;
    }

    public Timing getEndTime() {
        return endTime;
    }
}
