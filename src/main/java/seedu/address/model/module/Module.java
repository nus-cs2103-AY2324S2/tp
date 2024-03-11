package seedu.address.model.module;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;

import seedu.address.model.person.Name;

/**
 * Class used to handle the representation of modules.
 */
public class Module {
    final Name name;
    private final ArrayList<TutorialClass> tutorialClasses;

    /**
     * A constructor for Module. Used to initialise a new module with no tutorial classes
     *
     * @param name of the module to be created
     */
    public Module(Name name) {
        requireAllNonNull(name);
        this.name = name;
        this.tutorialClasses = new ArrayList<TutorialClass>();
    }

    /**
     * A constructor for Module. Used to initialise a new module with the tutorial class specified.
     * This is the constructor used when /add_class is used.
     *
     * @param name of the module to be created
     * @param tutorialClass to be added within the module
     */
    public Module(Name name, TutorialClass tutorialClass) {
        requireAllNonNull(name);
        this.name = name;
        this.tutorialClasses = new ArrayList<TutorialClass>();
        tutorialClasses.add(tutorialClass);
    }

    /**
     * A constructor for Module. Used to initialise a new module with the list of tutorial classes specified.
     * Used to get the representation of the module from the list of classes and the name specified.
     *
     * @param name of the module to be created
     * @param tutorialClasses of the module to be created
     */
    public Module(Name name, ArrayList<TutorialClass> tutorialClasses) {
        requireAllNonNull(name);
        this.name = name;
        this.tutorialClasses = tutorialClasses;
    }

    @Override
    public String toString() {
        return name.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Module // instanceof handles nulls
                && name.equals(((Module) other).name)); // state check
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public Name getName() {
        return name;
    }

    /**
     * Returns the ArrayList of tutorial classes under this module.
     *
     * @return the ArrayList of tutorial classes.
     */
    public ArrayList<TutorialClass> getTutorialClasses() {
        return tutorialClasses;
    }

    /**
     * Outputs the list of tutorial classes under this module as a String.
     *
     * @return the list of tutorial classes.
     */
    public String getTutorialClassesNames() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tutorialClasses.size(); i++) {
            output.append(tutorialClasses.get(i).toString());
        }
        return output.toString();
    }
}
