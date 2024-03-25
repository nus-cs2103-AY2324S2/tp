package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditStartupDescriptor;
import seedu.address.model.startup.Address;
import seedu.address.model.startup.Email;
import seedu.address.model.startup.FundingStage;
import seedu.address.model.startup.Industry;
import seedu.address.model.startup.Name;
import seedu.address.model.startup.Phone;
import seedu.address.model.startup.Startup;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditStartupDescriptor objects.
 */
public class EditStartupDescriptorBuilder {

    private EditStartupDescriptor descriptor;

    public EditStartupDescriptorBuilder() {
        descriptor = new EditStartupDescriptor();
    }

    public EditStartupDescriptorBuilder(EditStartupDescriptor descriptor) {
        this.descriptor = new EditStartupDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditStartupDescriptor} with fields containing {@code startup}'s details
     */
    public EditStartupDescriptorBuilder(Startup startup) {
        descriptor = new EditStartupDescriptor();
        descriptor.setName(startup.getName());
        descriptor.setIndustry(startup.getIndustry());
        descriptor.setFundingStage(startup.getFundingStage());
        descriptor.setPhone(startup.getPhone());
        descriptor.setEmail(startup.getEmail());
        descriptor.setAddress(startup.getAddress());
        descriptor.setTags(startup.getTags());

    }

    /**
     * Sets the {@code Name} of the {@code EditStartupDescriptor} that we are building.
     */
    public EditStartupDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code FundingStage} of the {@code EditStartupDescriptor} that we are building.
     */
    public EditStartupDescriptorBuilder withFundingStage(String fundingLevel) {
        descriptor.setFundingStage(new FundingStage(fundingLevel));
        return this;
    }

    /**
     * Sets the {@code Industry} of the {@code EditStartupDescriptor} that we are building.
     */
    public EditStartupDescriptorBuilder withIndustry(String industry) {
        descriptor.setIndustry(new Industry(industry));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditStartupDescriptor} that we are building.
     */
    public EditStartupDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditStartupDescriptor} that we are building.
     */
    public EditStartupDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditStartupDescriptor} that we are building.
     */
    public EditStartupDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditStartupDescriptor}
     * that we are building.
     */
    public EditStartupDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditStartupDescriptor build() {
        return descriptor;
    }
}
