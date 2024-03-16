package seedu.address.logic.relationship;

import java.util.Map;

import seedu.address.model.person.Person;

/**
 * Parses input arguments and creates a new AddRelationshipCommand object
 */
public class AddRelationshipCommandParser {

    private final Map<String, Person> personMap;

    /**
     * Creates a new AddRelationshipCommandParser with the given person map.
     *
     * @param personMap A map linking UUID strings to Person objects.
     */
    public AddRelationshipCommandParser(Map<String, Person> personMap) {
        this.personMap = personMap;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddRelationshipCommand
     * and returns an AddRelationshipCommand object for execution.
     *
     * The expected format of the user input is:
     * - For adding relationships with roles: "addrelation /[relationType] /[role1] [UUID1] /[role2] [UUID2]"
     * - For adding relationships without roles: "addrelation /[relationType] [UUID1],[UUID2]"
     *
     * @param userInput The user input to parse.
     * @return The AddRelationshipCommand object for execution.
     * @throws IllegalArgumentException If the user input does not conform the expected format.
     */
    public AddRelationshipCommand parse(String userInput) throws IllegalArgumentException {
        AddRelationshipCommand command = new AddRelationshipCommand(personMap);
        command.parseCommand(userInput);
        return command;
    }

}
