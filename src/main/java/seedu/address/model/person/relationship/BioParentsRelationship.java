package seedu.address.model.person.relationship;

import java.util.UUID;

/**
 * Represents a parent-child relationship between two persons.
 */
public class BioParentsRelationship extends RoleBasedRelationship {
    private static final String ROLE_PARENT = "parent";
    private static final String ROLE_CHILD = "child";

    /**
     * Creates a new BioParentsRelationship between two persons.
     */
    public BioParentsRelationship(UUID parentUuid, UUID childUuid) {
        super(parentUuid, childUuid);
        addRole(parentUuid, ROLE_PARENT);
        addRole(childUuid, ROLE_CHILD);
    }
}