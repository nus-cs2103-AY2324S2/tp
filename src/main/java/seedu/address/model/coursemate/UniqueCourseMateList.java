package seedu.address.model.coursemate;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.coursemate.exceptions.DuplicateCourseMateException;
import seedu.address.model.coursemate.exceptions.CourseMateNotFoundException;

/**
 * A list of course mates that enforces uniqueness between its elements and does not allow nulls.
 * A courseMate is considered unique by comparing using {@code CourseMate#isSameCourseMate(CourseMate)}. As such, adding and updating of
 * course mates uses CourseMate#isSameCourseMate(CourseMate) for equality so as to ensure that the courseMate being added or updated is
 * unique in terms of identity in the UniqueCourseMateList. However, the removal of a courseMate uses CourseMate#equals(Object) so
 * as to ensure that the courseMate with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see CourseMate#isSameCourseMate(CourseMate)
 */
public class UniqueCourseMateList implements Iterable<CourseMate> {

    private final ObservableList<CourseMate> internalList = FXCollections.observableArrayList();
    private final ObservableList<CourseMate> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent courseMate as the given argument.
     */
    public boolean contains(CourseMate toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameCourseMate);
    }

    /**
     * Adds a courseMate to the list.
     * The courseMate must not already exist in the list.
     */
    public void add(CourseMate toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateCourseMateException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the courseMate {@code target} in the list with {@code editedCourseMate}.
     * {@code target} must exist in the list.
     * The courseMate identity of {@code editedCourseMate} must not be the same as another existing courseMate in the list.
     */
    public void setCourseMate(CourseMate target, CourseMate editedCourseMate) {
        requireAllNonNull(target, editedCourseMate);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new CourseMateNotFoundException();
        }

        if (!target.isSameCourseMate(editedCourseMate) && contains(editedCourseMate)) {
            throw new DuplicateCourseMateException();
        }

        internalList.set(index, editedCourseMate);
    }

    /**
     * Removes the equivalent courseMate from the list.
     * The courseMate must exist in the list.
     */
    public void remove(CourseMate toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new CourseMateNotFoundException();
        }
    }

    public void setCourseMates(UniqueCourseMateList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code courseMates}.
     * {@code courseMates} must not contain duplicate courseMates.
     */
    public void setCourseMates(List<CourseMate> courseMates) {
        requireAllNonNull(courseMates);
        if (!courseMatesAreUnique(courseMates)) {
            throw new DuplicateCourseMateException();
        }

        internalList.setAll(courseMates);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<CourseMate> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<CourseMate> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueCourseMateList)) {
            return false;
        }

        UniqueCourseMateList otherUniqueCourseMateList = (UniqueCourseMateList) other;
        return internalList.equals(otherUniqueCourseMateList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code courseMates} contains only unique courseMates.
     */
    private boolean courseMatesAreUnique(List<CourseMate> courseMates) {
        for (int i = 0; i < courseMates.size() - 1; i++) {
            for (int j = i + 1; j < courseMates.size(); j++) {
                if (courseMates.get(i).isSameCourseMate(courseMates.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
