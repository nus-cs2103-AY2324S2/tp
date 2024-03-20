package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Classes;
import seedu.address.model.person.UniqueClassList;

/**
 * Wraps all data at the class-book level.
 * Duplicates are not allowed.
 */
public class ClassBook implements ReadOnlyClassBook {
    //storage not considered yet, need to set up storage and copy info over
    private final UniqueClassList classList = new UniqueClassList();

    /**
     * Creates an ClassBook using the Classes in the {@code toBeCopied}
     */
    public ClassBook(ReadOnlyClassBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    public ClassBook() {}

    /**
     * Replaces the contents of the class list with {@code classes}.
     * {@code classes} must not contain duplicate classes.
     */
    public void setClasses(List<Classes> classes) {
        this.classList.setClasses(classes);
    }

    /**
     * Resets the existing data of this {@code ClassBook} with {@code newData}.
     */
    public void resetData(ReadOnlyClassBook newData) {
        requireNonNull(newData);
        setClasses(newData.getClassList());
    }

    /**
     * Returns true if a class that is the same as {@code classes} exists in the class book.
     */
    public boolean hasClass(Classes classes) {
        requireNonNull(classes);
        return classList.contains(classes);
    }

    /**
     * Creates a class in the class book.
     * The class must not already exist in the class book.
     */
    public void createClass(Classes classes) {
        classList.add(classes);
    }

    /**
     * Replaces the given classes {@code target} in the list with {@code editedClass}.
     * {@code target} must exist in the class book.
     * The class of {@code editedClass} must not be the same as another existing class in the class book.
     */
    public void setClass(Classes target, Classes editedClass) {
        requireNonNull(editedClass);
        classList.setClass(target, editedClass);
    }

    /**
     * Removes {@code classes} from this {@code classList}.
     * {@code classes} must exist in the class book.
     */
    public void removeClass(Classes classes) {
        classList.remove(classes);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("classes", classList)
                .toString();
    }

    @Override
    public ObservableList<Classes> getClassList() {
        return classList.asUnmodifiableObservableList();
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClassBook)) {
            return false;
        }

        ClassBook otherClassBook = (ClassBook) other;
        return classList.equals(otherClassBook.classList);
    }

    @Override
    public int hashCode() {
        return classList.hashCode();
    }
}
