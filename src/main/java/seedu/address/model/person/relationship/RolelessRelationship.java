package seedu.address.model.person.relationship;

import java.util.UUID;

class RolelessRelationship extends Relationship {
    public RolelessRelationship(UUID person1, UUID person2) {
        super(person1, person2);
    }
}
