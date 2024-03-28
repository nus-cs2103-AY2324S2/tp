package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.AssignCommand;
import seedu.address.logic.commands.AssignCommand.AssignPersonDescriptor;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building AssignPersonDescriptor objects.
 */
public class AssignPersonDescriptorBuilder {

    private AssignPersonDescriptor descriptor;

    public AssignPersonDescriptorBuilder() {
        this.descriptor = new AssignPersonDescriptor();
    }

    public AssignPersonDescriptorBuilder(AssignCommand.AssignPersonDescriptor descriptor) {
        this.descriptor = new AssignCommand.AssignPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code AssignPersonDescriptor} with fields containing {@code person}'s details
     */
    public AssignPersonDescriptorBuilder(String role) {
        descriptor = new AssignPersonDescriptor();
        Set<Tag> tagSet = Stream.of(role).map(Tag::new).collect(Collectors.toSet());
        descriptor.setRole(tagSet);
    }

    public AssignCommand.AssignPersonDescriptor build() {
        return descriptor;
    }
}
