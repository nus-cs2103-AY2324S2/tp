package seedu.address.logic.relationship;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input into a DeleteRelationshipCommand.
 */
public class DeleteRelationshipCommandParser implements Parser<DeleteRelationshipCommand> {
    /**
     * Parses the given user input and returns a DeleteRelationshipCommand.
     *
     * @param userInput The user input to parse.
     * @return The DeleteRelationshipCommand corresponding to the user input.
     * @throws IllegalArgumentException If the user input is invalid.
     */
    public DeleteRelationshipCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        String[] parts = userInput.split(" ");
        if (parts.length != 1 && parts.length != 3) {
            throw new ParseException(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
        }
        if (parts.length == 1) {
            String relationshipDescriptor = parts[0].toLowerCase();
            if (relationshipDescriptor.equalsIgnoreCase("family")) {
                throw new ParseException("Please specify the type of familial relationship instead of 'Family'.\n"
                        + " Valid familial relations are: [bioParents, siblings, spouses]");
            }
            if (relationshipDescriptor.equals("friend") || relationshipDescriptor.equals("bioparents")
                    || relationshipDescriptor.equals("siblings") || relationshipDescriptor.equals("spouses")) {
                throw new ParseException(Messages.MESSAGE_INVALID_PREDEFINED_RELATIONSHIP_DESCRIPTOR);
            }
            return new DeleteRelationshipCommand(relationshipDescriptor, true);
        } else {
            try {
                String originUuid = ParserUtil.parseUuid(parts[0]);
                String targetUuid = ParserUtil.parseUuid(parts[1]);
                String relationshipDescriptor = parts[2].toLowerCase();
                if (relationshipDescriptor.equalsIgnoreCase("family")) {
                    throw new ParseException("Please specify the type of familial relationship instead of 'Family'.\n"
                            + " Valid familial relations are: [bioParents, siblings, spouses]");
                }
                return new DeleteRelationshipCommand(originUuid, targetUuid, relationshipDescriptor);
            } catch (ParseException pe) {
                throw pe;
            }
        }
    }
}
