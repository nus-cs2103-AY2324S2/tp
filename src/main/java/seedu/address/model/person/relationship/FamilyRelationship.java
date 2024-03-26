package seedu.address.model.person.relationship;

import java.util.UUID;

/**
 * Represents a family relationship between two persons with predefined roles.
 */
public class FamilyRelationship extends RoleBasedRelationship {
    public FamilyRelationship(UUID person1, UUID person2, String role1, String role2) {
        super(person1, person2, "family", role1, role2);
    }
}

