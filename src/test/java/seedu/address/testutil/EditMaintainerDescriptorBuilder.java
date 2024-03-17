package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditMaintainerCommand.EditMaintainerDescriptor;
import seedu.address.model.person.Address;
import seedu.address.model.person.Commission;
import seedu.address.model.person.Email;
import seedu.address.model.person.Maintainer;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Skill;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditMaintainerDescriptor objects.
 */
public class EditMaintainerDescriptorBuilder {

    private EditMaintainerDescriptor descriptor;

    public EditMaintainerDescriptorBuilder() {
        descriptor = new EditMaintainerDescriptor();
    }

    public EditMaintainerDescriptorBuilder(EditMaintainerDescriptor descriptor) {
        this.descriptor = new EditMaintainerDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditMaintainerDescriptor} with fields containing {@code maintainer}'s details
     */
    public EditMaintainerDescriptorBuilder(Maintainer maintainer) {
        descriptor = new EditMaintainerDescriptor();
        descriptor.setName(maintainer.getName());
        descriptor.setPhone(maintainer.getPhone());
        descriptor.setEmail(maintainer.getEmail());
        descriptor.setAddress(maintainer.getAddress());
        descriptor.setSkill(maintainer.getSkill());
        descriptor.setCommission(maintainer.getCommission());
        descriptor.setTags(maintainer.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditMaintainerDescriptor} that we are building.
     */
    public EditMaintainerDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditMaintainerDescriptor} that we are building.
     */
    public EditMaintainerDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditMaintainerDescriptor} that we are building.
     */
    public EditMaintainerDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditMaintainerDescriptor} that we are building.
     */
    public EditMaintainerDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code Skill} of the {@code EditMaintainerDescriptor} that we are building.
     */
    public EditMaintainerDescriptorBuilder withSkill(String skill) {
        descriptor.setSkill(new Skill(skill));
        return this;
    }

    /**
     * Sets the {@code Commission} of the {@code EditMaintainerDescriptor} that we are building.
     */
    public EditMaintainerDescriptorBuilder withCommission(String commission) {
        descriptor.setCommission(new Commission(commission));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditMaintainerDescriptor}
     * that we are building.
     */
    public EditMaintainerDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditMaintainerDescriptor build() {
        return descriptor;
    }
}
