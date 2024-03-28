package seedu.address.model.person.relationship;

import java.util.UUID;

/**
 * Represents a spouse relationship between two persons.
 */
public class SpousesRelationship extends FamilyRelationship {

    /**
     * Creates a new SpousesRelationship with the given UUIDs of the two persons.
     *
     * @param person1 The UUID of the first person in the relationship.
     * @param person2 The UUID of the second person in the relationship.
     */
    public SpousesRelationship(UUID person1, UUID person2) {
        super(person1, person2, "spouses", "spouse", "spouse");
    }
}
