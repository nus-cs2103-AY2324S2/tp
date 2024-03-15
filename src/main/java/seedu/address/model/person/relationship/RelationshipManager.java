package seedu.address.model.person.relationship;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages all the relationships of a person
 */
public class RelationshipManager {
    private Map<String, List<Relationship>> relationships; //overall hashmap where string is the relationship type

    public RelationshipManager() {
        relationships = new HashMap<>();
    }

    public void addRelationship(String relationshipType, Relationship relationship) {
        relationships.computeIfAbsent(relationshipType, k -> new ArrayList<>()).add(relationship);
    }

    public void deleteRelationship(String relationshipType, Relationship relationship) {
        relationships.get(relationshipType).remove(relationship);
    }

    // Get relationships by type
    public List<Relationship> getRelationships(String relationshipType) {
        return relationships.getOrDefault(relationshipType, Collections.emptyList());
    }
}
