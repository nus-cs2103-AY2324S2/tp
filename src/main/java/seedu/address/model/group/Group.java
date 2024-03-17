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
        //    TODO: implement
        return false;
    }

    /**
     * Returns true if both groups have the same ??? todo
     * This defines a stronger notion of equality between two groups.
     */
    @Override
    public boolean equals(Object other) {
        //    TODO: implement
        return false;
    }
}
