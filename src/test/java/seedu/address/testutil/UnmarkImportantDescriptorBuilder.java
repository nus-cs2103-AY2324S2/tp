package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.UnmarkImportantCommand.UnmarkImportantDescriptor;
import seedu.address.model.group.Group;
import seedu.address.model.skill.Skill;

/**
 * A utility class to help with building MarkImportantDescriptor objects
 */
public class UnmarkImportantDescriptorBuilder {

    private UnmarkImportantDescriptor descriptor;

    public UnmarkImportantDescriptorBuilder() {
        descriptor = new UnmarkImportantDescriptor();
    }

    public UnmarkImportantDescriptorBuilder(UnmarkImportantDescriptor descriptor) {
        this.descriptor = new UnmarkImportantDescriptor(descriptor);
    }

    /**
     * Returns an {@code MarkImportantDescriptor} with fields containing {@code group}'s skills
     */
    public UnmarkImportantDescriptorBuilder(Group group) {
        descriptor = new UnmarkImportantDescriptor();
        descriptor.setSkills(group.getSkills());
    }

    /**
     * Parses the {@code skills} into a {@code Set<Skill>} and set it to the {@code AddSkillDescriptor}
     * that we are building.
     */
    public UnmarkImportantDescriptorBuilder withSkills(String... skills) {
        Set<Skill> skillSet = Stream.of(skills).map(Skill::new).collect(Collectors.toSet());
        descriptor.setSkills(skillSet);
        return this;
    }

    public UnmarkImportantDescriptor build() {
        return descriptor;
    }
}
