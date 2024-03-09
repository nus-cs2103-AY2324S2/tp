package seedu.address.model.module;

import seedu.address.model.person.Name;
import java.util.ArrayList;

import java.util.Set;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class Module {
    final Name name;
    private final ArrayList<TutorialClass> tutorialClasses;

    public Module(Name name) {
        requireAllNonNull(name);
        this.name = name;
        this.tutorialClasses = new ArrayList<TutorialClass>();
    }

    public Module(Name name, TutorialClass tutorialClass) {
        requireAllNonNull(name);
        this.name = name;
        this.tutorialClasses = new ArrayList<TutorialClass>();
        tutorialClasses.add(tutorialClass);
    }

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

    public ArrayList<TutorialClass> getTutorialClasses() {
        return tutorialClasses;
    }

    public String getTutorialClassesNames() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tutorialClasses.size(); i++) {
            output.append(tutorialClasses.get(i).toString());
        }
        return output.toString();
    }
}
