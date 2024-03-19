package seedu.address.model.skill;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Skill in the contact list.
 * Guarantees: immutable; name is valid as declared in {@link #isValidSkillName(String)}
 */
public class Skill {

    public final String skillName;

    /**
     * Constructs a {@code Skill}.
     *
     * @param skillName A valid skill name.
     */
    public Skill(String skillName) {
        requireNonNull(skillName);
        this.skillName = skillName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Skill)) {
            return false;
        }

        Skill otherSkill = (Skill) other;
        return skillName.equals(otherSkill.skillName);
    }

    @Override
    public int hashCode() {
        return skillName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + skillName + ']';
    }

}
