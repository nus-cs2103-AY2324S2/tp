package seedu.address.logic.relationship;

import java.util.Map;
import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;
import seedu.address.model.person.relationship.Relationship;

/**
 * Parses input arguments and creates a new AddRelationshipCommand object
 */
public class AddRelationshipCommandParser {

    /**
     * Creates a new AddRelationshipCommandParser with the given person map.
     *
     * @param personMap A map linking UUID strings to Person objects.
     */
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
    public AddRelationshipCommand parse(String userInput) throws ParseException{
        requireNonNull(userInput);
        String[] parts = userInput.split(" ", 3);
        try {
            String originUUID = ParserUtil.parseUUID(parts[0]);
            String targetUUID = ParserUtil.parseUUID(parts[1]);
            String relationshipDescriptor = parts[2];
            return new AddRelationshipCommand(originUUID, targetUUID, relationshipDescriptor);
        } catch (ParseException pe) {
            throw new ParseException(Messages.MESSAGE_INVALID_PERSON_UUID);
        }
    }
}
