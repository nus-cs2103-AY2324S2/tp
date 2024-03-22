package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a staff in the hackathon.
 */
public class Staff extends Person {
    private Group group;
    /**
     * Constructs staff. Every field must be present and not null.
     */
    public Staff(Name name, Phone phone, Email email, Address address, Category category, Set<Tag> tags) {
        super(name, phone, email, address, category, tags);
        this.group = new Group();
    }

    /**
     * Sets the group number of the staff.
     */
    public void setGroupNumber(int groupNumber) {
        this.group = new Group(groupNumber);
    }

    /**
     * Returns the group number of the staff.
     */
    public int getGroupNumber() {
        return group.getGroupNumber();
    }
    @Override
    public boolean isSamePerson(Person other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Staff)) {
            return false;
        }

        Staff otherPerson = (Staff) other;
        boolean isSameGroup = otherPerson.getGroupNumber() == this.getGroupNumber();
        return super.isSamePerson(otherPerson) && isSameGroup;
    }
}
