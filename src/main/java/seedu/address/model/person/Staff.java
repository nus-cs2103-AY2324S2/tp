package seedu.address.model.person;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

import java.util.Objects;
import java.util.Set;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class Staff extends Person{
    // Data fields
    private final Salary salary;
    private final Employment employment;

    public Staff(Name name, Phone phone, Email email, Address address, Set<Tag> tags,
                 Salary salary, Employment employment) {
        super(name, phone, email, address, tags);
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
     * Returns true if both staffs have the same identity and data fields.
     * This defines a stronger notion of equality between two staff.
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
        return super.toString() + new ToStringBuilder(this)
                .add("salary", salary)
                .add("employment", employment)
                .toString();
    }
}
