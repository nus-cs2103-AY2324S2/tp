package seedu.address.model.person.relationship;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Represents a relationship between two persons with roles.
 */
public class RoleBasedRelationship extends Relationship {
    private static final int MAX_ROLES = 2;
    private Map<UUID, String> roles; // Map of person UUID to their role in the relationship

    /**
     * Creates a new RoleBasedRelationship with the given UUIDs of the two persons.
     *
     * @param person1 The UUID of the first person in the relationship.
     * @param person2 The UUID of the second person in the relationship.
     */
    public RoleBasedRelationship(UUID person1, UUID person2) {
        super(person1, person2);
        roles = new HashMap<>();
    }

    /**
     * Adds a role for the specified person in the relationship.
     *
     * @param personUuid The UUID of the person for whom the role is being added.
     * @param role       The role to be added for the person.
     * @throws IllegalStateException If attempting to add more than two roles to the relationship.
     */
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

