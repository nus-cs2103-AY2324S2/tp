package seedu.address.model.person.relationship;

import java.util.UUID;
public interface RelationshipUtil {
    public void addRelationship(Relationship toAdd);
    public void deleteRelationship(Relationship toDelete);
    public boolean hasRelationship(Relationship toFind);
    String getExistingRelationship(Relationship toGet);

    //    public String getRelationshipPath(UUID origin, UUID target);
}
