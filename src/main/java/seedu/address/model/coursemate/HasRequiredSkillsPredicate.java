package seedu.address.model.coursemate;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.model.skill.Skill;

/**
 * Tests that a {@code CourseMate}'s {@code Skill} matches any of the wanted skills given.
 * Also tests that a {@code CourseMate} is not in the specified {@code group}.
 */
public class HasRequiredSkillsPredicate implements Predicate<CourseMate> {
    public final List<CourseMate> memberList;
    public final Set<Skill> requiredSkills;

    /**
     * Public constructor for HasRequiredSkillsPredicate
     * @param memberList - The list of members to be rejected by the predicate
     * @param requiredSkills - The list of uncompleted skills needed by the group
     */
    public HasRequiredSkillsPredicate(List<CourseMate> memberList, Set<Skill> requiredSkills) {
        this.memberList = memberList;
        this.requiredSkills = requiredSkills;
    }

    @Override
    public boolean test(CourseMate courseMate) {
        // A coursemate already in the group should not display
        if (memberList.contains(courseMate)) {
            return false;
        }

        boolean hasSkill = false;
        Set<Skill> courseMateSkills = courseMate.getSkills();
        for (Skill skill : requiredSkills) {
            if (courseMateSkills.contains(skill)) {
                hasSkill = true;
            }
        }
        return hasSkill;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof HasRequiredSkillsPredicate)) {
            return false;
        }

        HasRequiredSkillsPredicate otherHasRequiredSkillsPredicate = (HasRequiredSkillsPredicate) other;
        return memberList.equals(otherHasRequiredSkillsPredicate.memberList)
                && requiredSkills.equals(otherHasRequiredSkillsPredicate.requiredSkills);
    }
}
