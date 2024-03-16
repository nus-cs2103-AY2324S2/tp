package seedu.address.logic.relationship;

import java.util.Map;

import seedu.address.model.person.Person;
import seedu.address.model.person.relationship.BioParentsRelationship;
import seedu.address.model.person.relationship.FriendsRelationship;
import seedu.address.model.person.relationship.RelationshipManager;
import seedu.address.model.person.relationship.SpousesRelationship;

/**
 * This class is responsible for parsing and executing commands to add relationships between persons.
 * It supports adding relationships with and without roles.
 */
public class AddRelationshipCommand {
    private final Map<String, Person> personMap;
    private final RelationshipManager relationshipManager;

    /**
     * Creates a new AddRelationshipCommand with the given person map.
     *
     * @param personMap A map linking UUID strings to Person objects.
     */
    public AddRelationshipCommand(Map<String, Person> personMap) {
        this.personMap = personMap;
        this.relationshipManager = new RelationshipManager();
    }

    /**
     * Parses and executes the given command to add a relationship between persons.
     * Supports adding relationships with and without roles based on the command format.
     *
     * @param command The command to parse and execute.
     * @throws IllegalArgumentException If the command is invalid.
     */
    public void parseCommand(String command) {
        String[] commandParts = command.split("\\s+");
        if (commandParts.length < 4) {
            throw new IllegalArgumentException("Invalid command format");
        }

        if (commandParts[2].contains(",")) {
            handleAddRolelessRelationship(commandParts);
        } else {
            handleAddRoleBasedRelationship(commandParts);
        }
    }

    /**
     * Handles adding a relationship with roles based on the given command parts.
     *
     * @param commandParts The parts of the command to parse and execute
     * @throws IllegalArgumentException If the person cannot be found or the relationship type is invalid.
     */
    private void handleAddRoleBasedRelationship(String[] commandParts) {
        if (commandParts.length < 6) {
            throw new IllegalArgumentException("Invalid command format");
        }
        String relationType = commandParts[1].substring(1);
        String uuid1 = matchUuid(commandParts[3]);
        String uuid2 = matchUuid(commandParts[5]);

        Person person1 = personMap.get(uuid1);
        Person person2 = personMap.get(uuid2);
        if (person1 == null || person2 == null) {
            throw new IllegalArgumentException("Person not found");
        }

        // create and add relationship
        switch (relationType) {
        case "bioparent":
            relationshipManager.addRelationship(relationType,
                    new BioParentsRelationship(person1.getUuid(), person2.getUuid()));
            break;
        default:
            throw new IllegalArgumentException("Invalid relationship type");
        }
    }

    /**
     * Handles adding a relationship without roles based on the given command parts.
     *
     * @param commandParts The parts of the command to parse and execute
     * @throws IllegalArgumentException If the person cannot be found or the relationship type is invalid.
     */
    private void handleAddRolelessRelationship(String[] commandParts) {
        if (commandParts.length < 4) {
            throw new IllegalArgumentException("Invalid command format");
        }
        String relationType = commandParts[1].substring(1);
        String[] personUuids = (commandParts[2] + commandParts[3]).split(",");
        if (personUuids.length != 2) {
            throw new IllegalArgumentException("Invalid command format");
        }

        String uuid1 = matchUuid(personUuids[0]);
        String uuid2 = matchUuid(personUuids[1]);

        Person person1 = personMap.get(uuid1);
        Person person2 = personMap.get(uuid2);
        if (person1 == null || person2 == null) {
            throw new IllegalArgumentException("Person not found");
        }

        // create and add relationship
        switch (relationType) {
        case "friends":
            relationshipManager.addRelationship(relationType,
                    new FriendsRelationship(person1.getUuid(), person2.getUuid()));
            break;
        case "spouses":
            relationshipManager.addRelationship(relationType,
                    new SpousesRelationship(person1.getUuid(), person2.getUuid()));
            break;
        default:
            throw new IllegalArgumentException("Invalid relationship type");
        }
    }

    /**
     * Matches a partial UUID with the full UUIDs in the person map.
     *
     * @param partialUuid The partial UUID to match.
     * @return The full UUID matching the partial UUID.
     * @throws IllegalArgumentException If no matching UUID is found.
     */
    private String matchUuid(String partialUuid) {
        for (String uuid : personMap.keySet()) {
            if (uuid.endsWith(partialUuid)) {
                return uuid;
            }
        }
        throw new IllegalArgumentException("No matching UUID found.");
    }

}
