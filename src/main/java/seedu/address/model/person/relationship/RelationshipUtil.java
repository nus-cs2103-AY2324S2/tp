package seedu.address.model.person.relationship;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Represents a utility class for managing the relationships associated with a person.
 * Allows for adding, deleting, and checking for existing relationships.
 */
public class RelationshipUtil {
    private final ObservableList<Relationship> relationshipsTracker = FXCollections.observableArrayList();
    private final ObservableList<Relationship> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(relationshipsTracker);

    /**
     * Adds a new relationship to the tracker.
     * @param toAdd The relationship to be added.
     */
    public void addRelationship(Relationship toAdd) {
        relationshipsTracker.add(toAdd);
    }

    /**
     * Deletes a specific relationship from the tracker.
     * @param toDelete The relationship to be deleted.
     */
    public void deleteRelationship(Relationship toDelete) {
        relationshipsTracker.remove(toDelete);
    }

    /**
     * Checks if a specific relationship exists in the tracker.
     * @param toFind The relationship to find.
     * @return true if the relationship exists, false otherwise.
     */
    public boolean hasRelationship(Relationship toFind) {
        return !relationshipsTracker.isEmpty() && relationshipsTracker.contains(toFind);
    }

    /**
     * Checks if a specific relationship exists in the tracker.
     * @param toFind The relationship to find.
     * @return true if the relationship exists, false otherwise.
     */
    public boolean hasRelationshipWithDescriptor(Relationship toFind) {
        for (Relationship relationship : relationshipsTracker) {
            if ((relationship.getPerson1().equals(toFind.getPerson1()) && relationship.getPerson2()
                    .equals(toFind.getPerson2()))
                    || (relationship.getPerson1().equals(toFind.getPerson2()) && relationship.getPerson2()
                    .equals(toFind.getPerson1()))) {
                if (relationship.getRelationshipDescriptor().equalsIgnoreCase(toFind.getRelationshipDescriptor())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if a relationship with a specific descriptor exists in the tracker.
     * @param descriptor The descriptor to find.
     * @return true if the relationship exists, false otherwise.
     */
    public static boolean descriptorExists(String descriptor) {
        for (Relationship relationship : relationshipsTracker) {
            if (relationship.getRelationshipDescriptor().equals(descriptor)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves a string representation of an existing relationship in the tracker.
     * @param toGet The relationship to retrieve.
     * @return A string representation of the specified relationship if it exists.
     * @throws IndexOutOfBoundsException if the relationship does not exist in the tracker.
     */
    public String getExistingRelationship(Relationship toGet) {
        for (Relationship relationship : relationshipsTracker) {
            if (relationship.equals(toGet)) {
                return relationship.toString();
            }
        }
        throw new IllegalArgumentException("Relationship does not exist.");
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Relationship> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Replaces the contents of this list with {@code relationships}.
     * {@code persons} must not contain duplicate relationships.
     */
    public void setRelationships(List<Relationship> relationships) {
        requireAllNonNull(relationships);
        relationshipsTracker.setAll(relationships);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RelationshipUtil)) {
            return false;
        }
        RelationshipUtil other = (RelationshipUtil) o;
        return relationshipsTracker.equals(other.relationshipsTracker);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Relationship relationship : relationshipsTracker) {
            sb.append(relationship.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
