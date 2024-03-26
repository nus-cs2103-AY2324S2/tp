package seedu.address.model.person.relationship;

import java.util.UUID;

/**
 * Represents a sibling relationship between two persons.
 */
public class SiblingRelationship extends FamilyRelationship {

    /**
     * Creates a new SiblingRelationship with the given UUIDs of the two persons.
     *
     * @param person1 The UUID of the first person in the relationship.
     * @param person2 The UUID of the second person in the relationship.
     */
    public SiblingRelationship(UUID person1, UUID person2) {
        super(person1, person2, "siblings", "sibling", "sibling");
    }
}
