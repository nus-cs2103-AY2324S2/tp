package seedu.address.model.person.relationship;

import java.util.UUID;

public class SpousesRelationship extends RoleBasedRelationship {
    public SpousesRelationship(UUID person1, UUID person2) {
        super(person1, person2);
    }
}
