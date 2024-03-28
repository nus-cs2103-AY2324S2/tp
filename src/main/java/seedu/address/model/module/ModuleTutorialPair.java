package seedu.address.model.module;

/**
 * Represents a pair of a module and a tutorial class.
 */
public class ModuleTutorialPair {
    private ModuleCode module;
    private TutorialClass tutorialClass;

    /**
     * Creates a ModuleTutorialPair object.
     * @param module        The module code.
     * @param tutorialClass The tutorial class.
     */
    public ModuleTutorialPair(ModuleCode module, TutorialClass tutorialClass) {
        this.module = module;
        this.tutorialClass = tutorialClass;
    }

    /**
     * Gets the module code.
     * @return The module code.
     */
    public ModuleCode getModule() {
        return module;
    }

    /**
     * Gets the tutorial class.
     * @return The tutorial class.
     */
    public TutorialClass getTutorialClass() {
        return tutorialClass;
    }

    @Override
    public String toString() {
        return "(" + module + ", " + tutorialClass + ")";
    }
}
