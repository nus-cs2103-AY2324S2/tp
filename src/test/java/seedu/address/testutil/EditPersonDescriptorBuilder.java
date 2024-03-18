package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditCompanyDescriptor;
import seedu.address.model.person.Company;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditCompanyDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditCompanyDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditCompanyDescriptor();
    }

    public EditPersonDescriptorBuilder(EditCompanyDescriptor descriptor) {
        this.descriptor = new EditCompanyDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditCompanyDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Company company) {
        descriptor = new EditCompanyDescriptor();
        descriptor.setName(company.getName());
        descriptor.setPhone(company.getPhone());
        descriptor.setEmail(company.getEmail());
        descriptor.setTags(company.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditCompanyDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditCompanyDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditCompanyDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }




    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditCompanyDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditCompanyDescriptor build() {
        return descriptor;
    }
}
