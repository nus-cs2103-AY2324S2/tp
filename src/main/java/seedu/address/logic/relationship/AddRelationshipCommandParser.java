package seedu.address.logic.relationship;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new AddRelationshipCommand object
 */
public class AddRelationshipCommandParser implements Parser<AddRelationshipCommand> {
    /**
     * Parses a userInput into the arguments to add a relationship to AB3
     * @param userInput user-input command
     * @return an addRelationshipCommand with the necessary arguments
     */
    public AddRelationshipCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        String[] parts = userInput.split(" ");
        if (parts.length != 3 && parts.length != 5) {
            throw new ParseException(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
        }
        String originUuid;
        String targetUuid;
        String relationshipDescriptor;
        if (parts.length == 5) {
            String role1 = parts[0];
            String role2 = parts[2];
            originUuid = ParserUtil.parseUuid(parts[1]);
            targetUuid = ParserUtil.parseUuid(parts[3]);
            relationshipDescriptor = parts[4];
            if (relationshipDescriptor.equalsIgnoreCase("family")) {
                throw new ParseException("Please specify the type of familial relationship instead of 'Family'.\n"
                        + " Valid familial relations are: [bioParents, siblings, spouses]");
            }
            return new AddRelationshipCommand(originUuid, targetUuid, relationshipDescriptor, role1, role2);
        } else {
            originUuid = ParserUtil.parseUuid(parts[0]);
            targetUuid = ParserUtil.parseUuid(parts[1]);
            relationshipDescriptor = parts[2];
            if (relationshipDescriptor.equalsIgnoreCase("family")) {
                throw new ParseException("Please specify the type of familial relationship instead of 'Family'.\n"
                        + " Valid familial relations are: [bioParents, siblings, spouses]");
            }
            return new AddRelationshipCommand(originUuid, targetUuid, relationshipDescriptor);
        }
    }
}
