package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AddTagsCommand;
import seedu.address.logic.commands.DeleteTagsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses the user's input arguments and creates a new DeleteTagsCommand object
 */
public class DeleteTagsCommandParser implements Parser<DeleteTagsCommand> {
    private static final Logger logger = LogsCenter.getLogger(DeleteTagsCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteTagsCommand
     * and returns an DeleteTagsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteTagsCommand parse(String args) throws ParseException {
        requireNonNull(args);
        logger.info("Parsing DeleteTagsCommand: " + args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_TAG) || argMultimap.getPreamble().isEmpty()) {
            logger.warning("Invalid command format for DeleteTagsCommand: " + args);
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteTagsCommand.MESSAGE_USAGE));
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            logger.warning("Index is not a non-zero unsigned integer in DeleteTagsCommand: " + args);
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagsCommand.MESSAGE_USAGE), ive);
        }

        Set<Tag> tagList;
        try {
            tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        } catch (ParseException pe) {
            logger.warning("Invalid tags provided in DeleteTagsCommand: " + args);
            throw new ParseException(pe.getMessage(), pe);
        }

        logger.log(Level.INFO, "Successfully parsed DeleteTagsCommand with index " + index + " and tags " + tagList);

        return new DeleteTagsCommand(index, tagList);
    }
}
