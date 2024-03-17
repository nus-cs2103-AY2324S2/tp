package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Classes;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniqueClassList;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class ClassBook implements ReadOnlyClassBook {
//storage not considered yet, need to set up storage and copy info over
    private final UniqueClassList classList = new UniqueClassList();

    public ClassBook(ReadOnlyClassBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    public ClassBook() {}

    public void setClasses(List<Classes> classes) {
        this.classList.setClasses(classes);
    }

    public void resetData(ReadOnlyClassBook newData) {
        requireNonNull(newData);
        setClasses(newData.getClassList());
    }

    public boolean hasClass(Classes classes) {
        requireNonNull(classes);
        return classList.contains(classes);
    }

    public void createClass(Classes classes) {
        classList.add(classes);
    }
    public void setClass(Classes target, Classes editedClass) {
        requireNonNull(editedClass);
        classList.setClass(target, editedClass);
    }

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
