package seedu.address.model.person.relationship;

import java.util.UUID;

public class BioParentsRelationship extends RoleBasedRelationship {
    private static final String ROLE_PARENT = "parent";
    private static final String ROLE_CHILD = "child";

    public BioParentsRelationship(UUID parentUUID, UUID childUUID) {
        super(parentUUID, childUUID);
        addRole(parentUUID, ROLE_PARENT);
        addRole(childUUID, ROLE_CHILD);
    }
}
