package vitalconnect.logic.parser;

import static vitalconnect.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import vitalconnect.commons.core.index.Index;
import vitalconnect.logic.commands.DeleteAptCommand;
import vitalconnect.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments to create a new {@code DeleteAptCommand} object.
 * This parser is responsible for interpreting the raw input arguments provided
 * for the deletion of an appointment and ensuring they meet the expected format.
 */
public class DeleteAptCommandParser implements Parser<DeleteAptCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteAptCommand
     * and returns an instance of DeleteAptCommand for execution.
     * <p>
     * The method expects the arguments to contain an index followed by the '/name' keyword
     * and the patient's name. The index should be a positive integer indicating the position
     * of the appointment in the list as shown to the user. The patient's name identifies
     * whose appointment is to be deleted. If the arguments do not conform to this expected
     * format, a ParseException is thrown with a message indicating proper usage.
     *
     * @param args The input arguments to be parsed.
     * @return A new instance of DeleteAptCommand encapsulating the specified index
     *         and patient's name for the appointment to be deleted.
     * @throws ParseException If the provided arguments do not conform to the expected
     *                        format or if other parsing errors occur, such as an invalid
     *                        integer for the index.
     */
    public DeleteAptCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteAptCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
              String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteAptCommand.MESSAGE_USAGE), pe);
        }
    }
}
