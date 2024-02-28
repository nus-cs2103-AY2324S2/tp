package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
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
    private final Name companyName;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Tag tag;
    private final JobDescription jobDescription;
    private final InterviewDate interviewDate;
    private final InternDuration internDuration;
    private final Salary salary;

    /**
     * Every field must be present and not null.
     */
    public Person(Name companyName, Phone phone, Email email, Address address, Tag tag,
                  JobDescription jobDescription, InterviewDate interviewDate,
                  InternDuration internDuration, Salary salary) {
        requireAllNonNull(companyName, phone, email, tag, jobDescription);
        this.companyName = companyName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tag = tag;
        this.jobDescription = jobDescription;
        this.interviewDate = interviewDate;
        this.internDuration = internDuration;
        this.salary = salary;
    }

    public Name getCompanyName() {
        return companyName;
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

    public Tag getTag() {
        return tag;
    }

    public JobDescription getJobDescription() {
        return jobDescription;
    }

    public InterviewDate getInterviewDate() {
        return interviewDate;
    }

    public InternDuration getInternDuration() {
        return internDuration;
    }

    public Salary getSalary() {
        return salary;
    }

    /**
     * Returns true if both inputs have the same company name and job description.
     * This defines a weaker notion of equality between two inputs.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getCompanyName().equals(getCompanyName())
                && otherPerson.getJobDescription().equals(getJobDescription());
    }

    /**
     * Returns true if both inputs have the same identity and data fields.
     * This defines a stronger notion of equality between two inputs.
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
        return companyName.equals(otherPerson.companyName)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && tag.equals(otherPerson.tag)
                && jobDescription.equals(otherPerson.jobDescription)
                && interviewDate.equals(otherPerson.interviewDate)
                && internDuration.equals(otherPerson.internDuration)
                && salary.equals(otherPerson.salary);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(companyName, phone, email, address, tag,
                jobDescription, interviewDate, internDuration, salary);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", companyName)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("tag", tag)
                .add("job description", jobDescription)
                .add("interview date", interviewDate)
                .add("intern duration", internDuration)
                .add("salary", salary)
                .toString();
    }

}
