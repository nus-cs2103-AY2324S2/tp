package seedu.address.logic.parser;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.DeleteAttributeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteAttributeCommandParser implements Parser<DeleteAttributeCommand> {

    /**
     *  Parses the given {@code String} of arguments in the context of the DeleteAttributeCommand
     *  and returns an DeleteAttributeCommand object for execution.
     *  Command format: delete UUID /attributeName1 /attributeName2 ...
     *  Examples:
     *  delete
     *  delete /Name /Phone /address
     */
    public DeleteAttributeCommand parse(String args) throws ParseException {
        args = args.trim();
        String[] parts = args.split("/", -1);
        String uuid = parts[0].trim();
        if (parts[0].isEmpty()) {
            throw new ParseException(Messages.MESSAGE_MISSING_UUID + "\n" + DeleteAttributeCommand.MESSAGE_USAGE);
        }
        if (parts.length < 2) {
            throw new ParseException(Messages.MESSAGE_MISSING_ATTRIBUTES
                    + "\n" + DeleteAttributeCommand.MESSAGE_USAGE);
        }
        if (uuid.split(" ").length > 1) {
            throw new ParseException(Messages.MESSAGE_SPACES_IN_UUID
                    + uuid.split(" ") + "\n"
                    + DeleteAttributeCommand.MESSAGE_USAGE);
        }

        String[] attributeNames = ParserUtil.removeFirstItemFromStringList(parts);

        return new DeleteAttributeCommand(uuid, attributeNames);
    }
}
