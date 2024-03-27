package seedu.address.logic.relationship;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input into an EditRelationshipCommand.
 */
public class EditRelationshipCommandParser {
    /**
     * Parses the given user input and returns an EditRelationshipCommand.
     *
     * @param userInput The user input to parse.
     * @return The EditRelationshipCommand corresponding to the user input.
     * @throws IllegalArgumentException If the user input is invalid.
     */
    public EditRelationshipCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        String[] parts = userInput.split(" ");
        if (parts.length != 4 && parts.length != 6) {
            throw new ParseException(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
        }
        try {
            if (parts.length == 6) {
                String role1 = parts[0];
                String originUuid = ParserUtil.parseUuid(parts[1]);
                String role2 = parts[2];
                String targetUuid = ParserUtil.parseUuid(parts[3]);
                String oldRelationshipDescriptor = parts[4];
                String newRelationshipDescriptor = parts[5];
                if (newRelationshipDescriptor.equalsIgnoreCase("family")) {
                    throw new ParseException("Please specify the type of familial relationship instead of 'Family'.\n"
                            + " Valid familial relations are: [bioParents, siblings, spouses]");
                }
                if (!role1.matches("[a-zA-Z]+") || !role2.matches("[a-zA-Z]+")) {
                    throw new ParseException("Roles must contain only letters");
                }
                return new EditRelationshipCommand(originUuid, targetUuid, oldRelationshipDescriptor,
                        newRelationshipDescriptor, role1, role2);
            } else {
                String originUuid = ParserUtil.parseUuid(parts[0]);
                String targetUuid = ParserUtil.parseUuid(parts[1]);
                String oldRelationshipDescriptor = parts[2];
                String newRelationshipDescriptor = parts[3];
                if (newRelationshipDescriptor.equalsIgnoreCase("family")) {
                    throw new ParseException("Please specify the type of familial relationship instead of 'Family'.\n"
                            + " Valid familial relations are: [bioParents, siblings, spouses]");
                }
                return new EditRelationshipCommand(originUuid, targetUuid, oldRelationshipDescriptor,
                        newRelationshipDescriptor);
            }
        } catch (ParseException pe) {
            throw pe;
        }
    }
}
