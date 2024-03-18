package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.MarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.TagStatus;

import java.util.stream.Stream;

/**
 * Parses input arguments and creates a new {@code RemarkCommand} object
 */
public class MarkCommandParser implements Parser<MarkCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code RemarkCommand}
     * and returns a {@code RemarkCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TAG, PREFIX_TAGSTATUS);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE), ive);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_TAG, PREFIX_TAGSTATUS)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_TAG, PREFIX_TAGSTATUS);
        String tagName = argMultimap.getValue(PREFIX_TAG).get();
        String status = argMultimap.getValue(PREFIX_TAGSTATUS).get();
        TagStatus tagStatus;

        // the strings for status can be better abstracted out
        switch (status) {
        case "cg":
            tagStatus = TagStatus.COMPLETE_GOOD;
            break;
        case "cb":
            tagStatus = TagStatus.COMPLETE_BAD;
            break;
        case "ig":
            tagStatus = TagStatus.INCOMPLETE_GOOD;
            break;
        case "ib":
            tagStatus = TagStatus.INCOMPLETE_BAD;
            break;
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE));
        }

        return new MarkCommand(index, tagName, tagStatus);
    }


    // duplicate method for AddCommandParser::arePrefixesPresent
    // should abstract it out to be placed in a util class afterwards
    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
