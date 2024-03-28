package seedu.address.model.person.relationship;

import java.util.UUID;

/**
 * Represents a family relationship between two persons with predefined roles.
 */
public abstract class FamilyRelationship extends RoleBasedRelationship {
    /**
     * Creates a new FamilyRelationship with the given UUIDs of the two persons.
     *
     * @param person1 The UUID of the first person in the relationship.
     * @param person2 The UUID of the second person in the relationship.
     * @param relationshipType The type of family relationship.
     * @param role1 The role of the first person in the relationship.
     * @param role2 The role of the second person in the relationship.
     */
    public FamilyRelationship(UUID person1, UUID person2, String relationshipType, String role1, String role2) {
        super(person1, person2, relationshipType, role1, role2);
    }
}

