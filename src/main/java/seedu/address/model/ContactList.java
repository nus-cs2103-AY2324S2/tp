package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.coursemate.CourseMate;
import seedu.address.model.coursemate.UniqueCourseMateList;

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
     * Resets the existing data of this {@code ContactList} with {@code newData}.
     */
    public void resetData(ReadOnlyContactList newData) {
        requireNonNull(newData);

        setCourseMates(newData.getCourseMateList());
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
     * The courseMate identity of {@code editedCourseMate} must not be the same as another existing courseMate in the contact list.
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
