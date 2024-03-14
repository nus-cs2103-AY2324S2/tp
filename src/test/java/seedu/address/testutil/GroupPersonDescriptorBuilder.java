package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.GroupCommand;
import seedu.address.logic.commands.GroupCommand.GroupPersonDescriptor;

import seedu.address.model.group.Group;
import seedu.address.model.person.NusId;
import seedu.address.model.person.Person;
import seedu.address.model.person.Tag;

/**
 * A utility class to help with building GroupPersonDescriptor objects.
 */
public class GroupPersonDescriptorBuilder {

    private GroupPersonDescriptor descriptor;

    public GroupPersonDescriptorBuilder() {
        descriptor = new GroupCommand.GroupPersonDescriptor();
    }

    public GroupPersonDescriptorBuilder(GroupPersonDescriptor descriptor) {
        this.descriptor = new GroupPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code GroupPersonDescriptor} with fields containing {@code person}'s details
     */
    public GroupPersonDescriptorBuilder(Person person) {
        descriptor = new GroupPersonDescriptor();
        descriptor.setNusId(person.getNusId());
        descriptor.setTag(person.getTag());
        descriptor.setGroups(person.getGroups());
    }

    /**
     * Sets the {@code Name} of the {@code GroupPersonDescriptor} that we are building.
     */
    public GroupPersonDescriptorBuilder withNusId(String nusId) {
        descriptor.setNusId(new NusId(nusId));
        return this;
    }



    /**
     * Sets the {@code Tag} of the {@code GroupPersonDescriptor} that we are building.
     */
    public GroupPersonDescriptorBuilder withTag(String tag) {
        descriptor.setTag(new Tag(tag));
        return this;
    }

    /**
     * Parses the {@code groups} into a {@code Set<Group>} and set it to the {@code GroupPersonDescriptor}
     * that we are building.
     */
    public GroupPersonDescriptorBuilder withGroups(String... groups) {
        Set<Group> groupSet = Stream.of(groups).map(Group::new).collect(Collectors.toSet());
        descriptor.setGroups(groupSet);
        return this;
    }

    public GroupPersonDescriptor build() {
        return descriptor;
    }
}
