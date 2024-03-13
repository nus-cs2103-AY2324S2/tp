package seedu.address.model.module;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a class in address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ClassList {
    private final ModuleCode moduleCode;
    private final TutorialClass tutorialClass;

    /**
     * Every field must be present and not null.
     */
    public ClassList(ModuleCode moduleCode, TutorialClass tutorialClass) {
        requireAllNonNull(moduleCode, tutorialClass);
        this.moduleCode = moduleCode;
        this.tutorialClass = tutorialClass;
    }

    public ModuleCode getModuleCode() {
        return moduleCode;
    }

    public TutorialClass getTutorialClass() {
        return tutorialClass;
    }

    /**
     * Returns true if both classes have the same module code and tutorial class.
     * This defines a weaker notion of equality between two classes.
     */
    public boolean isSameClass(ClassList otherClassList) {
        if (otherClassList == this) {
            return true;
        }

        return otherClassList != null
                && otherClassList.getModuleCode().equals(getModuleCode())
                && otherClassList.getTutorialClass().equals(getTutorialClass());
    }

    /**
     * Returns true if both classes have the same module code and tutorial class.
     * This defines a stronger notion of equality between two classes.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ClassList)) {
            return false;
        }

        ClassList otherClassList = (ClassList) other;
        return moduleCode.equals(otherClassList.moduleCode)
                && tutorialClass.equals(otherClassList.tutorialClass);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(moduleCode, tutorialClass);
    }

    @Override
    public String toString() {
        return "Module Code: " + moduleCode + ", Tutorial Class: " + tutorialClass;
    }
}
