package seedu.address.model.person.relationship;

import java.util.ArrayList;

/**
 * Manages all the relationships of a person
 */
public class RelationshipUtil {
    private ArrayList<Relationship> relationshipsTracker = new ArrayList<>();

    public void addRelationship(Relationship toAdd) {
        relationshipsTracker.add(toAdd);
    }
    public void deleteRelationship(Relationship toDelete) {
        relationshipsTracker.remove(toDelete);
    }
    public boolean hasRelationship(Relationship toFind) {
        if (relationshipsTracker.isEmpty()) {
            return false;
        }
        return relationshipsTracker.contains(toFind);
    }

    public String getExistingRelationship(Relationship toGet) {
        int index = relationshipsTracker.indexOf(toGet);
        return relationshipsTracker.get(index).toString();
    }
//    public String getRelationshipPath(UUID origin, UUID target) {
//        ArrayList<Relationship> currentFrontier =
//    }



}
