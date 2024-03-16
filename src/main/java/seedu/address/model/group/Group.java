package seedu.address.model.group;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.coursemate.CourseMate;
import seedu.address.model.coursemate.Name;


/**
 * Represents a Group in the group list.
 */
public class Group {

    // temporary
    private final Name name;
    private final Set<CourseMate> members = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Group(Name name, Set<CourseMate> members) {
        requireNonNull(name);
        this.name = name;
        this.members.addAll(members);
    }

    public Name getName() {
        return name;
    }

    /**
     * Returns an immutable coursemate set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<CourseMate> getMembers() {
        return Collections.unmodifiableSet(members);
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
