package vitalConnect.logic.parser;

import vitalConnect.logic.commands.DeleteAptCommand;
import vitalConnect.logic.parser.exceptions.ParseException;

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
            String trimmedArgs = args.trim();
            if (!trimmedArgs.matches("\\d+ /name .+")) {
                throw new ParseException(DeleteAptCommand.MESSAGE_USAGE);
            }

            String[] parts = trimmedArgs.split("/name");
            if (parts.length != 2) {
                throw new ParseException(DeleteAptCommand.MESSAGE_USAGE);
            }

            int index = Integer.parseInt(parts[0].trim());
            String patientName = parts[1].trim();

            if (index <= 0) {
                throw new ParseException("Index must be a positive integer.");
            }

            return new DeleteAptCommand(index, patientName);
        } catch (NumberFormatException e) {
            throw new ParseException("The index provided is not a valid integer.");
        }
    }
}
