package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Grade grade;
    private final Subject subject;
    private final Attendance attendance;
    private final Payment payment;
    private final Set<DateTime> dateTimes = new HashSet<>();
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Grade grade, Subject subject,
                  Attendance attendance, Payment payment, Set<DateTime> dateTimes, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, grade, subject, attendance, payment, dateTimes, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.grade = grade;
        this.subject = subject;
        this.attendance = attendance;
        this.payment = payment;
        this.dateTimes.addAll(dateTimes);
        this.tags.addAll(tags);
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

    public Grade getGrade() {
        return grade;
    }

    public Subject getSubject() {
        return subject;
    }
    public Attendance getAttendance() {
        return attendance;
    }

    public Payment getPayment() {
        return payment;
    }
    public Set<DateTime> getDateTimes() {
        return Collections.unmodifiableSet(dateTimes);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
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
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && dateTimes.equals(otherPerson.dateTimes)
                && tags.equals(otherPerson.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, dateTimes, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("grade", grade)
                .add("subject", subject)
                .add("attendance", attendance)
                .add("payment", payment)
                .add("dateTimes", dateTimes)
                .add("tags", tags)
                .toString();
    }

}
