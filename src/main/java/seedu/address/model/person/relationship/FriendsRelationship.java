package seedu.address.model.person.relationship;

import java.util.UUID;

public class FriendsRelationship extends RolelessRelationship {
    public FriendsRelationship(UUID person1, UUID person2) {
        super(person1, person2);
    }
}
