package seedu.address.testutil;

import seedu.address.model.ContactList;
import seedu.address.model.coursemate.CourseMate;

/**
 * A utility class to help with building ContactList objects.
 * Example usage: <br>
 *     {@code ContactList ab = new ContactListBuilder().withCourseMate("John", "Doe").build();}
 */
public class ContactListBuilder {

    private ContactList contactList;

    public ContactListBuilder() {
        contactList = new ContactList();
    }

    public ContactListBuilder(ContactList contactList) {
        this.contactList = contactList;
    }

    /**
     * Adds a new {@code CourseMate} to the {@code ContactList} that we are building.
     */
    public ContactListBuilder withCourseMate(CourseMate courseMate) {
        contactList.addCourseMate(courseMate);
        return this;
    }

    public ContactList build() {
        return contactList;
    }
}
