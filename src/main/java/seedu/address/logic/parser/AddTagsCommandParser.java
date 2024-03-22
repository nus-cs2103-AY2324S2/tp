package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_TAG_LENGTH_EXCEEDED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;
import static seedu.address.model.tag.Tag.MAX_TAG_LENGTH;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AddTagsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddTagsCommand object
 */
public class AddTagsCommandParser implements Parser<AddTagsCommand> {

    private static final Logger logger = LogsCenter.getLogger(AddTagsCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the AddTagsCommand
     * and returns an AddTagsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTagsCommand parse(String args) throws ParseException {
        requireNonNull(args);
        logger.log(Level.INFO, "Parsing AddTagsCommand: " + args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_TAG) || argMultimap.getPreamble().isEmpty()) {
            logger.log(Level.WARNING, "Invalid command format for AddTagsCommand: " + args);
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddTagsCommand.MESSAGE_USAGE));
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            logger.warning("Index is not a non-zero unsigned integer in AddTagsCommand: " + args);
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagsCommand.MESSAGE_USAGE), ive);
        }

        Set<Tag> tagList;
        try {
            tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        } catch (ParseException pe) {
            logger.log(Level.WARNING, "Invalid tags provided in AddTagsCommand: " + args);
            throw new ParseException(pe.getMessage(), pe);
        }

        logger.log(Level.INFO, "Successfully parsed AddTagsCommand with index " + index + " and tags " + tagList);

        return new AddTagsCommand(index, tagList);
    }

//    /**
//     * Checks if any tag in the list exceeds the maximum allowed length.
//     *
//     * @param tags The set of tags to check.
//     * @throws ParseException if any tag exceeds the maximum length.
//     */
//    public void checkTagLength(Set<Tag> tags) throws ParseException {
//        for (Tag tag : tags) {
//            if (tag.tagName.length() > MAX_TAG_LENGTH) {
//                throw new ParseException(String.format(MESSAGE_TAG_LENGTH_EXCEEDED, MAX_TAG_LENGTH));
//            }
//        }
//    }
}
