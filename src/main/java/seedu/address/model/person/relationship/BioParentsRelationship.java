package seedu.address.model.person.relationship;

import java.util.UUID;

/**
 * Represents a parent-child relationship between two persons.
 */
public class BioParentsRelationship extends FamilyRelationship {

    /**
     * Creates a new BioParentsRelationship with the given UUIDs of the two persons.
     *
     * @param parentUuid The UUID of the parent in the relationship.
     * @param childUuid The UUID of the child in the relationship.
     */
    public BioParentsRelationship(UUID parentUuid, UUID childUuid) {
        super(parentUuid, childUuid, "bioparents", "parent", "child");
    }
}
