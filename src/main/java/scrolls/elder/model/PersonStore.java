package scrolls.elder.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import scrolls.elder.commons.util.ToStringBuilder;
import scrolls.elder.model.person.Person;
import scrolls.elder.model.person.PersonFactory;
import scrolls.elder.model.person.UniquePersonList;

/**
 * Wraps all data for all persons stored.
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class PersonStore implements ReadOnlyPersonStore {

    private static final Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    private final UniquePersonList persons;
    private int personIdSequence;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Person> filteredVolunteers;
    private final FilteredList<Person> filteredBefriendees;
    /**
     * Creates an empty PersonStore.
     */
    public PersonStore() {
        this.personIdSequence = 0;
        this.persons = new UniquePersonList();

        ObservableList<Person> personList = persons.asUnmodifiableObservableList();
        filteredPersons = new FilteredList<>(personList);
        filteredVolunteers = new FilteredList<>(personList, person -> person.isVolunteer());
        filteredBefriendees = new FilteredList<>(personList, person -> !(person.isVolunteer()));
    }

    /**
     * Creates a PersonStore using the data in the {@code toBeCopied}
     */
    public PersonStore(ReadOnlyPersonStore toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// Collection-level getters and setters

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public ObservableList<Person> getFilteredVolunteerList() {
        return filteredVolunteers;
    }

    @Override
    public ObservableList<Person> getFilteredBefriendeeList() {
        return filteredBefriendees;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
        filteredVolunteers.setPredicate(person -> predicate.test(person) && person.isVolunteer());
        filteredBefriendees.setPredicate(person -> predicate.test(person) && person.isBefriendee());
    }

    @Override
    public void updateFilteredVolunteerList(Predicate<Person> predicate) {
        requireNonNull(predicate);

        // Only apply predicate to volunteers
        filteredPersons.setPredicate(person -> person.isBefriendee() || predicate.test(person));
        filteredVolunteers.setPredicate(person -> predicate.test(person) && person.isVolunteer());
    }

    @Override
    public void updateFilteredBefriendeeList(Predicate<Person> predicate) {
        requireNonNull(predicate);

        // Only apply predicate to befriendees
        filteredPersons.setPredicate(person -> predicate.test(person) || person.isVolunteer());
        filteredBefriendees.setPredicate(person -> predicate.test(person) && person.isBefriendee());
    }



    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersonList(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Resets the existing data of this {@code PersonStore} with {@code newData}.
     * Implicitly sets the {@code personIdSequence} to the maximum ID in the new data plus one.
     */
    public void resetData(ReadOnlyPersonStore newData) {
        requireNonNull(newData);

        this.personIdSequence = newData.getPersonList()
                .stream()
                .map(Person::getPersonId)
                .max(Integer::compare)
                .map(max -> max + 1)
                .orElse(0);
        setPersonList(newData.getPersonList());
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    //// Person-level CRUD operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the store.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Returns the person with the given ID.
     */
    public Person getPersonFromID(int id) {
        return persons.getPersonFromID(id);
    }

    /**
     * Adds a person to the store.
     * The person must not already exist in the store.
     */
    public void addPerson(Person p) {
        Person withId = PersonFactory.withIdFromPerson(personIdSequence, p);
        persons.add(withId);
        personIdSequence++;
    }

    /**
     * Adds an existing person to the store.
     * For the case where existing persons are read from storage.
     */
    public void addPersonWithId(Person p) {
        persons.add(p);
        if (p.getPersonId() >= personIdSequence) {
            personIdSequence = p.getPersonId() + 1;
        }
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);
        Person withId = PersonFactory.withIdFromPerson(target.getPersonId(), editedPerson);
        persons.setPerson(target, withId);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// Util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("persons", persons)
                .toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonStore)) {
            return false;
        }

        PersonStore otherPersonStore = (PersonStore) other;
        return persons.equals(otherPersonStore.persons);
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
