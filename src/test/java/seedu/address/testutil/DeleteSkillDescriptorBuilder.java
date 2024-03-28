package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.DeleteSkillCommand;
import seedu.address.logic.commands.DeleteSkillCommand.DeleteSkillDescriptor;
import seedu.address.model.coursemate.CourseMate;
import seedu.address.model.skill.Skill;

/**
 * A utility class to help with building DeleteSkillDescriptor objects.
 */
public class DeleteSkillDescriptorBuilder {

    private DeleteSkillCommand.DeleteSkillDescriptor descriptor;

    public DeleteSkillDescriptorBuilder() {
        descriptor = new DeleteSkillDescriptor();
    }

    public DeleteSkillDescriptorBuilder(DeleteSkillCommand.DeleteSkillDescriptor descriptor) {
        this.descriptor = new DeleteSkillCommand.DeleteSkillDescriptor(descriptor);
    }

    /**
     * Returns an {@code DeleteSkillDescriptor} with fields containing {@code courseMate}'s details
     */
    public DeleteSkillDescriptorBuilder(CourseMate courseMate) {
        descriptor = new DeleteSkillCommand.DeleteSkillDescriptor();
        descriptor.setSkills(courseMate.getSkills());
    }

    /**
     * Parses the {@code skills} into a {@code Set<Skill>} and set it to the {@code DeleteSkillDescriptor}
     * that we are building.
     */
    public DeleteSkillDescriptorBuilder withSkills(String... skills) {
        Set<Skill> skillSet = Stream.of(skills).map(Skill::new).collect(Collectors.toSet());
        descriptor.setSkills(skillSet);
        return this;
    }

    public DeleteSkillCommand.DeleteSkillDescriptor build() {
        return descriptor;
    }
}
