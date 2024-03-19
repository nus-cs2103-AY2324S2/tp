package seedu.address.model.person;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Employee in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Employee extends Person {

    private final Department department;
    private final JobTitle jobTitle;
    private Skills skills = new Skills(new HashSet<>());

    /**
     * Every field must be present and not null.
     */
    public Employee(Name name, Phone phone, Email email, Address address, Remark remark, Set<Tag> tags,
                    Department department, JobTitle jobTitle, Skills skills) {
        super(name, phone, email, address, remark, tags);
        this.department = department;
        this.jobTitle = jobTitle;
        this.skills = skills;
    }

    public Department getDepartment() {
        return this.department;
    }

    public JobTitle getJobTitle() {
        return this.jobTitle;
    }

    public Skills getSkills() {
        return this.skills;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Employee
                && super.equals(other)
                && department.equals(((Employee) other).department)
                && jobTitle.equals(((Employee) other).jobTitle)
                && skills.equals(((Employee) other).skills));
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), department, jobTitle, skills);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("remark", remark)
                .add("tags", tags)
                .add("department", department)
                .add("jobTitle", jobTitle)
                .add("skills", skills)
                .toString();
    }
}
