package scrolls.elder.model;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import scrolls.elder.model.person.Person;

/**
 * Unmodifiable view of the person store.
 */
public interface ReadOnlyPersonStore {
    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the filtered person list.
     * To update this view, see {@link #updateFilteredPersonList(Predicate)}
     */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns an unmodifiable view of the filtered volunteer list.
     * To update this view, see {@link #updateFilteredPersonList(Predicate)}
     */
    ObservableList<Person> getFilteredVolunteerList();

    /**
     * Returns an unmodifiable view of the filtered befriendee list.
     * To update this view, see {@link #updateFilteredPersonList(Predicate)}
     */
    ObservableList<Person> getFilteredBefriendeeList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);
}
