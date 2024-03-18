package seedu.address.model.skill;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import seedu.address.model.skill.Skill;
import seedu.address.model.skill.exceptions.SkillNotFoundException;

public class SkillList {
    public final HashMap<Skill, Integer> skillMap;

    public boolean add(Skill toAdd) {
        requireNonNull(toAdd);
        boolean newSkill = false;
        if (!skillMap.containsKey(toAdd)) {
            newSkill = true;
        }
        skillMap.put(toAdd, skillMap.get(toAdd) + 1);
        return newSkill;
    }

    public void remove(Skill toRemove) {
        requireNonNull(toRemove);
        if (!skillMap.containsKey(toRemove)) {
            throw new SkillNotFoundException();
        }
        skillMap.put(toRemove, skillMap.get(toRemove) - 1);
        if (skillMap.get(toRemove) == 0) {
            skillMap.remove(toRemove);
        }
        return;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SkillList)) {
            return false;
        }

        SkillList otherSkillList = (SkillList) other;
        return skillMap.equals(otherSkillList.skillMap);
    }

    @Override
    public String toString() {
        return skillMap.toString();
    }
}