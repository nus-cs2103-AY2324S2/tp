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

    /**
     * Every field must be present and not null.
     */
    public Employee(Id id, Name name, Phone phone, Email email, Address address, Remark remark, Set<Tag> tags,
                    Department department, JobTitle jobTitle, Skills skills) {
        super(id, name, phone, email, address, remark, tags);
        this.department = department;
        this.jobTitle = jobTitle;
        this.skills = skills;
    }

    /**
     * Returns the department of the employee.
     *
     * @return The department of the employee.
     */
    public Department getDepartment() {
        return this.department;
    }

    /**
     * Returns the job title of the employee.
     *
     * @return The job title of the employee.
     */
    public JobTitle getJobTitle() {
        return this.jobTitle;
    }

    /**
     * Returns the skills of the employee.
     *
     * @return The skills of the employee.
     */
    public Skills getSkills() {
        return this.skills;
    }

    public String getRole() {
        return "Employee";
    }

    /**
     * Checks if this employee is the same as another person.
     * Two employees are considered the same if they have the same attributes.
     *
     * @param otherPerson The person to compare with.
     * @return True if the employees are the same, false otherwise.
     */
    @Override
    public boolean isSamePerson(Person otherPerson) {
        if (!super.isSamePerson(otherPerson)) {
            return false;
        }
        return otherPerson instanceof Employee;
    }

    /**
     * Checks if this employee is equal to another object.
     * Two employees are considered equal if they have the same attributes.
     *
     * @param other The object to compare with.
     * @return True if the employees are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Employee
                && super.equals(other)
                && department.equals(((Employee) other).department)
                && jobTitle.equals(((Employee) other).jobTitle)
                && skills.equals(((Employee) other).skills));
    }

    /**
     * Returns the hash code of the employee.
     *
     * @return The hash code of the employee.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), department, jobTitle, skills);
    }

    /**
     * Returns the string representation of the employee.
     *
     * @return The string representation of the employee.
     */
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
