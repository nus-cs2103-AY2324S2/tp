package seedu.address.model.person;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a Person's skills in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidSkills(String)}
 */
public class Skills {

    public static final String MESSAGE_CONSTRAINTS = "Skills should only contain alphanumeric characters and spaces";

    private final Set<String> skills;

    /**
     * Constructs a {@code Skills}.
     *
     * @param skills A valid set of skills.
     */
    public Skills(Set<String> skills) {
        this.skills = new HashSet<>(skills);
    }

    /**
     * Constructs a {@code Skills}.
     *
     * @param skills A valid string of skills.
     */
    public Skills(String skills) {
        this.skills = new HashSet<>();
        String[] skillsArray = skills.split(" ");
        Collections.addAll(this.skills, skillsArray);
    }

    public static boolean isValidSkills(String test) {
        return test.matches("\\p{Alnum}+");
    }

    public Set<String> getSkills() {
        return new HashSet<>(skills);
    }

    public void addSkills(Set<String> skills) {
        this.skills.addAll(skills);
    }

    public void removeSkill(String skill) {
        skills.remove(skill);
    }

    public boolean containsSkill(String skill) {
        return skills.contains(skill);
    }

    @Override
    public String toString() {
        return skills.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Skills)) {
            return false;
        }

        Skills otherSkills = (Skills) other;
        return otherSkills.getSkills().equals(getSkills());
    }

    @Override
    public int hashCode() {
        return skills.hashCode();
    }
}
