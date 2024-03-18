package seedu.address.model.startup;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.startup.exceptions.DuplicateStartupException;
import seedu.address.model.startup.exceptions.StartupNotFoundException;

/**
 * A list of startups that enforces uniqueness between its elements and does not allow nulls.
 * A startup is considered unique by comparing using {@code Startup#isSameStartup(Startup)}. As such, adding and
 * updating of startups uses Startup#isSameStartup(Startup) for equality so as to ensure that the startup being added
 * or updated is unique in terms of identity in the UniqueStartupList. However, the removal of a startup uses
 * Startup#equals(Object) so as to ensure that the startup with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Startup#isSameStartup(Startup)
 */
public class UniqueStartupList implements Iterable<Startup> {

    private final ObservableList<Startup> internalList = FXCollections.observableArrayList();
    private final ObservableList<Startup> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent startup as the given argument.
     */
    public boolean contains(Startup toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameStartup);
    }

    /**
     * Adds a startup to the list.
     * The startup must not already exist in the list.
     */
    public void add(Startup toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateStartupException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the startup {@code target} in the list with {@code editedStartup}.
     * {@code target} must exist in the list.
     * The startup identity of {@code editedStartup} must not be the same as another existing startup in the list.
     */
    public void setStartup(Startup target, Startup editedStartup) {
        requireAllNonNull(target, editedStartup);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new StartupNotFoundException();
        }

        if (!target.isSameStartup(editedStartup) && contains(editedStartup)) {
            throw new DuplicateStartupException();
        }

        internalList.set(index, editedStartup);
    }

    /**
     * Removes the equivalent startup from the list.
     * The startup must exist in the list.
     */
    public void remove(Startup toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new StartupNotFoundException();
        }
    }

    public void setStartups(UniqueStartupList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code startups}.
     * {@code startups} must not contain duplicate startups.
     */
    public void setStartups(List<Startup> startups) {
        requireAllNonNull(startups);
        if (!startupsAreUnique(startups)) {
            throw new DuplicateStartupException();
        }

        internalList.setAll(startups);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Startup> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Startup> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueStartupList)) {
            return false;
        }

        UniqueStartupList otherUniqueStartupList = (UniqueStartupList) other;
        return internalList.equals(otherUniqueStartupList.internalList);
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
     * Returns true if {@code startups} contains only unique startups.
     */
    private boolean startupsAreUnique(List<Startup> startups) {
        for (int i = 0; i < startups.size() - 1; i++) {
            for (int j = i + 1; j < startups.size(); j++) {
                if (startups.get(i).isSameStartup(startups.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
