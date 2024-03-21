package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.language.ProgrammingLanguage;
import seedu.address.model.person.Address;
import seedu.address.model.person.CompanyName;
import seedu.address.model.person.Email;
import seedu.address.model.person.Info;
import seedu.address.model.person.InterviewTime;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Salary;
import seedu.address.model.tag.Tag;

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
        descriptor.setCompanyName(person.getCompanyName());
        descriptor.setName(person.getName());
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
        descriptor.setAddress(person.getAddress());
        descriptor.setDateTime(person.getDateTime());
        descriptor.setTags(person.getTags());
        descriptor.setProgrammingLanguages(person.getProgrammingLanguages());
    }

    /**
     * Sets the {@code CompanyName} of the {@code Person} that we are building.
     */
    public EditPersonDescriptorBuilder withCompanyName(String name) {
        descriptor.setCompanyName(new CompanyName(name));
        return this;
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
     * To add interview-time to the person object
     * @param dateTime input
     * @return the object
     */
    public EditPersonDescriptorBuilder withInterviewTime(String dateTime) {
        descriptor.setDateTime(new InterviewTime(dateTime));
        return this;
    }
    /**
     * Sets the {@code Salary} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withSalary(String salary) {
        descriptor.setSalary(new Salary(salary));
        return this;
    }

    /**
     * Sets the {@code Info} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withInfo(String info) {
        descriptor.setInfo(new Info(info));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Parses the {@code programmingLanguages} into a {@code Set<ProgrammingLanguage>} and
     * sets it to the {@code EditPersonDescriptorBuilder}.
     * @param programmingLanguages The programming languages to be set.
     * @return The updated {@code EditPersonDescriptorBuilder} object.
     */
    public EditPersonDescriptorBuilder withProgrammingLanguages(String... programmingLanguages) {
        Set<ProgrammingLanguage> programmingLanguageSet = Stream.of(programmingLanguages).map(ProgrammingLanguage::new)
                .collect(Collectors.toSet());
        descriptor.setProgrammingLanguages(programmingLanguageSet);
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }

}
