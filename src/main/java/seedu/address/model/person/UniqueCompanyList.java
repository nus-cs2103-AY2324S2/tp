package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Person#isSamePerson(Person)}. As such, adding and updating of
 * persons uses Person#isSamePerson(Person) for equality so as to ensure that the person being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a person uses Person#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Company#isSameCompany(Company)
 */
public class UniqueCompanyList implements Iterable<Company> {

    private final ObservableList<Company> internalList = FXCollections.observableArrayList();
    private final ObservableList<Company> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Company toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameCompany);
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(Company toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
     */
    public void setPerson(Company target, Company editedCompany) {
        requireAllNonNull(target, editedCompany);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSameCompany(editedCompany) && contains(editedCompany)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedCompany);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(Company toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    public void setCompany(UniqueCompanyList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setCompany(List<Company> companies) {
        requireAllNonNull(companies);
        if (!companiesAreUnique(companies)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(companies);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Company> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Company> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueCompanyList)) {
            return false;
        }

        UniqueCompanyList otherUniqueCompanyList = (UniqueCompanyList) other;
        return internalList.equals(otherUniqueCompanyList.internalList);
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
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean companiesAreUnique(List<Company> companies) {
        for (int i = 0; i < companies.size() - 1; i++) {
            for (int j = i + 1; j < companies.size(); j++) {
                if (companies.get(i).isSameCompany(companies.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Sorts the list in alphabetical order.
     * Sort is non case sensitive.
     */
    public void sort() {
        Comparator<Company> compareByName = Comparator.comparing(company -> company.getName().fullName.toLowerCase());
        FXCollections.sort(internalList, compareByName);
    }
}
