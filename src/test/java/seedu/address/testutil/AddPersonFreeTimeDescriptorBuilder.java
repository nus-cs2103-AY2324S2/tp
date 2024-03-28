package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddTimeCommand.EditPersonDescriptor;
import seedu.address.model.person.Person;
import seedu.address.model.tag.FreeTimeTag;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class AddPersonFreeTimeDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public AddPersonFreeTimeDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public AddPersonFreeTimeDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public AddPersonFreeTimeDescriptorBuilder(Person person) {
        descriptor = new EditPersonDescriptor();
        descriptor.setTags(person.getTags());
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public AddPersonFreeTimeDescriptorBuilder withFreeTimeTags(String... freeTimeTags) {
        Set<FreeTimeTag> tagSet = Stream.of(freeTimeTags).map(FreeTimeTag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
