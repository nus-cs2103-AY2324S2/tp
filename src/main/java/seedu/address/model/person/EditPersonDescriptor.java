package seedu.address.model.person;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;


/**
 * Stores the details to edit the person with. Each non-empty field value will replace the
 * corresponding field value of the person.
 */
public class EditPersonDescriptor {
    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;
    private JobDescription jobDescription;
    private InterviewDate interviewDate;
    private InternDuration internDuration;
    private Salary salary;

    /**
     * Copy constructor.
     * A defensive copy of {@code tags} is used internally.
     */
    public EditPersonDescriptor(EditPersonDescriptor toCopy) {
        setName(toCopy.name);
        setPhone(toCopy.phone);
        setEmail(toCopy.email);
        setAddress(toCopy.address);
        setTags(toCopy.tags);
        setJobDescription(toCopy.jobDescription);
        setInterviewDate(toCopy.interviewDate);
        setInternDuration(toCopy.internDuration);
        setSalary(toCopy.salary);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(name, phone, email, address, tags, jobDescription, interviewDate, internDuration, salary);
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Optional<Name> getName() {
        return Optional.ofNullable(name);
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Optional<Phone> getPhone() {
        return Optional.ofNullable(phone);
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Optional<Email> getEmail() {
        return Optional.ofNullable(email);
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Optional<Address> getAddress() {
        return Optional.ofNullable(address);
    }

    /**
     * Sets {@code tags} to this object's {@code tags}.
     * A defensive copy of {@code tags} is used internally.
     */
    public void setTags(Set<Tag> tags) {
        this.tags = (tags != null) ? new HashSet<>(tags) : null;
    }

    /**
     * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     * Returns an empty set if {@code tags} is null.
     */
    public Set<Tag> getTags() {
        return (tags != null) ? Collections.unmodifiableSet(tags) : Collections.emptySet();
    }


    public void setJobDescription(JobDescription jobDescription) {
        this.jobDescription = jobDescription;
    }

    public Optional<JobDescription> getJobDescription() {
        return Optional.ofNullable(jobDescription);
    }

    public void setInterviewDate(InterviewDate interviewDate) {
        this.interviewDate = interviewDate;
    }

    public Optional<InterviewDate> getInterviewDate() {
        return Optional.ofNullable(interviewDate);
    }

    public void setInternDuration(InternDuration internDuration) {
        this.internDuration = internDuration;
    }

    public Optional<InternDuration> getInternDuration() {
        return Optional.ofNullable(internDuration);
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public Optional<Salary> getSalary() {
        return Optional.ofNullable(salary);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditPersonDescriptor)) {
            return false;
        }

        EditPersonDescriptor otherEditPersonDescriptor = (EditPersonDescriptor) other;
        return Objects.equals(name, otherEditPersonDescriptor.name)
                && Objects.equals(phone, otherEditPersonDescriptor.phone)
                && Objects.equals(email, otherEditPersonDescriptor.email)
                && Objects.equals(address, otherEditPersonDescriptor.address)
                && Objects.equals(tags, otherEditPersonDescriptor.tags)
                && Objects.equals(jobDescription, otherEditPersonDescriptor.jobDescription)
                && Objects.equals(interviewDate, otherEditPersonDescriptor.interviewDate)
                && Objects.equals(internDuration, otherEditPersonDescriptor.internDuration)
                && Objects.equals(salary, otherEditPersonDescriptor.salary);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("tags", tags)
                .add("jobDescription", jobDescription)
                .add("interviewDate", interviewDate)
                .add("internDuration", internDuration)
                .add("salary", salary)
                .toString();
    }
}
