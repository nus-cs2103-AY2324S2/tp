package seedu.address.model.person;

import java.util.HashSet;
import java.util.Set;

public class Skills {

    private final Set<String> skills;

    public Skills(Set<String> skills) {
        this.skills = new HashSet<>(skills);
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
}
