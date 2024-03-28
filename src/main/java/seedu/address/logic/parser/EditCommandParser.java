package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditCourseMateDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.coursemate.QueryableCourseMate;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TELEGRAM);

        QueryableCourseMate queryableCourseMate;

        try {
            queryableCourseMate = ParserUtil.parseQueryableCourseMate(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TELEGRAM);

        EditCourseMateDescriptor editCourseMateDescriptor = new EditCourseMateDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editCourseMateDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editCourseMateDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editCourseMateDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_TELEGRAM).isPresent()) {
            editCourseMateDescriptor.setTelegramHandle(
                    ParserUtil.parseTelegramHandle(argMultimap.getValue(PREFIX_TELEGRAM).get()));
        }

        if (!editCourseMateDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(queryableCourseMate, editCourseMateDescriptor);
    }
}
