package seedu.address.model.person.relationship;

import java.util.UUID;

/**
 * Represents a spouse relationship between two persons.
 */
public class SpousesRelationship extends FamilyRelationship {
    public SpousesRelationship(UUID person1, UUID person2) {
        super(person1, person2, "spouse", "spouse");
    }
}
