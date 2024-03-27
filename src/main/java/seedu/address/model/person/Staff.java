package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Staff in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Staff extends Person {
    // Data fields
    private final Salary salary;
    private final Employment employment;

    /**
     * Every field must be present and not null.
     */
    public Staff(Name name, Phone phone, Email email, Address address, Note note, Set<Tag> tags,
                 Salary salary, Employment employment, Rating rating) {
        super(name, phone, email, address, note, tags, rating);
        requireAllNonNull(salary, employment);
        this.salary = salary;
        this.employment = employment;
    }

    public Salary getSalary() {
        return salary;
    }

    public Employment getEmployment() {
        return employment;
    }

    /**
     * Returns a new instantiation of the current {@code Staff} with the updated note,
     * which throws {@code UnsupportedOperationException} if modification is attempted.
     */
    @Override
    public Staff updateNote(Note note) {
        Staff staffToReturn = new Staff(this.getName(), this.getPhone(), this.getEmail(), this.getAddress(), note,
                this.getTags(), this.salary, this.employment, this.getRating());
        staffToReturn.setPinIfPinned(this);
        return staffToReturn;
    }

    /**
     * Returns a new instantiation of the current {@code Staff} with the updated rating,
     * which throws {@code UnsupportedOperationException} if modification is attempted.
     */
    @Override
    public Staff updateRating(Rating rating) {
        Staff staffToReturn = new Staff(this.getName(), this.getPhone(), this.getEmail(), this.getAddress(),
                this.getNote(), this.getTags(), this.salary, this.employment, rating);
        staffToReturn.setPinIfPinned(this);
        return staffToReturn;
    }

    /**
     * Returns true if both staffs have the same identity and data fields.
     * This defines a stronger notion of equality between two staff.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Staff)) {
            return false;
        }

        Staff otherPerson = (Staff) other;
        return super.equals(otherPerson)
                && salary.equals(otherPerson.salary)
                && employment.equals(otherPerson.employment);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(super.hashCode(), salary, employment);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", getName())
                .add("phone", getPhone())
                .add("email", getEmail())
                .add("address", getAddress())
                .add("tags", getTags())
                .add("rating", getRating())
                .add("salary", salary)
                .add("employment", employment)
                .toString();
    }
}
