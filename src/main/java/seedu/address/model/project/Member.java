package seedu.address.model.project;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.person.Name;

/**
 * Represents a Task of Project
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Member {

    // Identity fields
    private final Name memberName;

    private Task memberTask;

    /**
     * Constructs a new task object
     * @param name the task name
     */
    public Member(String name) {
        requireAllNonNull(name);
        this.memberName = new Name(name);
    }

    /**
     * Get the name of the task
     * @return
     */
    public Name getName() {
        return memberName;
    }

    /**
     * Returns true if both tasks have the same identity and data fields.
     * This defines a stronger notion of equality between two tasks.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Task)) {
            return false;
        }
        Member other = (Member) obj;
        return memberName.equals(other.memberName);
    }

    @Override
    public String toString() {
        return memberName.toString();
    }

}
