package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.MarkImportantCommand.MarkImportantDescriptor;
import seedu.address.model.group.Group;
import seedu.address.model.skill.Skill;

/**
 * A utility class to help with building MarkImportantDescriptor objects
 */
public class MarkImportantDescriptorBuilder {

    private MarkImportantDescriptor descriptor;

    public MarkImportantDescriptorBuilder() {
        descriptor = new MarkImportantDescriptor();
    }

    public MarkImportantDescriptorBuilder(MarkImportantDescriptor descriptor) {
        this.descriptor = new MarkImportantDescriptor(descriptor);
    }

    /**
     * Returns an {@code MarkImportantDescriptor} with fields containing {@code group}'s skills
     */
    public MarkImportantDescriptorBuilder(Group group) {
        descriptor = new MarkImportantDescriptor();
        descriptor.setSkills(group.getSkills());
    }

    /**
     * Parses the {@code skills} into a {@code Set<Skill>} and set it to the {@code AddSkillDescriptor}
     * that we are building.
     */
    public MarkImportantDescriptorBuilder withSkills(String... skills) {
        Set<Skill> skillSet = Stream.of(skills).map(Skill::new).collect(Collectors.toSet());
        descriptor.setSkills(skillSet);
        return this;
    }

    public MarkImportantDescriptor build() {
        return descriptor;
    }
}
