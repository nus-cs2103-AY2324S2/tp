package seedu.address.model.group;

import static java.util.Objects.requireNonNull;

import seedu.address.model.coursemate.CourseMate;
import seedu.address.model.coursemate.Name;
import seedu.address.model.coursemate.UniqueCourseMateList;

/**
 * Represents a Group in the group list. All its members must be unique.
 */
public class Group extends UniqueCourseMateList {

    private final Name name;

    /**
     * A basic constructor for a group that also initializes an iterable collection as a list of members.
     *
     * @param name the name of the group
     * @param members the Collection of members to initialize the group
     */
    public Group(Name name, Iterable<CourseMate> members) {
        super();
        requireNonNull(name);
        this.name = name;
        members.forEach(this::add);
    }

    /**
     * A basic constructor for a group.
     *
     * @param name the name of the group
     */
    public Group(Name name) {
        super();
        this.name = name;
    }

    public Name getName() {
        return name;
    }

    /**
     * Returns true if both groups have the same name.
     * This defines a weaker notion of equality between two groups.
     */
    public boolean isSameGroup(Group otherGroup) {
        return otherGroup.name.equals(name);
    }

    /**
     * Returns true if both groups have the same name and their members satisfy equality as well.
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

        return otherGroup.name.equals(name) && super.equals(other);
    }
}
