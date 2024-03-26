package seedu.teachstack.model.person;

import static seedu.teachstack.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.teachstack.commons.util.ToStringBuilder;
import seedu.teachstack.model.group.Group;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person implements Comparable<Person> {

    // Identity fields
    private final Name name;
    private final StudentId studentId;
    private final Email email;

    // Data fields
    private final Set<Group> groups = new HashSet<>();
    private final Grade grade;

    /**
     * Every field must be present and not null.
     */

    public Person(Name name, StudentId studentId, Email email, Grade grade,
                  Set<Group> groups) {
        requireAllNonNull(name, studentId, email, grade, groups);
        this.name = name;
        this.studentId = studentId;
        this.email = email;
        this.groups.addAll(groups);
        this.grade = grade;
    }

    public Name getName() {
        return name;
    }

    public StudentId getStudentId() {
        return studentId;
    }

    public Email getEmail() {
        return email;
    }

    public Grade getGrade() {
        return grade;
    }

    /**
     * Returns an immutable group set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Group> getGroups() {
        return Collections.unmodifiableSet(groups);
    }

    /**
     * Returns true if both persons have the same student id.
     * This defines a stronger notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getStudentId().equals(getStudentId())
                && otherPerson.isSameEmail(this);
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && studentId.equals(otherPerson.studentId)
                && email.equals(otherPerson.email)
                && grade.equals(otherPerson.grade)
                && groups.equals(otherPerson.groups);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own

        return Objects.hash(name, studentId, email, grade, groups);

    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("studentId", studentId)
                .add("email", email)
                .add("grade", grade)
                .add("groups", groups)
                .toString();
    }


    public boolean isWeak() {
        return grade.isWeak();
    }


    @Override
    public int compareTo(Person o) {
        return this.grade.compareTo(o.grade);
    }

    /**
     * @param otherPerson to be compared
     * @return if both have same email
     */
    public boolean isSameEmail(Person otherPerson) {
        if (otherPerson == null) {
            return false;
        }
        return this.getEmail().equals(otherPerson.getEmail());
    }

}
