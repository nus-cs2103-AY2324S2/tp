package seedu.address.model.skill;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Skill in the contact list.
 * Guarantees: immutable;
 */
public class Skill {

    public final String skillName;
    private boolean important;

    /**
     * Constructs a {@code Skill}.
     *
     * @param skillName A valid skill name.
     */
    public Skill(String skillName) {
        requireNonNull(skillName);
        this.skillName = skillName;
        this.important = false;
    }

    /**
     * Constructs a {@code Skill} with importance
     * @param skillName A valid skill name
     * @param important Signifies if the skill is important
     */
    public Skill(String skillName, boolean important) {
        requireNonNull(skillName);
        this.skillName = skillName;
        this.important = important;
    }

    public String importantStringRepresentation() {
        return this.important ? "[!] " : "";
    }

    public boolean getImportant() {
        return this.important;
    }

    public void setImportant(boolean important) {
        this.important = important;
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
        return skillName.equalsIgnoreCase(otherSkill.skillName);
    }

    @Override
    public int hashCode() {
        return skillName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + skillName + ']' + importantStringRepresentation();
    }

}
