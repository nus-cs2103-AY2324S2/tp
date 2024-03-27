package seedu.address.model.person.relationship;

import java.util.UUID;

/**
 * Represents a friends relationship between two persons.
 */
public class FriendsRelationship extends Relationship {
    /**
     * Creates a new FriendsRelationship with the given UUIDs of the two persons.
     *
     * @param person1 The UUID of the first person in the relationship.
     * @param person2 The UUID of the second person in the relationship.
     */
    public FriendsRelationship(UUID person1, UUID person2) {
        super(person1, person2, "friend");
    }
}
