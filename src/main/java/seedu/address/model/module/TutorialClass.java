package seedu.address.model.module;

import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

import java.util.ArrayList;

import java.util.Set;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class TutorialClass {
    final Name name;
    private final ArrayList<Person> students;

    public TutorialClass(Name name) {
        requireAllNonNull(name);
        this.name = name;
        this.students = new ArrayList<Person>();
    }

    public TutorialClass(Name name, Person student) {
        requireAllNonNull(name);
        this.name = name;
        this.students = new ArrayList<Person>();
        students.add(student);
    }

    public TutorialClass(Name name, ArrayList<Person> students) {
        requireAllNonNull(name);
        this.name = name;
        this.students = students;
    }

    @Override
    public String toString() {
        return name.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TutorialClass // instanceof handles nulls
                && name.equals(((TutorialClass) other).name)); // state check
    }

    public Name getName() {
        return name;
    }

    public ArrayList<Person> getStudents() {
        return students;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
