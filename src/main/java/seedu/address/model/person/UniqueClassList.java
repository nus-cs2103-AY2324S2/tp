package seedu.address.model.person;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class UniqueClassList implements Iterable<Classes> {
    private final ObservableList<Classes> internalList = FXCollections.observableArrayList();
    private final ObservableList<Classes> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    public boolean contains(Classes toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameClass);
    }
    @Override
    public Iterator<Classes> iterator() {
        return internalList.iterator();
    }

    /**
     * Adds a class to list.
     */
    public void add(Classes toAdd) {
        requireNonNull(toAdd);
//        if (contains(toAdd)) {
//            throw new DuplicateClassException()
//        }
        internalList.add(toAdd);
    }


    public void setClass(Classes target, Classes editedClass) {
        requireAllNonNull(target, editedClass);

        int index = internalList.indexOf(target);
//        if (index == -1) {
//            throw new CcNotFoundException()
//        }
//        if (!target.isSamePerson(editedClass) && contains(editedClass) {
//              throw new DuplicateClassException();
//        }

        internalList.set(index, editedClass);
    }

    public void setClasses(UniqueClassList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    public void setClasses(List<Classes> classes) {
        requireAllNonNull(classes);
//        if (!classesAreUnique(classes)) {
//            throw new DuplicateClassException();
//        }

        internalList.setAll(classes);
    }

    /**
     * Removes the class from the list.
     */
    public void remove(Classes toRemove) {
        requireNonNull(toRemove);
        internalList.remove(toRemove);
    }

    public ObservableList<Classes> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
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

    private boolean classesAreUnique(List<Classes> classes) {
        for (int i = 0; i < classes.size() - 1; i++) {
            for (int j = i + 1; j < classes.size(); j++) {
                if (classes.get(i).isSameClass(classes.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
