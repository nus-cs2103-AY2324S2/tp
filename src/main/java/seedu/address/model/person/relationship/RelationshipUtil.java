package seedu.address.model.person.relationship;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Represents a utility class for managing the relationships associated with a person.
 * Allows for adding, deleting, and checking for existing relationships.
 */
public class RelationshipUtil {
    private ArrayList<Relationship> relationshipsTracker = new ArrayList<>();

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

    public boolean hasRelationshipWithDescriptor(Relationship toFind) {
        for (Relationship relationship : relationshipsTracker) {
            if ((relationship.getPerson1().equals(toFind.getPerson1()) && relationship.getPerson2()
                    .equals(toFind.getPerson2())
                    || (relationship.getPerson1().equals(toFind.getPerson2()) && relationship.getPerson2().equals(toFind.getPerson1())))
                    && relationship.getRelationshipDescriptor().equals(toFind.getRelationshipDescriptor())) {
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
}
