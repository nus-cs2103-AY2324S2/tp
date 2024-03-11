package seedu.address.testutil;

import seedu.address.model.classes.ClassList;
import seedu.address.model.classes.ModuleCode;
import seedu.address.model.classes.TutorialClass;
/**
 * The ClassBuilder class is responsible for constructing instances of the Class class.
 * It provides methods to set various attributes of the Class being built.
 */
public class ClassBuilder {
    public static final String DEFAULT_MODULE_CODE = "CS2103T";
    public static final String DEFAULT_TUTORIAL_CLASS = "T09";

    private ModuleCode moduleCode;
    private TutorialClass tutorialClass;

    /**
     * Creates a {@code ClassBuilder} with the default details.
     */
    public ClassBuilder() {
        moduleCode = new ModuleCode(DEFAULT_MODULE_CODE);
        tutorialClass = new TutorialClass(DEFAULT_TUTORIAL_CLASS);
    }
    /**
     * Initializes the ClassBuilder with the data of {@code classToCopy}.
     */
    public ClassBuilder(ClassList classListToCopy) {
        moduleCode = classListToCopy.getModuleCode();
        tutorialClass = classListToCopy.getTutorialClass();
    }

    /**
     * Sets the module code of the {@code Class} that we are building.
     */
    public ClassBuilder withModuleCode(String moduleCode) {
        this.moduleCode = new ModuleCode(moduleCode);
        return this;
    }

    /**
     * Sets the tutorial class of the {@code Class} that we are building.
     */
    public ClassBuilder withTutorialClass(String tutorialClass) {
        this.tutorialClass = new TutorialClass(tutorialClass);
        return this;
    }

    /**
     * Builds and returns an instance of {@code Class}.
     */
    public ClassList build() {
        return new ClassList(moduleCode, tutorialClass);
    }
}
