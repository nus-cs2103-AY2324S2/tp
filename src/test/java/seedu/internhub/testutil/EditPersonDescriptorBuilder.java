package seedu.internhub.testutil;

import seedu.internhub.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.internhub.model.person.Address;
import seedu.internhub.model.person.Email;
import seedu.internhub.model.person.InternDuration;
import seedu.internhub.model.person.InterviewDate;
import seedu.internhub.model.person.JobDescription;
import seedu.internhub.model.person.Name;
import seedu.internhub.model.person.Person;
import seedu.internhub.model.person.Phone;
import seedu.internhub.model.person.Salary;
import seedu.internhub.model.person.Tag;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditPersonDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Person person) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(person.getCompanyName());
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
        descriptor.setAddress(person.getAddress());
        descriptor.setTag(person.getTag());
        descriptor.setJobDescription(person.getJobDescription());
        descriptor.setInterviewDate(person.getInterviewDate());
        descriptor.setInternDuration(person.getInternDuration());
        descriptor.setSalary(person.getSalary());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code Tag} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withTags(String tag) {
        descriptor.setTag(new Tag(tag));
        return this;
    }

    /**
     * Sets the {@code JobDescription} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withJobDescription(String jobDescription) {
        descriptor.setJobDescription(new JobDescription(jobDescription));
        return this;
    }

    /**
     * Sets the {@code InterviewDate} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withInterviewDate(String interviewDate) {
        descriptor.setInterviewDate(new InterviewDate(interviewDate));
        return this;
    }

    /**
     * Sets the {@code InternDuration} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withInternDuration(String internDuration) {
        descriptor.setInternDuration(new InternDuration(internDuration));
        return this;
    }

    /**
     * Sets the {@code Salary} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withSalary(String salary) {
        descriptor.setSalary(new Salary(salary));
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
