package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddSkillCommand;
import seedu.address.logic.commands.AddSkillCommand.AddSkillDescriptor;
import seedu.address.model.coursemate.CourseMate;
import seedu.address.model.skill.Skill;

/**
 * A utility class to help with building AddSkillDescriptor objects.
 */
public class AddSkillDescriptorBuilder {

    private AddSkillCommand.AddSkillDescriptor descriptor;

    public AddSkillDescriptorBuilder() {
        descriptor = new AddSkillDescriptor();
    }

    public AddSkillDescriptorBuilder(AddSkillCommand.AddSkillDescriptor descriptor) {
        this.descriptor = new AddSkillCommand.AddSkillDescriptor(descriptor);
    }

    /**
     * Returns an {@code AddSkillDescriptor} with fields containing {@code courseMate}'s details
     */
    public AddSkillDescriptorBuilder(CourseMate courseMate) {
        descriptor = new AddSkillCommand.AddSkillDescriptor();
        descriptor.setSkills(courseMate.getSkills());
    }

    /**
     * Parses the {@code skills} into a {@code Set<Skill>} and set it to the {@code AddSkillDescriptor}
     * that we are building.
     */
    public AddSkillDescriptorBuilder withSkills(String... skills) {
        Set<Skill> skillSet = Stream.of(skills).map(Skill::new).collect(Collectors.toSet());
        descriptor.setSkills(skillSet);
        return this;
    }

    public AddSkillCommand.AddSkillDescriptor build() {
        return descriptor;
    }
}
