package seedu.address.model.student;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.grade.Grade;
import seedu.address.model.timeslots.Timeslots;

/**
 * Represents a Student in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Timeslots> timeslots = new HashSet<>();

    private final Set<Grade> grades = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Student(Name name, Phone phone, Email email, Address address, Set<Timeslots> timeslots, Set<Grade> grades) {
        requireAllNonNull(name, phone, email, address, timeslots, grades);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.timeslots.addAll(timeslots);
        this.grades.addAll(grades);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable timeslot set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Timeslots> getTimeslots() {
        return Collections.unmodifiableSet(timeslots);
    }

    /**
     * Returns an immutable grade set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Grade> getGrades() {
        return Collections.unmodifiableSet(grades);
    }

    /**
     * Returns true if both students have the same name.
     * This defines a weaker notion of equality between two students.
     */
    public boolean isSameStudent(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && otherStudent.getName().equals(getName());
    }

    /**
     * Returns true if both students have the same identity and data fields.
     * This defines a stronger notion of equality between two students.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Student)) {
            return false;
        }

        Student otherStduent = (Student) other;
        return name.equals(otherStduent.name)
                && phone.equals(otherStduent.phone)
                && email.equals(otherStduent.email)
                && address.equals(otherStduent.address)
                && timeslots.equals(otherStduent.timeslots)
                && grades.equals(otherStduent.grades);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, timeslots, grades);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("timeslots", timeslots)
                .add("grades", grades)
                .toString();
    }

}
