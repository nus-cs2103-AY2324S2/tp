package seedu.address.model.person.relationship;

import java.util.UUID;

/**
 * Represents a parent-child relationship between two persons.
 */
public class BioParentsRelationship extends FamilyRelationship {
    public BioParentsRelationship(UUID parentUuid, UUID childUuid) {
        super(parentUuid, childUuid, "parent", "child");
    }
}
