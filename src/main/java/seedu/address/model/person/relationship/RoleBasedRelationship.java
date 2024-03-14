package seedu.address.model.person.relationship;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Represents a relationship between two persons with roles.
 */
class RoleBasedRelationship extends Relationship {
    private static final int MAX_ROLES = 2;
    private Map<UUID, String> roles; // Map of person UUID to their role in the relationship

    public RoleBasedRelationship(UUID person1, UUID person2) {
        super(person1, person2);
        roles = new HashMap<>();
    }

    // Add role for a person
    public void addRole(UUID personUuid, String role) {
        if (roles.size() < MAX_ROLES) {
            roles.put(personUuid, role);
        } else {
            throw new IllegalStateException("Cannot add more than two roles to a relationship.");
        }
    }

    // Get role of a person
    public String getRole(UUID personUuid) {
        String role = roles.get(personUuid);
        if (role == null) {
            throw new IllegalArgumentException("UUID not found in the roles map.");
        }
        return role;
    }
}

