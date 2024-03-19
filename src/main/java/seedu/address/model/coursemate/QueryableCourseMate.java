package seedu.address.model.coursemate;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.model.coursemate.exceptions.IllegalQueryableParameterException;

/**
 * Class that can represent either an index or a name.
 * This is used to query a CourseMate from a list.
 */
public class QueryableCourseMate {
    private final Index index;
    private final Name name;

    /**
     * Basic constructor that takes an {@code Index} object.
     */
    public QueryableCourseMate(Index index) {
        requireNonNull(index);
        this.index = index;
        this.name = null;
    }

    /**
     * Basic constructor that takes an {@code Name} object.
     */
    public QueryableCourseMate(Name name) {
        requireNonNull(name);
        this.index = null;
        this.name = name;
    }

    /**
     * Attempts to access this object as an index.
     * Throws a {@code IllegalQueryableParameterException} if it can't be done.
     */
    public Index getIndex() throws IllegalQueryableParameterException {
        if (index == null) {
            throw new IllegalQueryableParameterException("Index");
        }
        return index;
    }

    /**
     * Attempts to access this object as a name.
     * Throws a {@code IllegalQueryableParameterException} if it can't be done.
     */
    public Name getName() throws IllegalQueryableParameterException {
        if (name == null) {
            throw new IllegalQueryableParameterException("Name");
        }
        return name;
    }

    /**
     * Checks if the value is a {@code Index}. Mutually exclusive with {@code isName}.
     */
    public boolean isIndex() {
        return index != null;
    }

    /**
     * Checks if the value is a {@code Name}. Mutually exclusive with {@code isIndex}.
     */
    public boolean isName() {
        return name != null;
    }

    @Override
    public String toString() {
        if (isName()) {
            return name.toString();
        } else {
            return "#" + index.getOneBased();
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof QueryableCourseMate)) {
            return false;
        }

        QueryableCourseMate otherQueryable = (QueryableCourseMate) other;
        if (isName() && otherQueryable.isName()) {
            return name.equals(otherQueryable.name);
        }
        if (isIndex() && otherQueryable.isIndex()) {
            return index.equals(otherQueryable.index);
        }
        return false;
    }

    @Override
    public int hashCode() {
        if (isName()) {
            return name.hashCode();
        } else {
            return index.hashCode();
        }
    }
}
