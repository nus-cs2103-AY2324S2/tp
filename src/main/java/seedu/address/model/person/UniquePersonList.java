package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

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
 * @see Person#isSamePerson(Person)
 */
public class UniquePersonList implements Iterable<Person> {

    private final ObservableList<Person> internalList = FXCollections.observableArrayList();
    private final ObservableList<Person> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Person toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(Person toAdd) {
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
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSamePerson(editedPerson) && contains(editedPerson)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedPerson);
    }
    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(Person toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    public void setPersons(UniquePersonList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        requireAllNonNull(persons);
        if (!personsAreUnique(persons)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(persons);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Person> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Person> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniquePersonList)) {
            return false;
        }

        UniquePersonList otherUniquePersonList = (UniquePersonList) other;
        return internalList.equals(otherUniquePersonList.internalList);
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
    private boolean personsAreUnique(List<Person> persons) {
        for (int i = 0; i < persons.size() - 1; i++) {
            for (int j = i + 1; j < persons.size(); j++) {
                if (persons.get(i).isSamePerson(persons.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Deletes an attribute from a person identified by UUID.
     * Iterates through the list of persons until the matching UUID is found, then deletes the specified attribute.
     *
     * @param uuid The UUID of the person from whom to delete the attribute.
     * @param attributeName The name of the attribute to delete.
     */
    public void deleteAttribute(String uuid, String attributeName) {
        for (Person person : internalList) {
            if (person.getUuid().toString().equals(uuid)) {
                person.deleteAttribute(attributeName);
                return;
            }
        }
    }

    /**
     * Retrieves a Person object by their UUID.
     * Searches through the internal list of persons for a match with the provided UUID.
     *
     * @param uuid The UUID of the person to retrieve.
     * @return The Person object if found, null otherwise.
     */
    public Person getPersonByUuid(UUID uuid) {
        for (Person person : internalList) {
            if (person.getUuid().equals(uuid)) {
                return person;
            }
        }
        return null;
    }

    /**
     * Finds the full UUID of a person based on the last few digits provided.
     * Searches each person's UUID for a match on the last four digits.
     *
     * @param digits The last few digits of the person's UUID.
     * @return The full UUID of the person if a match is found, null otherwise.
     */
    public UUID getFullUuid(String digits) {
        for (Person p : internalList) {
            String currentPersonUuid = p.getUuidString();
            int len = currentPersonUuid.length();
            String toMatch = currentPersonUuid.substring(len - 4);
            boolean isMatch = toMatch.equals(digits);
            if (isMatch) {
                return p.getUuid();
            }
        }
        return null;
    }

    /**
     * Checks if a person identified by UUID has a specified attribute.
     * Iterates through the list of persons to find a match for the UUID, then checks for the attribute.
     *
     * @param uuidString The UUID of the person as a string.
     * @param attributeName The name of the attribute to check for.
     * @return true if the person has the specified attribute, false otherwise.
     */
    public boolean hasAttribute(String uuidString, String attributeName) {
        for (Person person : internalList) {
            if (person.getUuidString().equals(uuidString)) {
                return person.hasAttribute(attributeName);
            }
        }
        return false;
    }
}
