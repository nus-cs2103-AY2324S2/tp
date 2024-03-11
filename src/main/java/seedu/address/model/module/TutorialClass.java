package seedu.address.model.module;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;

import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Class used to handle representation of a tutorial class within a module.
 */
public class TutorialClass {
    final Name name;
    private final ArrayList<Person> students;

    /**
     * A constructor for TutorialClass. Creates an empty tutorial class with no students.
     *
     * @param name of tutorial to be added
     */
    public TutorialClass(Name name) {
        requireAllNonNull(name);
        this.name = name;
        this.students = new ArrayList<Person>();
    }

    /**
     * A constructor for TutorialClass. Creates a tutorial with one student, that is specified.
     *
     * @param name of tutorial to be added
     * @param student to be added
     */
    public TutorialClass(Name name, Person student) {
        requireAllNonNull(name);
        this.name = name;
        this.students = new ArrayList<Person>();
        students.add(student);
    }

    /**
     * A constructor for TutorialClass. Creates a tutorial with the list of students specified.
     *
     * @param name of tutorial to be added
     * @param students to be in the added tutorial
     */
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
