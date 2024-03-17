package seedu.address.logic.relationship;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import seedu.address.model.person.Person;
import seedu.address.model.person.relationship.Relationship;
import seedu.address.model.person.relationship.RelationshipManager;

/**
 * Represents a command to delete a relationship between two persons.
 */
public class DeleteRelationshipCommand {
    private final Map<String, Person> personMap;
    private final RelationshipManager relationshipManager;

    /**
     * Creates a new DeleteRelationshipCommand with the given person map and relationship manager.
     *
     * @param personMap           A map linking UUID strings to Person objects.
     * @param relationshipManager The RelationshipManager instance managing the relationships.
     */
    public DeleteRelationshipCommand(Map<String, Person> personMap, RelationshipManager relationshipManager) {
        this.personMap = personMap;
        this.relationshipManager = relationshipManager;
    }

    /**
     * Parses and executes the given delete relationship command.
     *
     * @param command The delete relationship command to parse and execute.
     * @throws IllegalArgumentException If the command is invalid.
     */
    public void parseCommand(String command) {
        String[] commandParts = command.split("\\s+|,");

        if (commandParts.length != 4) {
            throw new IllegalArgumentException("Invalid command format");
        }

        String relationType = commandParts[1].substring(1);
        String uuid1 = matchUuid(commandParts[2]);
        String uuid2 = matchUuid(commandParts[3]);

        List<Relationship> relationships = relationshipManager.getRelationships(relationType);

        // Check if the relationship exists and remove it
        boolean relationshipFound = false;
        for (Relationship relationship : relationships) {
            UUID person1Uuid = relationship.getPerson1();
            UUID person2Uuid = relationship.getPerson2();
            if ((person1Uuid.equals(UUID.fromString(uuid1)) && person2Uuid.equals(UUID.fromString(uuid2)))
                || (person1Uuid.equals(UUID.fromString(uuid2)) && person2Uuid.equals(UUID.fromString(uuid1)))) {
                relationshipManager.deleteRelationship(relationType, relationship);
                return; // Exit early if relationship is found and deleted
            }
        }

        if (!relationshipFound) {
            throw new IllegalArgumentException("No relationship found.");
        }
    }

    private String matchUuid(String partialUuid) {
        for (String uuid : personMap.keySet()) {
            if (uuid.endsWith(partialUuid)) {
                return uuid;
            }
        }
        throw new IllegalArgumentException("No matching UUID found.");
    }
}


