package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAGSTATUS;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.MarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.TagStatus;

/**
 * Parses input arguments and creates a new {@code RemarkCommand} object
 */
public class MarkCommandParser implements Parser<MarkCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code MarkCommand}
     * and returns a {@code MarkCommand} object for execution.
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

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_TAG, PREFIX_TAGSTATUS)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_TAG, PREFIX_TAGSTATUS);
        String tagName = argMultimap.getValue(PREFIX_TAG).get();
        String statusIdentifier = argMultimap.getValue(PREFIX_TAGSTATUS).get();

        TagStatus tagStatus = TagStatus.getTagStatus(statusIdentifier);

        return new MarkCommand(index, tagName, tagStatus);
    }

}
