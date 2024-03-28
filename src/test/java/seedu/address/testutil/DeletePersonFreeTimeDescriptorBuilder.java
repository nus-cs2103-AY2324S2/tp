package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.DeleteTimeCommand.EditPersonDescriptor;
import seedu.address.model.person.Person;
import seedu.address.model.tag.FreeTimeTag;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class DeletePersonFreeTimeDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public DeletePersonFreeTimeDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public DeletePersonFreeTimeDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public DeletePersonFreeTimeDescriptorBuilder(Person person) {
        descriptor = new EditPersonDescriptor();
        descriptor.setTags(person.getTags());
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public DeletePersonFreeTimeDescriptorBuilder withFreeTimeTags(String... freeTimeTags) {
        Set<FreeTimeTag> tagSet = Stream.of(freeTimeTags).map(FreeTimeTag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
