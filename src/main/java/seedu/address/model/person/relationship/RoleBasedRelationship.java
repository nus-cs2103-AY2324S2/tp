package seedu.address.model.person.relationship;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Represents a relationship between two persons with roles.
 */
public class RoleBasedRelationship extends Relationship {
    private Map<UUID, String> roles = new HashMap<>(); // Map of person UUID to their role in the relationship

    /**
     * Creates a new RoleBasedRelationship with the given UUIDs of the two persons.
     *
     * @param person1 The UUID of the first person in the relationship.
     * @param person2 The UUID of the second person in the relationship.
     */
    public RoleBasedRelationship(UUID person1, UUID person2,
                                 String relationDescriptor, String rolePerson1, String rolePerson2) {
        super(person1, person2, relationDescriptor);
        roles.put(person1, rolePerson1);
        roles.put(person2, rolePerson2);
    }
    @Override
    public String getRoleDescriptor(UUID targetPerson) {
        if (!targetPerson.equals(super.getPerson1()) && !targetPerson.equals(super.getPerson2())) {
            throw new IllegalArgumentException("This person is not in this relationship");
        }
        return roles.get(targetPerson);
    }

    @Override
    public String toString() {
        return String.format("%s\n %s is %s, %s is %s",
                super.toString(), super.getPerson1().toString(), getRoleDescriptor(super.getPerson1()),
                super.getPerson2().toString(), getRoleDescriptor(super.getPerson2()));
    }
}

