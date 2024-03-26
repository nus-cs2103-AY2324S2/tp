package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Address;
import seedu.address.model.person.BankDetails;
import seedu.address.model.person.Name;
import seedu.address.model.person.PayRate;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Sex;
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
        descriptor.setFirstName(person.getFirstName());
        descriptor.setLastName(person.getLastName());
        descriptor.setPhone(person.getPhone());
        descriptor.setSex(person.getSex());
        descriptor.setPayRate(person.getPayRate());
        descriptor.setAddress(person.getAddress());
        descriptor.setTags(person.getTags());
        descriptor.setBankDetails(person.getBankDetails());
    }

    /**
     * Sets the {@code FirstName} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withFirstName(String name) {
        descriptor.setFirstName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code LastName} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withLastName(String name) {
        descriptor.setLastName(new Name(name));
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
     * Sets the {@code Sex} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withSex(String sex) {
        descriptor.setSex(new Sex(sex));
        return this;
    }

    /**
     * Sets the {@code EmploymentType} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPayRate(double payRate) {
        descriptor.setPayRate(new PayRate(payRate));
        return this;
    }

    /**
     * Sets the {@code BankDetails} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withBankDetails(String bankDetails) {
        descriptor.setBankDetails(new BankDetails(bankDetails));
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
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
