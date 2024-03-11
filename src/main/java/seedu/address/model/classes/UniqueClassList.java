package seedu.address.model.classes;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.classes.exceptions.ClassNotFoundException;
import seedu.address.model.classes.exceptions.DuplicateClassException;

/**
 * A list of classes that enforces uniqueness between its elements and does not allow nulls.
 * A class is considered unique by comparing using {@code Class#isSameClass(Class)}. As such, adding and updating of
 * classes uses Class#isSameClass(Class) for equality so as to ensure that the class being added or updated is
 * unique in terms of identity in the UniqueClassList. However, the removal of a class uses Class#equals(Object) so
 * as to ensure that the class with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see ClassList#isSameClass(ClassList)
 */
public class UniqueClassList implements Iterable<ClassList> {

    private final ObservableList<ClassList> internalList = FXCollections.observableArrayList();
    private final ObservableList<ClassList> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent class as the given argument.
     */
    public boolean contains(ClassList toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameClass);
    }

    /**
     * Adds a class to the list.
     * The class must not already exist in the list.
     */
    public void add(ClassList toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateClassException();
        }
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent class from the list.
     * The class must exist in the list.
     */
    public void remove(ClassList toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ClassNotFoundException();
        }
    }

    /**
     * Replaces the given class {@code target} in the list with {@code editedClass}.
     * {@code target} must exist in the address book.
     * The class identity of {@code editedClass} must not be the same as another existing class in the address book.
     */
    public void setClass(ClassList target, ClassList editedClass) {
        requireAllNonNull(target, editedClass);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ClassNotFoundException();
        }

        if (!target.isSameClass(editedClass) && contains(editedClass)) {
            throw new DuplicateClassException();
        }

        internalList.set(index, editedClass);
    }

    public void setClasses(UniqueClassList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code classes}.
     * {@code classes} must not contain duplicate classes.
     */
    public void setClasses(List<ClassList> classLists) {
        requireAllNonNull(classLists);
        if (!classesAreUnique(classLists)) {
            throw new DuplicateClassException();
        }

        internalList.setAll(classLists);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<ClassList> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<ClassList> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueClassList)) {
            return false;
        }

        UniqueClassList otherUniqueClassList = (UniqueClassList) other;
        return internalList.equals(otherUniqueClassList.internalList);
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
     * Returns true if {@code classes} contains only unique classes.
     */
    private boolean classesAreUnique(List<ClassList> classLists) {
        for (int i = 0; i < classLists.size() - 1; i++) {
            for (int j = i + 1; j < classLists.size(); j++) {
                if (classLists.get(i).isSameClass(classLists.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
