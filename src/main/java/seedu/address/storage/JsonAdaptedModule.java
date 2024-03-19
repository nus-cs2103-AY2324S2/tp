package seedu.address.storage;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.TutorialClass;

/**
 * Jackson-friendly version of {@link ModuleCode}.
 */
class JsonAdaptedModule {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Module name is missing!";

    private final String name;
    private final ArrayList<JsonAdaptedTutorialClass> tutorialClasses;

    /**
     * Constructs a {@code JsonAdaptedModule} with the given module details.
     */
    @JsonCreator
    public JsonAdaptedModule(@JsonProperty("name") String name, @JsonProperty("tutorialClasses")
        ArrayList<JsonAdaptedTutorialClass> tutorialClasses) {
        this.name = name;
        this.tutorialClasses = tutorialClasses;
    }

    /**
     * Converts a given {@code Module} into this class for Jackson use.
     */
    public JsonAdaptedModule(ModuleCode source) {
        name = source.toString();
        this.tutorialClasses = source.getTutorialClasses().stream().map(JsonAdaptedTutorialClass::new)
            .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Retrieves module name from the module.
     */
    public String getModuleName() {
        return name;
    }

    /**
     * Retrieves tutorial classes from the module.
     */
    public ArrayList<JsonAdaptedTutorialClass> getTutorialClasses() {
        return tutorialClasses;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public ModuleCode toModelType() throws IllegalValueException {
        if (!ModuleCode.isValidModuleCode(name)) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, name));
        }
        ArrayList<TutorialClass> tutorialClasses = new ArrayList<>();
        for (JsonAdaptedTutorialClass tutorialClass : this.tutorialClasses) {
            tutorialClasses.add(tutorialClass.toModelType());
        }
        return new ModuleCode(name, tutorialClasses);
    }
}
