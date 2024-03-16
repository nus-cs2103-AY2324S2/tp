package seedu.address.model.group;

import seedu.address.model.coursemate.CourseMate;
import seedu.address.model.coursemate.Name;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a Group in the group list.
 */
public class Group {

    // temporary
    private final Name name;
    private final Set<CourseMate> members = new HashSet<>();

    public Group(Name name, Set<CourseMate> members) {
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
}