package seedu.address.model.group;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.coursemate.CourseMate;
import seedu.address.model.coursemate.Name;
import seedu.address.model.coursemate.UniqueCourseMateList;
import seedu.address.model.skill.Skill;

/**
 * Represents a {@code Group} in the group list. All its members must be unique.
 */
public class Group extends UniqueCourseMateList {

    private final Name name;
    private Set<Skill> skills;

    /**
     * A basic constructor for a {@code Group} that also initializes an iterable collection as a list of members.
     */
    public Group(Name name, Iterable<CourseMate> members) {
        this(name, members, new HashSet<>());
    }

    /**
     * A basic constructor for a group.
     */
    public Group(Name name) {
        this(name, new HashSet<>(), new HashSet<>());
    }

    /**
     * A constructor for a {@code Group} that takes members and a list of skills.
     */
    public Group(Name name, Iterable<CourseMate> members, Iterable<Skill> skills) {
        super();
        requireAllNonNull(name, members, skills);
        this.name = name;
        members.forEach(this::add);

        this.skills = new HashSet<>();
        skills.forEach(this.skills::add);
    }

    public Name getName() {
        return name;
    }

    public Set<Skill> getSkills() {
        return Collections.unmodifiableSet(skills);
    }

    /**
     * Returns a set containing all the skills that have been fulfilled.
     * Fulfilled means that any one of the group member has a corresponding skill
     * that matches with a specific group skill
     */
    public Set<Skill> completedSkills() {
        Set<Skill> completed = new HashSet<>();
        for (Skill skill: skills) {
            if (isSkillFound(skill)) {
                completed.add(skill);
            }
        }
        return Collections.unmodifiableSet(completed);
    }

    /**
     * Returns a set containing all the skills that have are still unfulfilled.
     * Unfulfilled means that all the group members do not have a skill
     * that matches with a corresponding group skill
     */
    public Set<Skill> uncompletedSkills() {
        Set<Skill> uncompleted = new HashSet<>();
        for (Skill skill: skills) {
            if (!isSkillFound(skill)) {
                uncompleted.add(skill);
            }
        }
        return Collections.unmodifiableSet(uncompleted);
    }

    /**
     * Returns true if both groups have the same name.
     * This defines a weaker notion of equality between two groups.
     */
    public boolean isSameGroup(Group otherGroup) {
        return otherGroup.name.equals(name);
    }

    /**
     * Returns true if both groups have the same name, same members, and same skills.
     * This defines a stronger notion of equality between two groups.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Group)) {
            return false;
        }

        Group otherGroup = (Group) other;

        return otherGroup.name.equals(name) && super.equals(other) && skills.equals(otherGroup.skills);
    }

    @Override
    public String toString() {
        return "Name: " + name + ", " + super.toString();
    }

    /**
     * Generates a hashcode that satisfies: two objects being {@link #equals(Object)} imply two equal codes.
     */
    @Override
    public int hashCode() {
        return name.hashCode()
                ^ super.hashCode()
                ^ skills.hashCode();
    }
}
