package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditAppointmentCommand;
import seedu.address.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditAppointmentCommandParser implements Parser<EditAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditAppointmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DATE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditAppointmentCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_DATE);

        EditAppointmentDescriptor editAppointmentDescriptor = new EditAppointmentDescriptor();

        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            editAppointmentDescriptor.setDate(ParserUtil.parseAppointmentDate(argMultimap.getValue(PREFIX_DATE).get()));
        }

        if (!editAppointmentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditAppointmentCommand.MESSAGE_NOT_EDITED);
        }

        return new EditAppointmentCommand(index, editAppointmentDescriptor);
    }

}
