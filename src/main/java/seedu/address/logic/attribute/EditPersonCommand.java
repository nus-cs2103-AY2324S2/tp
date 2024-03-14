package seedu.address.logic.attribute;

import java.util.Map;

import seedu.address.model.person.Person;
import seedu.address.model.person.attribute.Attribute;

/**
 * This class is responsible for executing commands to edit attributes of Person objects.
 * It supports adding new attributes to a person and deleting existing attributes.
 */
public class EditPersonCommand {

    private final Map<String, Person> personMap; // Assume this is populated elsewhere

    /**
     * Constructs an EditPersonCommand with a reference to the existing map of persons.
     *
     * @param personMap A map linking person UUID strings to Person objects.
     */
    public EditPersonCommand(Map<String, Person> personMap) {
        this.personMap = personMap;
    }

    /**
     * Parses and executes the given command string to modify person attributes.
     * Supports adding or deleting attributes based on the command.
     *
     * @param command The command string to parse.
     * @throws IllegalArgumentException If the command format is invalid or unsupported.
     */
    public void parseCommand(String command) {
        String[] parts = command.split("\\s+");
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid command format.");
        }

        if ("/add".equals(parts[1])) {
            handleAddAttribute(parts);
        } else if ("/delete".equals(parts[1])) {
            handleDeleteAttribute(parts);
        } else {
            throw new IllegalArgumentException("Unsupported operation: " + parts[1]);
        }

    }

    /**
     * Handles the addition of a new attribute to a person.
     *
     * @param parts The components of the command, including the operation, UUID, attribute name, and attribute value.
     * @throws IllegalArgumentException If the command is incomplete or if the specified person cannot be found.
     */
    private void handleAddAttribute(String[] parts) {
        if (parts.length < 6) {
            throw new IllegalArgumentException("Incomplete add command.");
        }

        // Extract information
        String uuid = matchUuid(parts[3]);
        String attributeName = parts[4];
        String attributeValue = parts[5];

        // Find person and update attribute
        Person person = personMap.get(uuid);
        if (person == null) {
            throw new IllegalArgumentException("Person not found.");
        }

        Attribute attribute = Attribute.fromString(attributeName, attributeValue);
        person.updateAttribute(attribute);
    }

    /**
     * Handles the deletion of an existing attribute from a person.
     *
     * @param parts The components of the command, including the operation, UUID, and attribute name.
     * @throws IllegalArgumentException If the command is incomplete or if the specified person cannot be found.
     */
    private void handleDeleteAttribute(String[] parts) {
        if (parts.length < 5) {
            throw new IllegalArgumentException("Incomplete delete command.");
        }

        String uuid = matchUuid(parts[3]);
        String attributeName = parts[4];

        // Find person and delete attribute
        Person person = personMap.get(uuid);
        if (person == null) {
            throw new IllegalArgumentException("Person not found.");
        }

        person.deleteAttribute(attributeName);
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



