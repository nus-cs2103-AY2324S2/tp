package seedu.address.model.person.relationship;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Manages all the relationships of a person
 */
public class RelationshipUtilManager implements RelationshipUtil {
    private ArrayList<Relationship> relationshipsTracker = new ArrayList<>();

    public void addRelationship(Relationship toAdd) {
        relationshipsTracker.add(toAdd);
    }
    public void deleteRelationship(Relationship toDelete) {
        relationshipsTracker.remove(toDelete);
    }
    public boolean hasRelationship(Relationship toFind) {
        return relationshipsTracker.contains(toFind);
    }
    @Override
    public String getExistingRelationship(Relationship toGet) {
        int index = relationshipsTracker.indexOf(toGet);
        return relationshipsTracker.get(index).toString();
    }
//    public String getRelationshipPath(UUID origin, UUID target) {
//        ArrayList<Relationship> currentFrontier =
//    }



}
