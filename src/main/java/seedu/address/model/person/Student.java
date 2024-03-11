package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import seedu.address.model.tag.Tag;

import java.util.Set;

/**
 * Represents a Student in the address book.
 * A <code>Student</code> is a <code>Person</code>.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student extends Person {
    // Student Data Fields
    private final Payment payment;

    public Student(Name name, Phone phone, Email email, Address address, Set<Tag> tags, Payment payment) {
        super(name, phone, email, address, tags);
        requireNonNull(payment);
        this.payment = payment;
    }

    public Payment getPayment() {
        return payment;
    }
}
