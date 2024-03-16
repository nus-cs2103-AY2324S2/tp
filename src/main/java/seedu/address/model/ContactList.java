package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.coursemate.CourseMate;
import seedu.address.model.coursemate.Email;
import seedu.address.model.coursemate.Name;
import seedu.address.model.coursemate.Phone;
import seedu.address.model.coursemate.UniqueCourseMateList;
import seedu.address.model.group.Group;
import seedu.address.model.group.UniqueGroupList;
import seedu.address.model.skill.Skill;

/**
 * Wraps all data at the contact list level
 * Duplicates are not allowed (by .isSameCourseMate comparison)
 */
public class ContactList implements ReadOnlyContactList {

    private final UniqueCourseMateList courseMates;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        courseMates = new UniqueCourseMateList();
    }

    /*
     * Temporary measure
     * TODO: Could possibly move to something like GroupList
     */
    private final UniqueGroupList groups = new UniqueGroupList();


    public ContactList() {}

    /**
     * Creates an ContactList using the Course mates in the {@code toBeCopied}
     */
    public ContactList(ReadOnlyContactList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the courseMate list with {@code courseMates}.
     * {@code courseMates} must not contain duplicate courseMates.
     */
    public void setCourseMates(List<CourseMate> courseMates) {
        this.courseMates.setCourseMates(courseMates);
    }

    /**
     * Replaces the contents of the group list with {@code groups}.
     * {@code groups} must not contain duplicate groups.
     */
    public void setGroups(List<Group> groups) {
        this.groups.setGroups(groups);
    }

    /**
     * Resets the existing data of this {@code ContactList} with {@code newData}.
     */
    public void resetData(ReadOnlyContactList newData) {
        requireNonNull(newData);

        setCourseMates(newData.getCourseMateList());

        // For now initialize groups with hard-coded data

        CourseMate member1 = new CourseMate(
                new Name("Member1"), new Phone("0123"), new Email("Member1@example.com"),
                Set.of(new Skill("skill1")));
        CourseMate member2 = new CourseMate(
                new Name("Member2"), new Phone("0123"), new Email("Member2@example.com"),
                Set.of(new Skill("skill1")));
        CourseMate member3 = new CourseMate(
                new Name("Member3"), new Phone("0123"), new Email("Member3@example.com"),
                Set.of(new Skill("skill1")));
        CourseMate member4 = new CourseMate(
                new Name("Member4"), new Phone("0123"), new Email("Member4@example.com"),
                Set.of(new Skill("skill1")));

        setGroups(List.of(
                new Group(new Name("Group 1"), Set.of(member1, member2, member3, member4)),
                new Group(new Name("Group 2"), Set.of(member1, member2)),
                new Group(new Name("Group 3"), Set.of(member1, member2, member3)),
                new Group(new Name("Group 4"), Set.of(member2, member3, member4)),
                new Group(new Name("Group 5"), Set.of(member2, member3))));
        // TODO: replace with this
        // setGroups(newData.getGroupList());
    }

    //// courseMate-level operations

    /**
     * Returns true if a courseMate with the same identity as {@code courseMate} exists in the contact list.
     */
    public boolean hasCourseMate(CourseMate courseMate) {
        requireNonNull(courseMate);
        return courseMates.contains(courseMate);
    }

    /**
     * Adds a courseMate to the contact list.
     * The courseMate must not already exist in the contact list.
     */
    public void addCourseMate(CourseMate p) {
        courseMates.add(p);
    }

    /**
     * Replaces the given courseMate {@code target} in the list with {@code editedCourseMate}.
     * {@code target} must exist in the contact list.
     * The courseMate identity of {@code editedCourseMate} must not be the same as
     * another existing courseMate in the contact list.
     */
    public void setCourseMate(CourseMate target, CourseMate editedCourseMate) {
        requireNonNull(editedCourseMate);

        courseMates.setCourseMate(target, editedCourseMate);
    }

    /**
     * Removes {@code key} from this {@code ContactList}.
     * {@code key} must exist in the contact list.
     */
    public void removeCourseMate(CourseMate key) {
        courseMates.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("courseMates", courseMates)
                .toString();
    }

    @Override
    public ObservableList<CourseMate> getCourseMateList() {
        return courseMates.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Group> getGroupList() {
        return groups.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ContactList)) {
            return false;
        }

        ContactList otherContactList = (ContactList) other;
        return courseMates.equals(otherContactList.courseMates);
    }

    @Override
    public int hashCode() {
        return courseMates.hashCode();
    }
}
